package goldeneagle.carsdealer.utils.interfaces;

public interface Serializer {

    <T> String serialize(T t);

    <T> T deserialize(Class<T> clazz, String path);
}
