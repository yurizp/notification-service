package br.com.yurizp.notificationservice.service;

import br.com.yurizp.notificationservice.controller.request.user.CreateUserRequest;
import br.com.yurizp.notificationservice.db.UserRepository;
import br.com.yurizp.notificationservice.db.entity.UserEntity;
import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.notificationservice.exceptions.UserAlreadyExistsInDatabaseException;
import br.com.yurizp.notificationservice.exceptions.UserNotFoundException;
import br.com.yurizp.notificationservice.mapper.UserEntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public User findUserById(final Long id) {
        log.info("[Buscar usuario] Iniciando a busca de um usuario por ID {}", id);
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        User userDto = objectMapper.convertValue(user, User.class);
        log.info("[Buscar usuario] Finalizada a busca de usuario na base {}", userDto);
        return userDto;
    }

    public User createUser(CreateUserRequest createUserRequest) {
        log.info("[Criar usuario] Iniciando a etapa de criar usuario na base {}", createUserRequest);
        validateIfEmailAlreadyExistsInDatabase(createUserRequest.getEmail());
        UserEntity userEntity = UserEntityMapper.create(createUserRequest);
        UserEntity user = userRepository.save(userEntity);
        User userDto = objectMapper.convertValue(user, User.class);
        log.info("[Criar usuario] Finalizada a etapa de criar usuario na base {}", userDto);
        return userDto;
    }

    public User findByEmailAndPassword(final String email, final String password) {
        Optional<UserEntity> emailPresent = userRepository.findByEmailEqualsAndPasswordEquals(email, password);
        if (emailPresent.isPresent()) {
            log.info("[Busca por email] Usuario encontrado na base email {}", email);
            return emailPresent
                    .map(user -> objectMapper.convertValue(user, User.class))
                    .get();
        }
        log.error("[Busca por email] Usuario não encontrado na base email {}", email);
        throw new UserNotFoundException();
    }

    private void validateIfEmailAlreadyExistsInDatabase(String email) {
        if (userRepository.existsByEmail(email)) {
            log.error("[Criar usuario] E-mail ja existe na base de dados {}", email);
            throw new UserAlreadyExistsInDatabaseException();
        }
        log.info("[Criar usuario] E-mail não existe na base de dados {}", email);
    }
}
