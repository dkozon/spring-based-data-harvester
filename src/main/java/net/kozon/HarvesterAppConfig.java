package net.kozon;

import net.kozon.dataharvester.impl.presentation.DataPresentationOnConsoleService;
import net.kozon.dataharvester.impl.presentation.DataPresentationOnJSONService;
import net.kozon.dataharvester.impl.presentation.DataPresentationOnXMLService;
import net.kozon.dataharvester.impl.presentation.DataPresentationServiceExploitable;
import net.kozon.dataharvester.impl.provider.WebDataProvider;
import net.kozon.dataanalyzer.WebXPathGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HarvesterAppConfig {

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

    @Bean
    public WebXPathGenerator webXPathGenerator() {
        return new WebXPathGenerator();
    }

}
