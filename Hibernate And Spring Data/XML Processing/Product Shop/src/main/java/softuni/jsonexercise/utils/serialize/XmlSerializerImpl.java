package softuni.jsonexercise.utils.serialize;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;

public class XmlSerializerImpl implements Serializer{
    private JAXBContext jaxbContext;

    @Override
    public <T> String serialize(T t) {

        this.jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(t, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return String.format("Could not serialize Object of type %s", t.getClass());
        }
    }

    @Override
    public <T> T deserialize(Class<T> clazz, String path) {

        try {
            this.jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            InputStream inputStream = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            T t = (T) unmarshaller.unmarshal(reader);
            return t;
        } catch (JAXBException e) {
            e.printStackTrace();

        }
        return null;
    }
}
