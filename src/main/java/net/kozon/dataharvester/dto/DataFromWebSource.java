package net.kozon.dataharvester.dto;

import lombok.Value;

import java.util.List;

@Value
public class DataFromWebSource {

    private String sourceName;
    private List<String> selectors;
}
