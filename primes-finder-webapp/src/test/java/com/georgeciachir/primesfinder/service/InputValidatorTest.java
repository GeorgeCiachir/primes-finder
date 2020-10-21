package com.georgeciachir.primesfinder.service;

import com.georgeciachir.primesfinder.UnitTest;
import com.georgeciachir.primesfinder.exception.InvalidRequestException;
import org.junit.Test;
import org.mockito.InjectMocks;

import static com.georgeciachir.primesfinder.service.InputValidator.INVALID_INPUT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;

public class InputValidatorTest extends UnitTest {

    @InjectMocks
    public InputValidator validator;

    @Test
    public void givenValidInput_validate() {
        try {
            validator.validate("1");
        } catch (Exception e) {
            fail("It should have not get here");
        }
    }

    @Test
    public void givenNegativeInput_invalidate() {
        try {
            validator.validate("-3");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }

    @Test
    public void givenEmptyInput_invalidate() {
        try {
            validator.validate("");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }

    @Test
    public void givenBlankInput_invalidate() {
        try {
            validator.validate("    ");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }

    @Test
    public void givenInputContainsLetters_invalidate() {
        try {
            validator.validate("No1");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }

    @Test
    public void givenInputContainsSpecialCharacters_invalidate() {
        try {
            validator.validate("#1");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }

    @Test
    public void givenInputStartsWithZeroAndHasAdditionalContent_invalidate() {
        try {
            validator.validate("031");
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }
}