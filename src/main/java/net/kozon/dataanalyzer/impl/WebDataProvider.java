package net.kozon.dataanalyzer.impl;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class WebDataProvider implements DataProvider {

    private String url;
    private Document document;

    public void setUrl(String url) throws IOException {
        this.url = url;
    }

    @Override
    public Document getDocument() throws IOException {
        return harvestRawData();
    }

    @Override
    public String extractDetailedData(String selector, String element) throws IOException {
        return harvestRawData().select(selector).get(0).getElementsContainingText(element).text();
    }

    @Override
    public String extractSourceName() throws IOException {
        String name = this.url.replaceAll("https://|http://", "page_").replace("/", "");
        log.debug(name);
        return name;
    }

    private Document harvestRawData() throws IOException {
        this.document = Jsoup.connect(url).get();
        log.info(this.document.title());
        return this.document;
    }
}
