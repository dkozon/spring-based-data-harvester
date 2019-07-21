package net.kozon.dataanalyzer.impl.presentation;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataPresentation;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class DataPresentationOnJSONService implements DataPresentation {

    @Override
    public boolean saveToFile(DataFromSource dataFromSource, String outputFile) {
        JSONObject root = new JSONObject();
        root.put("page", dataFromSource.getSourceName());
        JSONObject element = new JSONObject();
        for (String selector : dataFromSource.getSelectors()) {
            element.put("element", selector);
        }
        root.put("source", element);

        try (FileWriter file = new FileWriter(outputFile)) {
            file.write(root.toJSONString());
            log.info("Successfully Copied JSON Object to File...");
            log.info("JSON Object: " + root);
        } catch (IOException e) {
            log.error("Wrong output file. Problem with saving data. Log: %s", e);
            return false;
        }
        return true;
    }

}
