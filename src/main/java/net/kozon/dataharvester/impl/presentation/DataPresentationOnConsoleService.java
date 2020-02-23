package net.kozon.dataharvester.impl.presentation;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataharvester.dto.DataFromWebSource;
import net.kozon.dataharvester.interfaces.DataPresentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

@Slf4j
public class DataPresentationOnConsoleService implements DataPresentation {

    @Override
    public boolean saveToFile(DataFromWebSource dataFromWebSource, String outputFile) {
        try {
            Files.write(Paths.get(outputFile), Collections.singleton(
                    String.format("Source name: %s, element for harvesting: %s",
                            dataFromWebSource.getSourceName(),
                            dataFromWebSource.getSelectors())));
        } catch (IOException e) {
            log.error("IOException: %s%n", e);
            return false;
        }
        return presentDataOnConsole(dataFromWebSource);
    }

    private boolean presentDataOnConsole(DataFromWebSource dataFromWebSource) {
        log.info(String.format("Source name: %s, element for harvesting: %s", dataFromWebSource.getSourceName(), dataFromWebSource.getSelectors()));
        return true;
    }
}
