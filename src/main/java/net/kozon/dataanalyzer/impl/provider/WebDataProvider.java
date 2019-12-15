package net.kozon.dataanalyzer.impl.provider;

import lombok.Getter;
import lombok.Setter;
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
@Setter
@Getter
@Component
public class WebDataProvider implements DataProvider {

    private String url;

    @Override
    public Document getDocument() throws IOException {
        return harvestRawData();
    }

    @Override
    public String extractDetailedData(String selector) throws IOException {
        String rawData = harvestRawData().select(selector).get(0).text();
        log.info(rawData);
        return rawData;
    }

    @Override
    public String prepareSourceName() {
        String name = this.url.replaceAll("https://|http://", "page_")
                .replace("/", "_")
                .replace(":", "_");
        log.debug(name);
        return name;
    }

    @Override
    public List<String> prepareListFromData(List<String> selectors) throws IOException {
        List<String> list = new ArrayList<>();
        for (String selector : selectors) {
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


