package br.com.vibbra.notificationservice.controller;

import br.com.vibbra.notificationservice.config.auth.Secured;
import br.com.vibbra.notificationservice.controller.request.user.CreateUserRequest;
import br.com.vibbra.notificationservice.controller.response.UserResponse;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.mapper.UserResposeMapper;
import br.com.vibbra.notificationservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/v1/users")
@RestController("UsuarioApiV1")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestão de usuarios")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Api responsavel por criar um usuario.")
    public UserResponse createUser(@RequestBody @Validated CreateUserRequest createUserRequest) {
        log.info("[UserController] Iniciando o processo de criação de usuario {}", createUserRequest);
        User user = userService.createUser(createUserRequest);
        log.info("[UserController] Finalizado o processo de criação de usuario");
        return UserResposeMapper.create(user);
    }

    @Secured
    @GetMapping("{id}")
    @Operation(
            summary = "Api responsavel por buscar um usuario pelo seu id.",
            description = "Para buscar o user voce deve ter um token valido."
    )
    public UserResponse findUserById(@RequestHeader String Authorization, @PathVariable Long id) {
        log.info("[FindUserById] Iniciando o buscar de usuario por ID {}", id);
        User user = userService.findUserById(id);
        log.info("[FindUserById] Finalizada o buscar de usuario por ID {}", user);
        return UserResposeMapper.create(user);
    }
}
