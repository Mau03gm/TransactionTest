package com.mauriciogomez.transaction.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauriciogomez.transaction.exception.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.InputStream;

public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            InputStream body = response.body().asInputStream();

            ErrorResponse error = objectMapper.readValue(body, ErrorResponse.class);

            return new RuntimeException(error.getMessage());

        } catch (Exception e) {
            return new RuntimeException("Unknown Feign error");
        }
    }
}