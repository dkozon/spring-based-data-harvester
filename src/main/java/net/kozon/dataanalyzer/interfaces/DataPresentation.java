package net.kozon.dataanalyzer.interfaces;

import net.kozon.dataanalyzer.pojo.DataFromSource;

import java.io.IOException;

public interface DataPresentation {

    void presentDataBy(DataFromSource dataFromSource, String outputFile) throws IOException;

    void saveToFile(DataFromSource dataFromSource, String outputFile) throws IOException;

}
