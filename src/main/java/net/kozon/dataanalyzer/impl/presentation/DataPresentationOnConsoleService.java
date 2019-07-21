package net.kozon.dataanalyzer.impl.presentation;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataPresentation;
import net.kozon.dataanalyzer.pojo.DataFromSource;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Slf4j
public class DataPresentationOnConsoleService implements DataPresentation {

    @Override
    public boolean saveToFile(DataFromSource dataFromSource, String outputFile) {
        try (FileWriter writer = new FileWriter(outputFile); BufferedWriter bw = new BufferedWriter(writer)) {
            for (String element : dataFromSource.getSelectors()) {
                bw.write(String.format("Source name: %s, element for harvesting: %s", dataFromSource.getSourceName(), element));
            }
        } catch (IOException e) {
            log.error("IOException: %s%n", e);
            return false;
        }
        return presentDataOnConsole(dataFromSource);
    }

    private boolean presentDataOnConsole(DataFromSource dataFromSource) {
        for (String element : dataFromSource.getSelectors()) {
            log.info(String.format("Source name: %s, element for harvesting: %s", dataFromSource.getSourceName(), element));
        }
        return true;
    }
}