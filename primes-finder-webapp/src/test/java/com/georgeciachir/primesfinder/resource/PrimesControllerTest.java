package com.georgeciachir.primesfinder.resource;

import com.georgeciachir.primesfinder.WebIntegrationTest;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PrimesControllerTest extends WebIntegrationTest {

    private static final String NEXT_PRIME_URL = PrimesController.RESOURCE_ENDPOINT + PrimesController.NEXT;
    private static final String IS_PRIME_URL = PrimesController.RESOURCE_ENDPOINT + PrimesController.IS_PRIME;

    @Test
    public void givenAPrimeValueFrom_whenTheValueShouldBeIncluded_thenIncludeTheValue() throws Exception {
        mvc.perform(
                get(NEXT_PRIME_URL)
                        .queryParam("from", "13"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", is("13")));
    }

    @Test
    public void givenAPrimeValueFrom_whenTheValueShouldNotBeIncluded_thenDoNotIncludeTheValue() throws Exception {
        mvc.perform(
                get(NEXT_PRIME_URL)
                        .queryParam("from", "13")
                        .queryParam("includingFrom", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value", is("17")));
    }

    @Test
    public void givenAValueFrom_whenTheValueIsNotValid_thenIsABadRequest() throws Exception {
        String negativeNumberInput = "-3";

        mvc.perform(
                get(NEXT_PRIME_URL)
                        .queryParam("from", negativeNumberInput)
                        .queryParam("includingFrom", "false"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The provided input does not meet the required criteria"));
    }

    @Test
    public void givenAPrimeValue_whenTheValueRespectsTheConditions_testForPrime() throws Exception {
        mvc.perform(
                get(IS_PRIME_URL)
                        .queryParam("toTest", "13"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void givenAPrimeValue_whenTheValueDoesNotRespectTheConditions_thenIsABadRequest() throws Exception {
        String negativeNumberInput = "-3";

        mvc.perform(
                get(IS_PRIME_URL)
                        .queryParam("toTest", negativeNumberInput))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The provided input does not meet the required criteria"));
    }


}
