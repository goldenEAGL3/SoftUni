package utils;

public interface ValidationService {

    boolean isUsernameTaken(String username);

    boolean isEmailInvalid(String email);

    boolean isPasswordIncorrect(String password, String confirmPassword);
}
