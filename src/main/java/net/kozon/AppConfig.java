package net.kozon;

import net.kozon.dataanalyzer.impl.WebDataProvider;
import net.kozon.dataanalyzer.service.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.service.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.service.DataPresentationOnXMLService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class AppConfig {

    @Bean
    public WebDataProvider webDataProvider() {
        return new WebDataProvider();
    }

    @Bean
    public DataPresentationOnConsoleService dataPresentationOnConsoleService() throws IOException {
        return new DataPresentationOnConsoleService(webDataProvider());
    }

    @Bean
    public DataPresentationOnXMLService dataPresentationOnXMLService() throws IOException {
        return new DataPresentationOnXMLService(webDataProvider());
    }

    @Bean
    public DataPresentationOnJSONService dataPresentationOnJSONService() throws IOException {
        return new DataPresentationOnJSONService(webDataProvider());
    }

}
