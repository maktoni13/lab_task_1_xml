package lab.model.parser;

import lab.model.entity.Catalog;
import lab.model.entity.Person;
import lab.model.entity.builder.PersonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class JDomTreeModelXmlParser
 * Implementation of Entity XML Parser for Catalog entity through DOM Tree Model
 * @author Anton Makukhin
 */
public class JDomTreeModelXmlParser implements EntityXmlParser<Catalog> {
    private static final Logger LOGGER = LoggerFactory.getLogger(JDomTreeModelXmlParser.class);
    private static final String CATALOG_TAG = "notebook";
    private static final String NOTEBOOK_TAG = "notebook";
    private static final String PERSON_TAG = "person";
    private static final String ID_ATTR = "id";
    private static final String NAME_ELEM = "name";
    private static final String ADDRESS_ELEM = "address";
    private static final String CASH_ELEM = "cash";
    private static final String EDUCATION_ELEM = "education";

    @Override
    public boolean createXmlFile(Catalog entity, String path) {
        File file = new File(path);
        return createXmlFile(entity, file);
    }

    @Override
    public Catalog parseXmlFile(String path) {
        File file = new File(path);
        return parseXmlFile(file);
    }

    @Override
    public Catalog parseXmlFile(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);
            Node root = document.getDocumentElement();
            root.normalize();
            Node notebookNode = ((Element) root).getElementsByTagName(NOTEBOOK_TAG).item(0);
            notebookNode.normalize();
            NodeList personNodeList = ((Element) notebookNode).getElementsByTagName(PERSON_TAG);
            Catalog catalog = new Catalog();
            for (int i = 0; i < personNodeList.getLength(); i++) {
                Node node = personNodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    PersonBuilder personBuilder = new PersonBuilder();
                    catalog.getNotebook().getPersonList().add(
                            personBuilder.buildId(Integer.parseInt(element.getAttribute(ID_ATTR)))
                                    .buildName(element.getElementsByTagName(NAME_ELEM)
                                            .item(0)
                                            .getTextContent())
                                    .buildAddress(element.getElementsByTagName(ADDRESS_ELEM)
                                            .item(0)
                                            .getTextContent())
                                    .buildCash(Integer.parseInt(element.getElementsByTagName(CASH_ELEM)
                                            .item(0)
                                            .getTextContent()))
                                    .buildEducation(element.getElementsByTagName(EDUCATION_ELEM)
                                            .item(0)
                                            .getTextContent()).build());
                }
            }
            return catalog;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            LOGGER.error(ERROR_XML_CREATING, e);
            return null;
        }
    }

    @Override
    public boolean createXmlFile(Catalog entity, File file) {

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement(CATALOG_TAG);
            doc.appendChild(rootElement);

            Element notebookElement = doc.createElement(NOTEBOOK_TAG);
            rootElement.appendChild(notebookElement);
            List<Person> personList = entity.getNotebook().getPersonList();

            for (Person person : personList) {
                Element personElement = doc.createElement(PERSON_TAG);
                notebookElement.appendChild(personElement);
                personElement.setAttribute(ID_ATTR, String.valueOf(person.getId()));
                addChildTextElement(doc, personElement, NAME_ELEM, person.getName());
                addChildTextElement(doc, personElement, ADDRESS_ELEM, person.getAddress());
                addChildTextElement(doc, personElement, CASH_ELEM, String.valueOf(person.getId()));
                addChildTextElement(doc, personElement, EDUCATION_ELEM, person.getEducation());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            return true;
        } catch (Exception e) {
            LOGGER.error(ERROR_XML_CREATING, e);
            return false;
        }
    }

    private void addChildTextElement(Document doc, Element element, String nameElem, String value) {
        Element childElement = doc.createElement(nameElem);
        childElement.appendChild(doc.createTextNode(value));
        element.appendChild(childElement);
    }
}
