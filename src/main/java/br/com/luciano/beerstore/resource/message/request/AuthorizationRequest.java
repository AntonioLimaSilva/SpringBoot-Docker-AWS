package br.com.luciano.beerstore.resource.message.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthorizationRequest {

    @NotBlank(message = "auth-1")
    @Email(message = "auth-2")
    @Size(min = 6, max = 100, message = "auth-3")
    private String email;
    @NotBlank(message = "auth-4")
    @Size(min = 4, max = 100, message = "auth-5")
    private String password;

}
