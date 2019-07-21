package net.kozon.dataanalyzer.pojo;

import java.util.List;

public class DataFromSource {

    private String sourceName;
    private List<String> selectors;

    public DataFromSource(String sourceName, List<String> selectors) {
        this.sourceName = sourceName;
        this.selectors = selectors;
    }

    public String getSourceName() {
        return sourceName;
    }

    public List<String> getSelectors() {
        return selectors;
    }
}
