package br.com.luciano.beerstore.resource.message.request;

import lombok.Data;

@Data
public class AuthorizationRequest {

    private String email;
    private String password;

}
