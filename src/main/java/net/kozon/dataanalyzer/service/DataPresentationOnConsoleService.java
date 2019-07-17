package net.kozon.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class DataPresentationOnConsoleService {

    public void presentDataBy(DataFromSource dataFromSource) {
        for (Map.Entry entry : dataFromSource.getElements().entrySet()) {
            log.info(dataFromSource.getSourceName() + " " + entry.getKey().toString());
            log.info(dataFromSource.getSourceName() + " " + entry.getValue().toString());
        }
    }
}
