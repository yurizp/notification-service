package br.com.vibbra.notificationservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.vibbra.notificationservice.config.GlobalExceptionHandler;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.JwtTokenStub;
import br.com.vibbra.notificationservice.exceptions.ValidationException;
import br.com.vibbra.notificationservice.service.AuthService;
import br.com.vibbra.notificationservice.utils.ResourceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    private static MockMvc mockMvc;
    private static final String TOKEN = "such a valid token, wow";
    private static final String BASE_URI = "/v1/login";

    @Mock
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(authService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void shouldCreateUser() throws Exception {
        String input = ResourceUtils.loadResourceAsString("json/login/request_login.json");

        JwtToken jwtToken = JwtTokenStub.create();
        when(authService.generateToken(any())).thenReturn(jwtToken);

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content(input)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.user.id").value(jwtToken.getUser().getId()))
                .andExpect(jsonPath("$.user.email").value(jwtToken.getUser().getEmail()))
                .andExpect(jsonPath("$.token").value(jwtToken.getToken()))
                .andExpect(jsonPath("$.expiration").isNotEmpty())
                .andExpect(jsonPath("$.issuedAt").isNotEmpty());
    }

    @Test
    public void shouldReturnErrorWhenInputIsNullCreateUser() throws Exception {

        String response = ResourceUtils.loadResourceAsString("json/login/response_requerid_fields.json");
        ValidationException validationException = new ValidationException("");
        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(validationException.getStatus().value()))
                .andExpect(content().json(response));
    }
}
