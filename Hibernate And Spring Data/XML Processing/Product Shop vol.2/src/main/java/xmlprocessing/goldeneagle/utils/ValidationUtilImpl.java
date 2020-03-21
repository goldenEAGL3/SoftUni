package xmlprocessing.goldeneagle.utils;

import xmlprocessing.goldeneagle.utils.interfaces.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

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

    @Override
    public <E> String violationMessage(E entity) {
        StringBuilder sb = new StringBuilder();

        Set<ConstraintViolation<E>> validate = this.validator.validate(entity);

        for (ConstraintViolation<E> eConstraintViolation : validate) {
            sb.append(eConstraintViolation.getMessage()).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
