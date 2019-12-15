package net.kozon.helpers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Configuration {

    public String source;
    public List<String> selectors;

    private static final String CONFIG_FILE = "harvesterConfig.json";
    private static Configuration configuration;

    private Configuration() {
    }

    public static Configuration getInstance() throws IOException, URISyntaxException {
        BufferedReader bufferedReader = Files.newBufferedReader(getPath());
        JsonReader jsonReader = new JsonReader(bufferedReader);
        if (configuration == null) {
            synchronized (Configuration.class) {
                if (configuration == null) {
                    configuration = new Gson().fromJson(jsonReader, Configuration.class);
                }
            }
        }
        return configuration;
    }

    private static Path getPath() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource(CONFIG_FILE).toURI();
        String mainPath = Paths.get(uri).toString();
        return Paths.get(mainPath);
    }

}
