package net.kozon.dataanalyzer.impl.presentation;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.dto.DataFromWebSource;
import net.kozon.dataanalyzer.interfaces.DataPresentation;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class DataPresentationOnJSONService implements DataPresentation {

    @Override
    public boolean saveToFile(DataFromWebSource dataFromWebSource, String outputFile) {
        JSONObject root = new JSONObject();

        root.put("page", dataFromWebSource.getSourceName());
        JSONObject element = new JSONObject();
        for (String selector : dataFromWebSource.getSelectors()) {
            element.put("element", selector);
        }
        root.put("source", element);

        try {
            Files.write(Paths.get(outputFile), root.toString().getBytes());
            log.info("Successfully Copied JSON Object to File...");
            log.info("JSON Object: " + root);
        } catch (IOException e) {
            log.error("Wrong output file. Problem with saving data. Log: %s", e);
            return false;
        }

        return true;
    }

}
