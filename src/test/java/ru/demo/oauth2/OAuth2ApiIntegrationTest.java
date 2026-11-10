package ru.demo.oauth2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OAuth2ApiIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void exposesAuthorizationUrlWithoutLogin() throws Exception {
        mockMvc.perform(get("/api/auth/login-url"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorizationUrl", is("/oauth2/authorization/github")));
    }

    @Test
    void exposesCsrfTokenForLogoutRequest() throws Exception {
        mockMvc.perform(get("/api/csrf"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.headerName", is("X-XSRF-TOKEN")))
                .andExpect(jsonPath("$.token").isNotEmpty());
    }
}
