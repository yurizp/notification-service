package br.com.yurizp.notificationservice.controller.response;

import br.com.yurizp.commons.tostring.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @Email
    @JsonProperty("email")
    private String email;

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
