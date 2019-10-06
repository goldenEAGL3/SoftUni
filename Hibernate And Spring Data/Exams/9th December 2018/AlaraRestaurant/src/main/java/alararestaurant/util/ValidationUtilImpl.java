package alararestaurant.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {
    private final Validator validator;

    @Autowired
    public ValidationUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return this.validator.validate(entity).size() == 0;
    }

//    @Override
//    public <E> String getViolationMessage(E entity) {
//        StringBuilder sb = new StringBuilder();
//
//        this.validator.validate(entity)
//                .forEach(violation -> sb.append(violation.getMessage())
//                        .append(System.lineSeparator()));
//
//        return sb.toString().trim();
//    }
}
