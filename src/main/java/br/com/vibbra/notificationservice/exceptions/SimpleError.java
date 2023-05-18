package br.com.vibbra.notificationservice.exceptions;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleError {

    private String message;
    private String code;
    private Object object;
    private List<SimpleError> details;
}