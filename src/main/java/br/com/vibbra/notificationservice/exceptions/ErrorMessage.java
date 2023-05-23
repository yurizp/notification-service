package br.com.vibbra.notificationservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    EXPIRED_TOKEN("NTS-001", "Token expirado."),
    INVALID_TOKEN("NTS-002", "Token enviado não é valido ou é nulo."),
    USER_ALREADY_EXISTS_IN_DATABASE("NTS-003", "Usuario ja cadastrado na base de dados."),
    VALIDATION_ERROR_DEFAULT("NTS-004", "Campo(s) com formato(s) invalido(s)."),
    USER_NOT_FOUND("NTS-005", "Não foi possivel encontrar o usuario."),
    APP_NOT_FOUND("NTS-006", "Não foi possivel encontrar o app."),
    CHANNEL_NOT_IMPLEMENTED("NTS-007", "Não temos esse canal em funcionamento ainda."),
    SETTINGS_NOT_FOUND("NTS-008", "Não foram encontradas configurações para esse canal."),
    INVALID_BODY("NTS-009", "O body enviado nao corresponde ao esperado."),
    INTERNAL_SERVER_ERROR("NTS-999", "Ocorreu um erro interno.");

    private String code;
    private String message;
}
