package com.example.resttemplate.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RestTemplateMetricsInterceptor implements ClientHttpRequestInterceptor {

    private final MeterRegistry meterRegistry;

    public RestTemplateMetricsInterceptor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        long startTime = System.nanoTime();

        ClientHttpResponse response = execution.execute(request, body);

        // Track HTTP request and response time
        long duration = System.nanoTime() - startTime;
        meterRegistry.timer("http.client.requests", Tags.of(
                "method", request.getMethod().name(),
                "uri", request.getURI().toString(),
                "status", String.valueOf(response.getStatusCode().value())
        )).record(duration, TimeUnit.NANOSECONDS);

        return response;
    }
}
