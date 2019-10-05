package softuni.workshop.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlParserImpl implements XmlParser{
    private JAXBContext jaxbContext;

    @Override
    @SuppressWarnings("unchecked")
    public <E> E importXML(Class<E> objectClass, String path) throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(objectClass);
        Unmarshaller unmarshaller = this.jaxbContext.createUnmarshaller();

        return (E) unmarshaller.unmarshal(new File(path));
    }

    @Override
    public <E> void exportXML(E entity, String path) throws JAXBException {
        this.jaxbContext = JAXBContext.newInstance(entity.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(entity, new File(path));
    }
}
