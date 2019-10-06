package alararestaurant.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XmlParserImpl implements XmlParser {
    @Override
    @SuppressWarnings("unchecked")
    public <E> E importXML(Class<E> objectClass, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return (E) unmarshaller.unmarshal(new File(path));
    }

    @Override
    public <E> void exportXML(E entity, String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(entity, new File(path));
    }

    public static class DateTimeAdapter extends XmlAdapter<String, Date> {
        private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        @Override
        public Date unmarshal(String v) throws Exception {
            return this.sdf.parse(v);
        }

        @Override
        public String marshal(Date v) throws Exception {
            return this.sdf.format(v);
        }
    }
}
