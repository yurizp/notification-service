package br.com.yurizp.notificationservice.controller.request.user;

import br.com.yurizp.commons.tostring.Objects;
import br.com.yurizp.commons.tostring.ToStringIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class CreateUserRequest {

    @Email
    @JsonProperty("email")
    private String email;

    @NotBlank
    @JsonProperty("password")
    @ToStringIgnore
    private String password;

    @NotBlank
    @JsonProperty("name")
    private String name;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("company_address")
    private String companyAddress;

    @Override
    public String toString() {
        return Objects.toString(this);
    }
}
