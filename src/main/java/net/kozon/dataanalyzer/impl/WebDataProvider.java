package net.kozon.dataanalyzer.impl;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;
import net.kozon.helpers.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WebDataProvider implements DataProvider {

    private String url;

    private Document document;

    public void setUrl(String url) throws IOException {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Document getDocument() throws IOException {
        return harvestRawData();
    }

    @Override
    public String extractDetailedData(String selector) throws IOException {
        log.info(harvestRawData().select(selector).get(0).text());
        return harvestRawData().select(selector).get(0).text();
    }

    @Override
    public String prepareSourceName() {
        String name = this.url.replaceAll("https://|http://", "page_").replace("/", "").replace(":", "");
        log.debug(name);
        return name;
    }

    public List<String> prepareListFromData() throws IOException {
        List<String> list = new ArrayList<>();
        for (String selector : Configuration.getInstance().selectors) {
            list.add(extractDetailedData(selector));
        }
        return list;
    }

    private Document harvestRawData() throws IOException {
        this.document = Jsoup.connect(url).get();
        log.info(this.document.title());
        return this.document;
    }
}
