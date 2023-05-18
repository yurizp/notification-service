package br.com.vibbra.notificationservice.service;

import br.com.vibbra.notificationservice.controller.request.authentication.AuthRequest;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserService userService;

    public JwtToken generateToken(AuthRequest authRequest) {
        User user = userService.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
        return jwtService.generateToken(user);
    }

    public JwtToken decodeToken(final String token) {
        return jwtService.decodeToken(token);
    }
}
