package org.softuni.residentevil.util;

import org.softuni.residentevil.util.annotations.DateValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidator implements ConstraintValidator<DateValidation, LocalDate> {
    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        return date.isBefore(today);
    }
}
