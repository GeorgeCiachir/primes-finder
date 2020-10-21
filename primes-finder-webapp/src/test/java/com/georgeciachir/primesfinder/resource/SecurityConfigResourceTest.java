package com.georgeciachir.primesfinder.resource;

import com.georgeciachir.primesfinder.WebIntegrationTest;
import org.junit.Test;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecurityConfigResourceTest extends WebIntegrationTest {

    @WithAnonymousUser
    @Test
    public void shouldReturn401WhenNotAuthenticated() throws Exception {
        mvc.perform(
                get("/test-resource"))
                .andExpect(status().isUnauthorized());
    }

    @WithMockUser("clientName")
    @Test
    public void shouldReturn404WhenAuthenticated() throws Exception {
        mvc.perform(
                get("/test-resource"))
                .andExpect(status().isNotFound());
    }

}
