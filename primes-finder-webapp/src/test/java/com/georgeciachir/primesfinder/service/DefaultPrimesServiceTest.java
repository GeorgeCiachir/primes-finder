package com.georgeciachir.primesfinder.service;

import com.georgeciachir.primesfinder.ServiceIntegrationTest;
import com.georgeciachir.primesfinder.exception.InvalidRequestException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.georgeciachir.primesfinder.service.InputValidator.INVALID_INPUT_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;

public class DefaultPrimesServiceTest extends ServiceIntegrationTest {

    @Autowired
    private DefaultPrimesService service;

    @Test
    public void givenInvalidInput_whenPrimeIsRequested_thenInvalidate() {
        String invalidValue = "-3";

        try {
            service.findNextPrime(invalidValue, true);
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }


    @Test
    public void givenPrimeValueValidInput_whenPrimeIsRequestedIncluding_thenRetrieveSaPrime() {
        String validValue = "3";

        String nextPrime = service.findNextPrime(validValue, true);

        assertThat(nextPrime).isEqualTo("3");
    }

    @Test
    public void givenPrimeValueValidInput_whenNextPrimeIsRequested_thenRetrieveNextPrime() {
        String validValue = "3";

        String nextPrime = service.findNextPrime(validValue, false);

        assertThat(nextPrime).isEqualTo("5");
    }

    @Test
    public void givenInvalidInput_whenPrimalityTestIsRequested_thenInvalidate() {
        String invalidValue = "-3";

        try {
            service.isPrime(invalidValue);
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
            assertThat(e.getMessage()).isEqualTo(INVALID_INPUT_MESSAGE);
        }
    }


    @Test
    public void givenPrimeValueValidInput_whenIsPrime_thenVerifyIsPrime() {
        String validValue = "3";

        boolean nextPrime = service.isPrime(validValue);

        assertThat(nextPrime).isEqualTo(true);
    }

    @Test
    public void givenPrimeValueValidInput_whenIsNotPrime_thenVerifyIsNotPrime() {
        String validValue = "4";

        boolean nextPrime = service.isPrime(validValue);

        assertThat(nextPrime).isEqualTo(false);
    }
}