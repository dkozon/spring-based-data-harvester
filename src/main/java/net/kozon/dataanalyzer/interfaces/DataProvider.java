package net.kozon.dataanalyzer.interfaces;

import org.jsoup.nodes.Document;

import java.io.IOException;

public interface DataProvider {
    Document getDocument() throws IOException;

    String extractDetailedData(String selector, String element) throws IOException;

    String extractSourceName() throws IOException;
}
