package it.sevenbits.eisetasks.web.controllers.auth;

import it.sevenbits.eisetasks.core.model.User;
import it.sevenbits.eisetasks.web.service.signin.SignInService;
import it.sevenbits.eisetasks.web.model.auth.SignIn;
import it.sevenbits.eisetasks.web.model.auth.Token;
import it.sevenbits.eisetasks.web.security.JwtTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Performs sign in action
 */
@RequestMapping("/signin")
public class HeaderSignInController {

    private final SignInService signInService;
    private final JwtTokenService tokenService;

    /**
     * Constructor for HeaderSignInController
     * @param signInService is a service which provides log in
     * @param tokenService is a service for work with token
     */
    public HeaderSignInController(final SignInService signInService, final JwtTokenService tokenService) {
        this.signInService = signInService;
        this.tokenService = tokenService;
    }

    /**
     * Creates token with user information
     * @param signIn contains user information needed to sign in
     * @return token with user information
     */
    @PostMapping
    @ResponseBody
    public Token create(@RequestBody final SignIn signIn) {
        User user = signInService.signIn(signIn);
        String token = tokenService.createToken(user);
        return new Token(token);
    }
}