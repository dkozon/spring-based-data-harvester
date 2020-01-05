package net.kozon.utils;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.impl.provider.WebDataProvider;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class WebXPathGenerator {

    public String xpathGenerator(String url) {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(url);

        StringBuilder stringBuilder = new StringBuilder();

        try {
            Document doc = webDataProvider.getDocument();

            Elements allElements = doc.select("*");
            if (allElements != null && allElements.size() > 0) {
                for (int i = 0; i < allElements.size(); i++) {
                    stringBuilder.append(allElements.get(i).cssSelector())
                            .append(",")
                            .append(allElements.get(i).tag().getName())
                            .append("\n");
                    log.info("Root: {}\n", allElements.get(i).cssSelector());
                    for (Element element : allElements) {
                        stringBuilder.append(element.cssSelector())
                                .append(",")
                                .append(element.tag().getName())
                                .append("\n");
                        log.info("Child Text (if available): {}\n " +
                                "Child: {}\n\n", element.text(), element.cssSelector());
                    }
                }
            }
            System.out.println("Done");
        } catch (IOException e) {
            log.error("Document not found!", e);
        }
        return stringBuilder.toString();
    }

    public void saveToCSV(String saveFilePath, String url) throws IOException {
        Files.write(Paths.get(saveFilePath), xpathGenerator(url).getBytes());
    }

}
