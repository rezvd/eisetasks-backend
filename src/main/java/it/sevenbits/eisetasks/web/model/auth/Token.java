package it.sevenbits.eisetasks.web.model.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model to send token.
 */
public class Token {

    private final String token;

    /**
     * JSON constructor for Token
     * @param token is a token in string type
     */
    @JsonCreator
    public Token(@JsonProperty("token") final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
