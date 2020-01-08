package org.softuni.productshop.util;

public interface ValidationService {

    boolean isUsernameTaken(String username);

    boolean isEmailInvalid(String email);

    boolean isEmailTaken(String email);

}
