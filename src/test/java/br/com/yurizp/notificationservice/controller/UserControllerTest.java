package br.com.yurizp.notificationservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.vibbra.notificationservice.config.GlobalExceptionHandler;
import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.notificationservice.dto.UserStub;
import br.com.vibbra.notificationservice.exceptions.ValidationException;
import br.com.yurizp.notificationservice.service.UserService;
import br.com.yurizp.notificationservice.utils.ResourceUtils;
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
class UserControllerTest {
    private static MockMvc mockMvc;
    private static final String TOKEN = "such a valid token, wow";
    private static final String BASE_URI = "/v1/users";

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void shouldCreateUser() throws Exception {
        String input = ResourceUtils.loadResourceAsString("json/users/users_valid_input.json");

        User user = UserStub.create();

        when(userService.createUser(any())).thenReturn(user);

        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content(input)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.phone_number").value(user.getPhoneNumber()))
                .andExpect(jsonPath("$.company_name").value(user.getCompanyName()))
                .andExpect(jsonPath("$.company_address").value(user.getCompanyAddress()));
    }

    @Test
    public void shouldReturnErrorWhenInputIsNullCreateUser() throws Exception {

        String input = ResourceUtils.loadResourceAsString("json/users/response_requerid_fields.json");
        ValidationException validationException = new ValidationException("");
        mockMvc.perform(post(BASE_URI)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .content("{}")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(validationException.getStatus().value()))
                .andExpect(content().json(input));
    }

    @Test
    public void shouldReturnUserById() throws Exception {
        User user = UserStub.create();

        when(userService.findUserById(any())).thenReturn(user);

        mockMvc.perform(get(BASE_URI.concat("/1"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, TOKEN)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").doesNotExist())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.phone_number").value(user.getPhoneNumber()))
                .andExpect(jsonPath("$.company_name").value(user.getCompanyName()))
                .andExpect(jsonPath("$.company_address").value(user.getCompanyAddress()));
    }
}
