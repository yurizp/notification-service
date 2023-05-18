package br.com.vibbra.notificationservice.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.vibbra.notificationservice.controller.request.user.CreateUserRequest;
import br.com.vibbra.notificationservice.controller.request.user.CreateUserRequestStub;
import br.com.vibbra.notificationservice.db.UserRepository;
import br.com.vibbra.notificationservice.db.entity.UserEntity;
import br.com.vibbra.notificationservice.db.entity.UserEntityStub;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.exceptions.UserAlreadyExistsInDatabaseException;
import br.com.vibbra.notificationservice.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        final ObjectMapper objectMapper = new ObjectMapper();
        service = new UserService(repository, objectMapper);
    }

    @Test
    public void shouldReturnUserByEmailAndPassword() {
        String email = "test@valid.com";
        String password = "password";
        UserEntity entity = UserEntityStub.create();

        when(repository.findByEmailEqualsAndPasswordEquals(email, password)).thenReturn(Optional.of(entity));

        User response = service.findByEmailAndPassword(email, password);

        assertAll(
                () -> assertEquals(entity.getId(), response.getId()),
                () -> assertNotNull(response.getId()),
                () -> assertEquals(entity.getName(), response.getName()),
                () -> assertNotNull(response.getName()),
                () -> assertEquals(entity.getEmail(), response.getEmail()),
                () -> assertNotNull(response.getEmail()),
                () -> assertEquals(entity.getPassword(), response.getPassword()),
                () -> assertNotNull(response.getPassword()),
                () -> assertEquals(entity.getCompanyAddress(), response.getCompanyAddress()),
                () -> assertNotNull(response.getCompanyAddress()),
                () -> assertEquals(entity.getCompanyName(), response.getCompanyName()),
                () -> assertNotNull(response.getCompanyName()),
                () -> assertEquals(entity.getPhoneNumber(), response.getPhoneNumber()),
                () -> assertNotNull(response.getPhoneNumber()));
    }

    @Test
    public void shouldThrowErrorWhenNotFindUserByEmailAndPasswordInDb() {
        String email = "test@valid.com";
        String password = "password";

        when(repository.findByEmailEqualsAndPasswordEquals(email, password)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.findByEmailAndPassword(email, password));
    }

    @Test
    public void shouldCreateUserInDb() {
        CreateUserRequest reques = CreateUserRequestStub.create();
        UserEntity entity = UserEntityStub.create();

        when(repository.existsByEmail(reques.getEmail())).thenReturn(false);
        when(repository.save(any())).thenReturn(entity);

        User response = service.createUser(reques);

        assertAll(
                () -> assertEquals(entity.getId(), response.getId()),
                () -> assertNotNull(response.getId()),
                () -> assertEquals(entity.getName(), response.getName()),
                () -> assertNotNull(response.getName()),
                () -> assertEquals(entity.getEmail(), response.getEmail()),
                () -> assertNotNull(response.getEmail()),
                () -> assertEquals(entity.getPassword(), response.getPassword()),
                () -> assertNotNull(response.getPassword()),
                () -> assertEquals(entity.getCompanyAddress(), response.getCompanyAddress()),
                () -> assertNotNull(response.getCompanyAddress()),
                () -> assertEquals(entity.getCompanyName(), response.getCompanyName()),
                () -> assertNotNull(response.getCompanyName()),
                () -> assertEquals(entity.getPhoneNumber(), response.getPhoneNumber()),
                () -> assertNotNull(response.getPhoneNumber()));
    }

    @Test
    public void shouldReturnUserAlreadyExistsWhenEmailExistsInDb() {
        CreateUserRequest reques = CreateUserRequestStub.create();

        when(repository.existsByEmail(reques.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsInDatabaseException.class, () -> service.createUser(reques));
    }
}
