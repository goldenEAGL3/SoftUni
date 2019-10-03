package softuni.jsonexercise.utils.serialize;

public interface Serializer {

    <T> String serialize(T t);

    <T> T deserialize(Class<T> clazz, String path);


}
