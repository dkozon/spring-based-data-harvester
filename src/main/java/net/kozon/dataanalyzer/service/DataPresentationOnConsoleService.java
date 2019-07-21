package net.kozon.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.pojo.DataFromSource;

@Slf4j
public class DataPresentationOnConsoleService {

    public void presentDataBy(DataFromSource dataFromSource) {
        for (String element : dataFromSource.getSelectors()) {
            log.info(dataFromSource.getSourceName() + " " + element);
        }
    }
}
