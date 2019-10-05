package softuni.workshop.util;

import javax.xml.bind.JAXBException;

public interface XmlParser {

    <E> E importXML(Class<E> objectClass, String path) throws JAXBException;

    <E> void exportXML(E entity,String path) throws JAXBException;
}
