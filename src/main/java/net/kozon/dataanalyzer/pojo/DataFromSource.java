package net.kozon.dataanalyzer.pojo;

import java.util.Map;

public class DataFromSource {

    private String sourceName;
    private Map<String, String> elements;

    public DataFromSource(String sourceName, Map<String, String> elements) {
        this.sourceName = sourceName;
        this.elements = elements;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Map<String, String> getElements() {
        return elements;
    }

    public void setElements(Map<String, String> elements) {
        this.elements = elements;
    }
}
