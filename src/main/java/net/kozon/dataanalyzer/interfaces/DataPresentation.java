package net.kozon.dataanalyzer.interfaces;

import net.kozon.dataanalyzer.pojo.DataFromSource;

import java.io.IOException;

public interface DataPresentation {

    boolean saveToFile(DataFromSource dataFromSource, String outputFile) throws IOException;

}
