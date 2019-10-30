package org.softuni.residentevil.util;

import org.softuni.residentevil.domain.entity.enums.Creator;
import org.softuni.residentevil.util.annotations.CreatorValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CreatorValidator implements ConstraintValidator<CreatorValidation, Creator> {
    @Override
    public boolean isValid(Creator creator, ConstraintValidatorContext constraintValidatorContext) {
        if(creator == null) {
            return false;
        }

        return creator == Creator.Corp || creator == Creator.corp;
    }
}
