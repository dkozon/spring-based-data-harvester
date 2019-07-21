package net.kozon.dataanalyzer.impl.provider;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;
import net.kozon.helpers.Configuration;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class WebDataProvider implements DataProvider {

    public String getUrl() {
        return url;
    }
    private String url;

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public List<String> prepareListFromData() throws IOException {
        List<String> list = new ArrayList<>();
        for (String selector : Configuration.getInstance().selectors) {
            list.add(extractDetailedData(selector));
        }
        return list;
    }

    private Document harvestRawData() throws IOException {
        Document document = Jsoup.connect(url).get();
        log.info(document.title());
        return document;
    }
}
