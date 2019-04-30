package br.com.luciano.beerstore.resource.message.response;

public class JwtResponse {

    private String token;
    private String type = "Bearer";

    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

    public String getAccessToken() {
        return token;
    }

    public String getTokenType() {
        return type;
    }

}
