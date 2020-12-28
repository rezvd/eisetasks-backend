package it.sevenbits.eisetasks.web.controllers.auth;

import it.sevenbits.eisetasks.core.model.User;
import it.sevenbits.eisetasks.web.model.auth.SignUp;
import it.sevenbits.eisetasks.web.model.auth.Token;
import it.sevenbits.eisetasks.web.security.JwtTokenService;
import it.sevenbits.eisetasks.web.service.signup.SignUpService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Performs sign up action
 */
@RequestMapping("/signup")
public class HeaderSignUpController {

    private final SignUpService signUpService;
    private final JwtTokenService tokenService;

    /**
     * Constructor for HeaderSignUpController
     * @param signUpService is a service which provides sign up
     * @param tokenService is a service for work with token
     */
    public HeaderSignUpController(final SignUpService signUpService, final JwtTokenService tokenService) {
        this.signUpService = signUpService;
        this.tokenService = tokenService;
    }

    /**
     * Creates token with user information
     * @param signUp contains user information needed to sign in
     * @return token with user information
     */
    @PostMapping
    @ResponseBody
    public Token create(@RequestBody final SignUp signUp) {
        User user = signUpService.signUp(signUp);
        String token = tokenService.createToken(user);
        return new Token(token);
    }
}