package net.kozon.helpers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Configuration {

    public String source;
    public List<String> selectors;

    private Configuration() {
    }

    public static Configuration getInstance() throws IOException {
        Path path = Paths.get("src\\main\\resources\\harvesterConfig.json");
        BufferedReader bufferedReader = Files.newBufferedReader(path);
        JsonReader jsonReader = new JsonReader(bufferedReader);
        Gson gson = new Gson();
        return gson.fromJson(jsonReader, Configuration.class);
    }

}
