package utils;

import repository.UserRepository;
import service.UserService;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationServiceImpl implements ValidationService {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final UserRepository userRepository;

    @Inject
    public ValidationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return this.userRepository.findByUsername(username) != null;
    }

    @Override
    public boolean isEmailInvalid(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return !matcher.find();
    }

    @Override
    public boolean isPasswordIncorrect(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }
}
