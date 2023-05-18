package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.auth.Secured;
import br.com.vibbra.notificationservice.controller.request.user.CreateUserRequest;
import br.com.vibbra.notificationservice.controller.response.UserResponse;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.mapper.UserResposeMapper;
import br.com.vibbra.notificationservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1/users")
@RestController("UsuarioApi")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Secured
    public UserResponse createUser(
            @RequestHeader String Authorization, @RequestBody @Validated CreateUserRequest createUserRequest) {
        log.info("[UserController] Iniciando o processo de criação de usuario {}", createUserRequest);
        User user = userService.createUser(createUserRequest);
        log.info("[UserController] Finalizado o processo de criação de usuario");
        return UserResposeMapper.create(user);
    }
}
