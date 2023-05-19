package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.GlobalExceptionHandler;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponse;
import br.com.vibbra.notificationservice.controller.response.notification.NotificationResponseStub;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.JwtTokenStub;
import br.com.vibbra.notificationservice.dto.NotificationConfigResponse;
import br.com.vibbra.notificationservice.facade.NotificationFacade;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {


    private static MockMvc mockMvc;
    private static final String TOKEN = "such a valid token, wow";
    private static final String BASE_URI = "/v1/apps";
    @Mock
    public NotificationFacade facede;
    @Mock
    private AuthService authService;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new NotificationController(facede, authService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void shouldCreateUser() throws Exception {
        String input = ResourceUtils.loadResourceAsString("json/notification/settings_request.json");

        JwtToken jwtToken = JwtTokenStub.create();
        when(authService.decodeToken(any())).thenReturn(jwtToken);

        mockMvc.perform(post(BASE_URI.concat("/123/WEBPUSHES/settings"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content(input)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200));
    }

    @Test
    public void shouldReturnErrorWhenCreateSettingsBodyIsEmpty() throws Exception {
        String input = ResourceUtils.loadResourceAsString("json/notification/settings_request_with_all_empty.json");
        String response = ResourceUtils.loadResourceAsString("json/notification/settings_response_error_with_all_empty.json");

        mockMvc.perform(post(BASE_URI.concat("/123/WEBPUSHES/settings"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content(input)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(400))
                .andExpect(content().json(response));
    }

    @Test
    public void shouldReturnSuccessfullyEnableOrDisable() throws Exception {
        NotificationConfigResponse notificationConfigResponse = NotificationConfigResponse.builder().previousStatus(true).currentStatus(false).build();
        JwtToken jwtToken = JwtTokenStub.create();

        when(authService.decodeToken(any())).thenReturn(jwtToken);
        when(facede.enableOrDisableNotification(any(), any(), any())).thenReturn(notificationConfigResponse);

        String response = ResourceUtils.loadResourceAsString("json/notification/enable_or_disable_notification_response.json");

        mockMvc.perform(put(BASE_URI.concat("/123/WEBPUSHES/settings"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(content().json(response));
    }

    @Test
    public void shouldReturnSuccessfullyGetSettingsToWebpushes() throws Exception {
        NotificationResponse notificationResponse = NotificationResponseStub.createWebpushResponse();
        JwtToken jwtToken = JwtTokenStub.create();

        when(authService.decodeToken(any())).thenReturn(jwtToken);
        when(facede.findConfig(any(), any(), any())).thenReturn(notificationResponse);

        String response = ResourceUtils.loadResourceAsString("json/notification/get_settings.json");

        mockMvc.perform(get(BASE_URI.concat("/123/WEBPUSHES/settings"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(content().json(response));
    }


}