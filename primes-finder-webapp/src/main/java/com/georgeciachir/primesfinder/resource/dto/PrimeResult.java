package com.georgeciachir.primesfinder.resource.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "with")
public class PrimeResult {

    private final String value;
}
