package org.root.runner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomHttpClientResponseHandler<T> implements HttpClientResponseHandler<Client5Response<T>> {
    private static final Logger logger = LoggerFactory.getLogger(CustomHttpClientResponseHandler.class);
    private final Class<T> responseType;

    public CustomHttpClientResponseHandler(Class<T> responseType) {
        this.responseType = responseType;
    }

    // ResponseHandler<Client5Resp<Person>> responseHandler = new CustomHttpClientResponseHandler<>(Person.class);
    //Client5Resp<Person> response = httpClient.execute(request, responseHandler);
    @Override
    public Client5Response<T> handleResponse(ClassicHttpResponse response) {
        try {
            ProtocolVersion version = response.getVersion();
            String reasonPhrase = response.getReasonPhrase();

            int statusCode = response.getCode();
            HttpEntity entity = response.getEntity();
            String contentType = entity.getContentType();
            //byte[] responseBytes = EntityUtils.toByteArray(response.getEntity());
            String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            //String responseJson = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
            if (statusCode >= HttpStatus.SC_BAD_REQUEST) {
                logger.error("HTTP error response received with status code: {}", statusCode);
                return new Client5Response<>(statusCode, responseBody, null);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            T data = objectMapper.readValue(responseBody, responseType);
            return new Client5Response<>(statusCode, responseBody, data);
        } catch (JsonProcessingException ex) {
            logger.error("JSON parsing error: {}", ex.getMessage(), ex);
        } catch (HttpException ex) {
            logger.error("HTTP protocol violations error: {}", ex.getMessage(), ex);
        }catch (IOException ex) {
            logger.error("Network or I/O error while reading the response: {}", ex.getMessage(), ex);
        }catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
        }
        return null;
    }
}
