package net.kozon.dataanalyzer.service;

import lombok.extern.slf4j.Slf4j;
import net.kozon.dataanalyzer.interfaces.DataProvider;

import java.io.IOException;

@Slf4j
public class DataPresentationOnConsoleService {

    private DataProvider dataProvider;

    public DataPresentationOnConsoleService(DataProvider dataProvider) throws IOException {
        this.dataProvider = dataProvider;
    }

    public void presentAllDataFromSource() throws IOException {
        this.dataProvider.getDocument();
        log.info(dataProvider.getDocument().toString());
    }

    public void presentDataBy(String selector, String element) throws IOException {
        log.info(dataProvider.extractDetailedData(selector, element));
    }
}
