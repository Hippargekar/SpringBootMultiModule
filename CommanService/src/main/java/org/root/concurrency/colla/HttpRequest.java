package org.root.concurrency.colla;

import java.util.concurrent.Callable;

public class HttpRequest implements Callable<String> {
    private final String httpClient;
    private final String httpRequest;

    public HttpRequest(String httpClient, String httpRequest) {
        this.httpClient = httpClient;
        this.httpRequest = httpRequest;
    }

    @Override
    public String call() throws Exception {
        String variable = "Callback";
        if(httpClient == null){
            throw new NullPointerException("Value is null");
        }
        Thread.sleep(2000); // Simulate delay
        return variable + "successfully processed.";
    }
}
