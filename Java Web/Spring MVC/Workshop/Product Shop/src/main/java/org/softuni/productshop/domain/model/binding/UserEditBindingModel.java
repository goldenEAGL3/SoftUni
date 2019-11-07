package org.softuni.productshop.domain.model.binding;

import org.softuni.productshop.util.annotations.FieldMatch;

import javax.validation.constraints.NotEmpty;

@FieldMatch(first = "newPassword", second = "confirmNewPassword", message = "Passwords must match")
public class UserEditBindingModel {


    @NotEmpty(message = "Email cannot be empty!")
    private String email;

    @NotEmpty(message = "Old password cannot be empty!")
    private String oldPassword;

    @NotEmpty(message = "New password cannot be empty!")
    private String password;

    @NotEmpty(message = "Confirm password cannot be empty!")
    private String confirmPassword;


    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
