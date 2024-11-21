package com.example.resttemplate.config;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.actuate.metrics.web.client.RestTemplateExchangeTagsProvider;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class CustomRestTemplateExchangeTagsProvider implements RestTemplateExchangeTagsProvider {

    @Override
    public Iterable<Tag> getTags(String urlTemplate, HttpRequest request, ClientHttpResponse response) {
        // Create custom tags based on the HTTP request and response
        Tags tags = Tags.empty()
                .and("method 1", Objects.requireNonNull(request.getMethod()).name())
                .and("uri 2", request.getURI().toString());

        if (response != null) {
            // Add HTTP status code tag if response is available
            try {
                tags = tags.and("status 3", String.valueOf(response.getStatusCode().value()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return tags;
    }

}
