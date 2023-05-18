package br.com.vibbra.notificationservice.config.auth.filter;

import br.com.vibbra.notificationservice.config.auth.Secured;
import br.com.vibbra.notificationservice.dto.JwtToken;
import br.com.vibbra.notificationservice.exceptions.ExpiredTokenException;
import br.com.vibbra.notificationservice.exceptions.HttpException;
import br.com.vibbra.notificationservice.exceptions.InternalServerErrorException;
import br.com.vibbra.notificationservice.exceptions.InvalidTokenException;
import br.com.vibbra.notificationservice.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
@RequiredArgsConstructor
public class SecurityWebFilter extends OncePerRequestFilter {

    private final ServletContext servletContext;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;
    private final JwtService authService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain chain)
            throws IOException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            Optional<HandlerExecutionChain> handlerOptional =
                    Optional.ofNullable(this.requestMappingHandlerMapping.getHandler(req));

            if (!handlerOptional.isPresent()) {
                chain.doFilter(req, res);
                return;
            }

            Optional<Secured> securedOptional = Optional.ofNullable(
                            (HandlerMethod) handlerOptional.get().getHandler())
                    .map(it -> it.getMethodAnnotation(Secured.class));

            if (!securedOptional.isPresent()) {
                chain.doFilter(req, res);
                return;
            }

            Secured secured = securedOptional.get();
            String token = req.getHeader("Authorization");
            if (StringUtils.isBlank(token) || !StringUtils.startsWithIgnoreCase(token, "Bearer")) {
                throw new InvalidTokenException();
            }
            String tokenCuted = StringUtils.substring(token, 7);

            JwtToken userToken = authService.decodeToken(tokenCuted);
            servletContext.setAttribute("jwtToken", userToken);

            chain.doFilter(req, res);
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado", e);
            ExpiredTokenException error = new ExpiredTokenException();
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().append(objectMapper.writeValueAsString(error.getError()));
            res.setStatus(error.getStatus().value());
        } catch (HttpException e) {
            logger.error("Erro ao validar o token", e);
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().append(objectMapper.writeValueAsString(e.getError()));
            res.setStatus(e.getStatus().value());
        } catch (Exception e) {
            logger.error("Erro ao validar o token", e);
            InternalServerErrorException error = new InternalServerErrorException(e, e.getMessage());
            res.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
            res.getWriter().append(objectMapper.writeValueAsString(error.getError()));
            res.setStatus(error.getStatus().value());
        }
    }
}
