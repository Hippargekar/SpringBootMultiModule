package org.root.runner;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.DefaultBackoffStrategy;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.impl.DefaultConnectionReuseStrategy;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.pool.PoolStats;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

public class HttpClientTest {
//<dependency>
//    <groupId>com.fasterxml.jackson.datatype</groupId>
//    <artifactId>jackson-datatype-jsr310</artifactId>
//    <version>2.13.3</version>
//</dependency>
//ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();

// try {
//    Resource resource = new ClassPathResource(@Value("${file.path}"));
//    String license = StreamUtils.copyToString(
//            resource.getInputStream(),
//            StandardCharsets.UTF_8
//    );
//} catch (IOException ex) {
//
//}

    public static void main(String[] args) {
        String json = "{"
                + "    \"password\": \"secret\","
                + "    \"username\": \"wdbyte.com\""
                + "}";
        String result = post("http://httpbin.org/post", json);
        System.out.println(result);
    }

    public static String post(String requestUrl, String jsonBody) {
        String result = null;
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                System.out.println(response.getCode());
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    private PoolingHttpClientConnectionManager buildConnectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setValidateAfterInactivity(TimeValue.ofSeconds(600));
        return connectionManager;
    }

    private RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(5L))
                .setResponseTimeout(5L, TimeUnit.SECONDS)
                .setConnectionRequestTimeout(3L, TimeUnit.SECONDS)
                .build();
    }

    public HttpClient httpClient() {
        return HttpClients.custom()
                .setConnectionManager(buildConnectionManager())
                .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRetryStrategy(buildRetryStrategy())
                .setConnectionBackoffStrategy(new DefaultBackoffStrategy())
                .setDefaultRequestConfig(requestConfig())
                .evictExpiredConnections()//Cleans up already expired HTTP connections, preventing invalid connections from being reused.
                .evictIdleConnections(TimeValue.ofSeconds(30))//Prevents resource waste by removing idle connections that have not been used for a set amount of time.
                .build();
    }

    public RestClient restClient(HttpClient httpClient) {
        return RestClient.builder()
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }

    private DefaultHttpRequestRetryStrategy buildRetryStrategy() {
        return new DefaultHttpRequestRetryStrategy(
                0, TimeValue.ofSeconds(1L)
        );
    }

    public RestTemplate restTemplate(boolean sslHostnameValidationEnabled) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Charset srcCharset = StandardCharsets.UTF_8;//Charset.forName("UTF-8")
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)//new TrustSelfSignedStrategy()
                .build();
        String[] SUPPORT_TLS_VERSION = {"TLSv1.2", "TLSv1.3"};
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext
                ,null, null, NoopHostnameVerifier.INSTANCE);

//        RegistryBuilder<ConnectionSocketFactory> registryBuilder = RegistryBuilder.<ConnectionSocketFactory>create()
//                .register("http", PlainConnectionSocketFactory.INSTANCE);
//        if (sslHostnameValidationEnabled) {
//            registryBuilder.register("https",
//                    new SSLConnectionSocketFactory(sslContext));
//        } else {
//            registryBuilder.register("https", new SSLConnectionSocketFactory(
//                    sslContext, NoopHostnameVerifier.INSTANCE));
//        }
//        Registry<ConnectionSocketFactory> registry = registryBuilder.build();

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", csf)
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

        PoolStats stats = connectionManager.getTotalStats();
        RequestConfig requestConfig = RequestConfig.custom().build();

        HttpComponentsClientHttpRequestFactory requestFactory =  new HttpComponentsClientHttpRequestFactory();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .evictIdleConnections(TimeValue.ofSeconds(30))
                .build();

        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

}
