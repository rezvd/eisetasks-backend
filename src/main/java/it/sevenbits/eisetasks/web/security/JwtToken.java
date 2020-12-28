package it.sevenbits.eisetasks.web.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Authorization which holds unparsed and unverified JWT token
 */
public class JwtToken extends AbstractAuthenticationToken {

    private final String token;

    /**
     * Conctructor for JwtToken
     * @param token is token in string type
     */
    public JwtToken(final String token) {
        super(null);
        this.token = token;
        super.setAuthenticated(false);
    }

    @Override
    public void setAuthenticated(final boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted");
        }
        super.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

}
