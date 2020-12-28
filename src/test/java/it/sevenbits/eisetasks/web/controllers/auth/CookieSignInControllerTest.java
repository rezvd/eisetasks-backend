package it.sevenbits.eisetasks.web.controllers.auth;

import it.sevenbits.eisetasks.core.model.User;
import it.sevenbits.eisetasks.web.model.auth.SignIn;
import it.sevenbits.eisetasks.web.security.JwtTokenService;
import it.sevenbits.eisetasks.web.service.signin.SignInService;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.time.Duration;

import static org.mockito.Mockito.*;

public class CookieSignInControllerTest {

    private SignInService signInService;
    private JwtTokenService tokenService;
    private CookieSignInController cookieSignInController;

    @Before
    public void setup() {
        signInService = mock(SignInService.class);
        tokenService = mock(JwtTokenService.class);
        cookieSignInController = new CookieSignInController(signInService, tokenService);
    }

    @Test
    public void testCreate() {
        String token = "token";
        SignIn signIn = new SignIn("username", "password");
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        when(tokenService.getTokenExpiredIn()).thenReturn(Duration.ofSeconds(30));
        when(tokenService.createToken(any(User.class))).thenReturn(token);
        cookieSignInController.create(signIn, httpServletResponse);

        verify(signInService, times(1)).signIn(eq(signIn));
        verify(httpServletResponse, times(1)).addCookie(any(Cookie.class));
    }
}