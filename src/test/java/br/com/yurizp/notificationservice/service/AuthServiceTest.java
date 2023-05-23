package br.com.yurizp.notificationservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.yurizp.notificationservice.controller.request.authentication.AuthRequest;
import br.com.yurizp.notificationservice.controller.request.authentication.AuthRequestStub;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.yurizp.notificationservice.dto.User;
import br.com.yurizp.notificationservice.dto.UserStub;
import br.com.yurizp.notificationservice.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    private AuthService service;

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Test
    public void shouldGenerateToken() {
        AuthRequest auth = AuthRequestStub.create();
        User user = UserStub.create();
        JwtToken token = JwtToken.builder().build();

        when(userService.findByEmailAndPassword(auth.getEmail(), auth.getPassword()))
                .thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);

        JwtToken jwtToken = service.generateToken(auth);

        assertEquals(token, jwtToken);
        verify(userService).findByEmailAndPassword(auth.getEmail(), auth.getPassword());
        verify(jwtService).generateToken(user);
    }

    @Test
    public void shouldReturnErrorWhenSomeMethodThrows() {
        AuthRequest auth = AuthRequestStub.create();

        when(userService.findByEmailAndPassword(auth.getEmail(), auth.getPassword()))
                .thenThrow(new UserNotFoundException());

        assertThrows(UserNotFoundException.class, () -> service.generateToken(auth));

        verify(userService).findByEmailAndPassword(auth.getEmail(), auth.getPassword());
        verify(jwtService, never()).generateToken(any());
    }
}
