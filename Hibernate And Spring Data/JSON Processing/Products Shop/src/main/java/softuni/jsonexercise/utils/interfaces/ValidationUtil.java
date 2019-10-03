package softuni.jsonexercise.utils.interfaces;

public interface ValidationUtil {

    <E> boolean isValid(E entity);

    <E> String violationMessage(E entity) ;


}
