package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.GlobalExceptionHandler;
import br.com.vibbra.notificationservice.controller.response.app.AppResponse;
import br.com.vibbra.notificationservice.controller.response.app.AppResponseStub;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.JwtTokenStub;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.dto.UserStub;
import br.com.vibbra.notificationservice.service.AppService;
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

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AppControllerTest {

    private static MockMvc mockMvc;
    private static final String TOKEN = "such a valid token, wow";
    private static final String BASE_URI = "/v1/apps";
    @Mock
    private AppService appService;
    @Mock
    private AuthService authService;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AppController(appService, authService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void shouldFindAppById() throws Exception {
        String input = ResourceUtils.loadResourceAsString("json/auth/app_request.json");
        JwtToken jwtToken = JwtTokenStub.create();
        AppResponse appResponse = AppResponseStub.create();

        when(appService.createApp(any(), any())).thenReturn(appResponse);
        when(authService.decodeToken(eq(TOKEN))).thenReturn(jwtToken);

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content(input)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.app_id").value(appResponse.getAppId()))
                .andExpect(jsonPath("$.app_name").value(appResponse.getAppName()))
                .andExpect(jsonPath("$.app_token").value(appResponse.getAppToken()));
    }

    @Test
    public void shouldCreateApp() throws Exception {
        JwtToken jwtToken = JwtTokenStub.create();
        AppResponse appResponse = AppResponseStub.create();

        when(appService.findAppById(any(), any()))
                .thenReturn(appResponse);
        when(authService.decodeToken(eq(TOKEN)))
                .thenReturn(jwtToken);

        mockMvc.perform(get(BASE_URI.concat("/123"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.app_id").value(appResponse.getAppId()))
                .andExpect(jsonPath("$.app_name").value(appResponse.getAppName()))
                .andExpect(jsonPath("$.app_token").value(appResponse.getAppToken()));
    }
}