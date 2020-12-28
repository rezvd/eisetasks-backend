package it.sevenbits.eisetasks.web.service.signin;

import it.sevenbits.eisetasks.core.model.User;
import it.sevenbits.eisetasks.core.repository.users.UsersRepository;
import it.sevenbits.eisetasks.web.model.auth.SignIn;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service for signing users in
 */
@Service
public class SignInService {

    private final UsersRepository users;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for SignInService
     * @param users is a user repository
     * @param passwordEncoder is an encoder for encoding passwords
     */
    public SignInService(final UsersRepository users, final PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Performs signing in
     * @param signIn contains information about user needed to sign in
     * @return authenticated user
     */
    public User signIn(final SignIn signIn) {
        User user = users.findByUserName(signIn.getUsername());

        if (user == null) {
            throw new SignInFailedException("User '" + signIn.getUsername() + "' not found");
        }

        if (!passwordEncoder.matches(signIn.getPassword(), user.getPassword())) {
            throw new SignInFailedException("Wrong password");
        }
        return user;
    }

}
