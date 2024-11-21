package com.example.resttemplate.config;

import io.micrometer.common.KeyValue;
import io.micrometer.common.KeyValues;
import org.springframework.http.client.observation.ClientHttpObservationDocumentation;
import org.springframework.http.client.observation.ClientRequestObservationContext;
import org.springframework.http.client.observation.ClientRequestObservationConvention;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
public class CustomRestTemplateExchangeTagsProvider implements ClientRequestObservationConvention {

    @Override
    public String getName() {
        // We can customize the metric name as per our own requirement
        return "http.client.requests";
    }

    @Override
    @NonNull
    public KeyValues getLowCardinalityKeyValues(@NonNull ClientRequestObservationContext context) {
        return KeyValues.of(method(context), status(context), exception(context));
    }

    protected KeyValue method(ClientRequestObservationContext context) {
        // return KeyValue.of(ClientHttpObservationDocumentation.LowCardinalityKeyNames.METHOD, context.getCarrier().getMethod().toString());
        return KeyValue.of("method 1", Objects.requireNonNull(context.getCarrier()).getMethod().toString());
    }

    protected KeyValue status(ClientRequestObservationContext context) {
        String statusCode = "";
        try {
            statusCode = Integer.toString(context.getResponse().getStatusCode().value());
        } catch (Exception e) {

        }
        // return KeyValue.of(ClientHttpObservationDocumentation.LowCardinalityKeyNames.STATUS, statusCode);
        return KeyValue.of("status 3", statusCode);
    }

    protected KeyValue exception(ClientRequestObservationContext context) {
        if (context.getError() != null) {
            return KeyValue.of(ClientHttpObservationDocumentation.LowCardinalityKeyNames.EXCEPTION, context.getError().getClass().getName());
        } else {
            return KeyValue.of(ClientHttpObservationDocumentation.LowCardinalityKeyNames.EXCEPTION, "none");
        }
    }

}
