package net.kozon.dataanalyzer.interfaces;

import net.kozon.dataanalyzer.dto.DataFromWebSource;

import java.io.IOException;

public interface DataPresentation {

    boolean saveToFile(DataFromWebSource dataFromWebSource, String outputFile) throws IOException;

}
