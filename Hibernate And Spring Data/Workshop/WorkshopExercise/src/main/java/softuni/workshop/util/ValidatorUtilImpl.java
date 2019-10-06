package softuni.workshop.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtilImpl implements ValidatorUtil  {

    private final Validator validator;

    @Autowired
    public ValidatorUtilImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <E> boolean isValid(E entity) {
        return  this.validator.validate(entity).size() == 0;
    }

    @Override
    public <E> String getViolationMessages(E entity) {
        StringBuilder sb = new StringBuilder();
        this.validator.validate(entity).forEach(violation -> sb.append(violation.getMessage()).append(System.lineSeparator()));
        return sb.toString().trim();
    }
}
