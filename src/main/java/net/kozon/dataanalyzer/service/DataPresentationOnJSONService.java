package net.kozon.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class DataPresentationOnJSONService {

    public void presentDataBy(DataFromSource dataFromSource, String outputFile) throws IOException {
        saveToXML(dataFromSource, outputFile);
    }

    private void saveToXML(DataFromSource dataFromSource, String outputFile) throws IOException {
        JSONObject root = new JSONObject();
        root.put("page", dataFromSource.getSourceName());
        JSONObject element = new JSONObject();
        for (Map.Entry entry : dataFromSource.getElements().entrySet()) {
            element.put("element", entry.getKey());
            element.put("value", entry.getValue());
        }
        root.put("source", element);

        try (FileWriter file = new FileWriter(outputFile)) {
            file.write(root.toJSONString());
            log.info("Successfully Copied JSON Object to File...");
            log.info("JSON Object: " + root);
        }
    }

}
