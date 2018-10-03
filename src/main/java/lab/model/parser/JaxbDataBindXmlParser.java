package lab.model.parser;

import lab.model.entity.Catalog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Class JaxbDataBindXmlParser
 * Implementation of Entity XML Parser for Catalog entity through JAXB Data bind
 * @author Anton Makukhin
 */
public class JaxbDataBindXmlParser implements EntityXmlParser<Catalog> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JaxbDataBindXmlParser.class);

    @Override
    public boolean createXmlFile(Catalog entity, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entity, file);
        } catch (JAXBException e){
            LOGGER.error(ERROR_XML_CREATING, e);
            return false;
        }
        return true;
    }

    @Override
    public boolean createXmlFile(Catalog entity, String path) {
        File file = new File(path);
        return createXmlFile(entity, file);
    }

    @Override
    public Catalog parseXmlFile(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Catalog) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e){
            LOGGER.error(ERROR_XML_PARSING, e);
            return null;
        }
    }

    @Override
    public Catalog parseXmlFile(String path) {
        File file = new File(path);
        return parseXmlFile(file);
    }
}
