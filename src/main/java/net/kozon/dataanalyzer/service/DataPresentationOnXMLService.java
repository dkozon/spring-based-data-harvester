package net.kozon.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.pojo.DataFromSource;
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
import java.util.Map;

@Slf4j
public class DataPresentationOnXMLService {

    public void presentDataBy(DataFromSource dataFromSource, String outputFile) {
        saveToXML(dataFromSource, outputFile);
    }

    private void saveToXML(DataFromSource dataFromSource, String outputFile) {
        Document document;
        Element element;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            document = documentBuilder.newDocument();

            log.info(dataFromSource.getSourceName());
            Element rootEle = document.createElement(dataFromSource.getSourceName());

            for (String entry : dataFromSource.getSelectors()) {
                element = document.createElement("entity");
                element.appendChild(document.createTextNode(entry));
                rootEle.appendChild(element);
            }

            document.appendChild(rootEle);

            try {
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.METHOD, "xml");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream(outputFile)));

            } catch (TransformerException | IOException exception) {
                log.info(exception.getMessage());
            }
        } catch (ParserConfigurationException exception) {
            log.info("UsersXML: Error trying to instantiate DocumentBuilder " + exception);
        }
    }

}
