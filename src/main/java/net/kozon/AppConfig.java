package net.kozon;

import net.kozon.dataanalyzer.impl.WebDataProvider;
import net.kozon.dataanalyzer.service.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.service.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.service.DataPresentationOnXMLService;
import net.kozon.dataanalyzer.service.DataPresentationServiceExploitable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public WebDataProvider webDataProvider() {
        return new WebDataProvider();
    }

    @Bean
    public DataPresentationOnConsoleService dataPresentationOnConsoleService() {
        return new DataPresentationOnConsoleService();
    }

    @Bean
    public DataPresentationOnXMLService dataPresentationOnXMLService() {
        return new DataPresentationOnXMLService();
    }

    @Bean
    public DataPresentationOnJSONService dataPresentationOnJSONService() {
        return new DataPresentationOnJSONService();
    }

    @Bean
    public DataPresentationServiceExploitable dataPresentationServiceExploitable() {
        return new DataPresentationServiceExploitable();
    }

}
