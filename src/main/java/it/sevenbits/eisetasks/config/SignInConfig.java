package it.sevenbits.eisetasks.config;

import it.sevenbits.eisetasks.web.controllers.auth.HeaderSignInController;
import it.sevenbits.eisetasks.web.service.signin.SignInService;
import it.sevenbits.eisetasks.web.security.JwtTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configures SignInService
 */
@Configuration
public class SignInConfig {

    /**
     * Provide certain controller for signing in
     * @param signInService is a service which provides sign in
     * @param jwtTokenService is a service for work with JWT token
     * @return configured sign in controller
     */
    @Bean
    public Object signInController(final SignInService signInService, final JwtTokenService jwtTokenService) {
        return new HeaderSignInController(signInService, jwtTokenService);
    }
}
