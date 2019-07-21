package net.kozon;

import net.kozon.dataanalyzer.impl.WebDataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import net.kozon.dataanalyzer.service.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.service.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.service.DataPresentationOnXMLService;
import net.kozon.dataanalyzer.service.DataPresentationServiceExploitable;
import net.kozon.helpers.Configuration;
import net.kozon.helpers.HarvesterConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.UUID;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HarvesterConfig.class);

        WebDataProvider webDataProvider = context.getBean(WebDataProvider.class);
        webDataProvider.setUrl(Configuration.getInstance().source);

        DataFromSource dataFromSource = new DataFromSource(webDataProvider.prepareSourceName(), webDataProvider.prepareListFromData());

        DataPresentationOnConsoleService dataPresentationOnConsoleService = context.getBean(DataPresentationOnConsoleService.class);
        dataPresentationOnConsoleService.presentDataBy(dataFromSource);

        DataPresentationOnXMLService dataPresentationOnXMLService = context.getBean(DataPresentationOnXMLService.class);
        dataPresentationOnXMLService.presentDataBy(dataFromSource, UUID.randomUUID() + ".xml");

        DataPresentationOnJSONService dataPresentationOnJSONService = context.getBean(DataPresentationOnJSONService.class);
        dataPresentationOnJSONService.presentDataBy(dataFromSource, UUID.randomUUID() + ".json");

        DataPresentationServiceExploitable dataPresentationServiceExploitable = context.getBean(DataPresentationServiceExploitable.class);
        dataPresentationServiceExploitable.presentDataBy();
    }
}
