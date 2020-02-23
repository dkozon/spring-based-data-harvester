package net.kozon.dataharvester.interfaces;

import net.kozon.dataharvester.dto.DataFromWebSource;

import java.io.IOException;

@FunctionalInterface
public interface DataPresentation {

    boolean saveToFile(DataFromWebSource dataFromWebSource, String outputFile) throws IOException;

}
