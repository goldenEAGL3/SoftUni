package softuni.workshop.util;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidatorUtil {

    <E> boolean isValid(E entity);

    <E> String getViolationMessages(E entity);
}
