package br.com.vibbra.notificationservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    EXPIRED_TOKEN("NTS-001", "Token expirado."),
    INVALID_TOKEN("NTS-002", "Token enviado não é valido ou é nulo.");

    private String code;
    private String message;
}
