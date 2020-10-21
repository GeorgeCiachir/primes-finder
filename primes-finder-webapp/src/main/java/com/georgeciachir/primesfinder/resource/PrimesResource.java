package com.georgeciachir.primesfinder.resource;

import com.georgeciachir.primesfinder.resource.dto.PrimeResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;

public interface PrimesResource {

    String RESOURCE_ENDPOINT = "/primes";
    String NEXT = "/next";
    String IS_PRIME = "/isprime";

    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "The provided input does not meet the required criteria")
    })
    @ApiOperation("Returns the next prime number equal to or greater than the given input\n" +
            "The decision to include or not the current value is provided\n" +
            "by the boolean parameter")
    @GetMapping(path = NEXT, produces = MediaTypes.HAL_JSON_VALUE)
    EntityModel<PrimeResult> nextPrime(
            @Parameter(description = "The String representation of the value to start from in order to find the next " +
                    "prime number. The value must not be signed (\"-\" or \"+\", and must not contain letters or special characters")
                    String from,
            @Parameter(description = "If you want to include the current value in the search")
                    boolean includingFrom);

    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "400", description = "The provided input does not meet the required criteria")
    })
    @ApiOperation("Returns whether the the given input is a prime number")
    @GetMapping(path = IS_PRIME, produces = MediaTypes.HAL_JSON_VALUE)
    boolean isPrime(
            @Parameter(description = "The String representation of the value to be tested. " +
                    "The value must not be signed (\"-\" or \"+\", and must not contain letters or special characters")
                    String toTest);
}
