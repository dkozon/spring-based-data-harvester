package net.kozon.dataanalyzer.dto;

import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@RequiredArgsConstructor
@Value
public class DataFromWebSource {

    private String sourceName;
    private List<String> selectors;
}
