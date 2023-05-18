package br.com.vibbra.notificationservice.service;

import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.dto.User;
import br.com.vibbra.notificationservice.mapper.ClaimsMapper;
import br.com.vibbra.notificationservice.mapper.JwtTokenMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    private static String SECRET_KEY = "59703373367639792442264528482B4D6251655468576D5A7134743777217A25";

    public JwtToken generateToken(final User user) {
        log.info("[Gerar token] Gerando o token do usuario {}", user);
        Date expiration = Date.from(Instant.now().plus(30, ChronoUnit.MINUTES));
        Date issuedAt = new Date(System.currentTimeMillis());

        String token = Jwts.builder()
                .setClaims(ClaimsMapper.create(user))
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setSubject(user.getEmail())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        JwtToken jwtToken = JwtTokenMapper.create(token, user, expiration, issuedAt);
        log.info("[Gerar token] Gerado com sucesso o token do usuario {}", jwtToken);
        return jwtToken;
    }

    public JwtToken decodeToken(final String token) {
        log.info("[Abrir toke] Inciando o fluxo de desempacotamento de token");
        Claims claims = extractAllClaims(token);
        JwtToken userToken = JwtTokenMapper.create(claims, token);
        log.info("[Abrir toke] Desempacotamento de token finalizado com sucesso user {}", userToken);
        return userToken;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}
