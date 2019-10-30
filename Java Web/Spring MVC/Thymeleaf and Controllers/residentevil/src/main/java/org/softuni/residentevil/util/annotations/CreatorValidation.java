package org.softuni.residentevil.util.annotations;

import org.softuni.residentevil.util.CreatorValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CreatorValidator.class)
public @interface CreatorValidation {
    String message() default "Creator must be either Corp or corp.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
