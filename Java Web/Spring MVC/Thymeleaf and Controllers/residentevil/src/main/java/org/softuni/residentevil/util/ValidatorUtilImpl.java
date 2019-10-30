package org.softuni.residentevil.util;


import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public class ValidatorUtilImpl implements ValidatorUtil {
    private Validator validator;

    @Autowired
    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> boolean isValid(T model) {
        return false;
    }
}
