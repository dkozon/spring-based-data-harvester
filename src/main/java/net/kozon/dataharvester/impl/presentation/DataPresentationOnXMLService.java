package net.kozon.dataharvester.impl.presentation;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataharvester.dto.DataFromWebSource;
import net.kozon.dataharvester.interfaces.DataPresentation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class DataPresentationOnXMLService implements DataPresentation {

    @Override
    public boolean saveToFile(DataFromWebSource dataFromWebSource, String outputFile) {
        Document document;
        Element element;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder;
        try {
            documentBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.info("UsersXML: Error trying to instantiate DocumentBuilder.\n Log: %s", e);
            return false;
        }
        document = documentBuilder.newDocument();

        log.info(dataFromWebSource.getSourceName());
        Element rootElement = document.createElement(dataFromWebSource.getSourceName());

        for (String entry : dataFromWebSource.getSelectors()) {
            element = document.createElement("entity");
            element.appendChild(document.createTextNode(entry));
            rootElement.appendChild(element);
        }

        document.appendChild(rootElement);

        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(outputFile)));
        } catch (TransformerException | IOException exception) {
            log.info(exception.getMessage());
            return false;
        }
        return true;
    }

}
