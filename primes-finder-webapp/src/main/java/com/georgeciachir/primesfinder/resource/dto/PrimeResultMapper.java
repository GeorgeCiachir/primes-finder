package com.georgeciachir.primesfinder.resource.dto;

import org.springframework.stereotype.Component;

@Component
public class PrimeResultMapper {

    public PrimeResult toPrimeResponse(String value) {
        return PrimeResult.builder()
                .withValue(value)
                .build();
    }
}
