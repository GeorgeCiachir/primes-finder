package com.georgeciachir.primesfinder.service;

import com.georgeciachir.primesfinder.exception.InvalidRequestException;
import org.springframework.stereotype.Component;

@Component
public class InputValidator {

    public static final String INVALID_INPUT_MESSAGE = "The provided input does not meet the required criteria";

    /**
     * Validates that the input is a string representation of a number and
     * does not contain letters or special characters
     *
     * @param input The value to validate
     */
    void validate(String input) {
        if (input.isEmpty()) {
            throw new InvalidRequestException(INVALID_INPUT_MESSAGE);
        }

        if (!input.matches("^[0-9]*$")) {
            throw new InvalidRequestException(INVALID_INPUT_MESSAGE);
        }

        if (input.startsWith("0") && input.length() > 1) {
            throw new InvalidRequestException(INVALID_INPUT_MESSAGE);
        }
    }
}
