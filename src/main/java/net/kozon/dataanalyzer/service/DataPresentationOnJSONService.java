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

    private DataProvider dataProvider;

    public DataPresentationOnJSONService(DataProvider dataProvider) throws IOException {
        this.dataProvider = dataProvider;
    }

    public void presentDataBy(String outputFile, DataFromSource dataFromSource) throws IOException {
        saveToXML(outputFile, dataFromSource);
    }

    private void saveToXML(String outputFile, DataFromSource dataFromSource) throws IOException {
        JSONObject obj = new JSONObject();
        JSONArray element = new JSONArray();
        obj.put("page", dataFromSource.getSourceName());
        for (Map.Entry entry : dataFromSource.getElements().entrySet()) {
            element.add(entry.getValue().toString());
            obj.put(entry.getKey(), element);
        }

        try (FileWriter file = new FileWriter(outputFile)) {
            file.write(obj.toJSONString());
            log.info("Successfully Copied JSON Object to File...");
            log.info("JSON Object: " + obj);
        }
    }

}
