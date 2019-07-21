package net.kozon;

import net.kozon.dataanalyzer.impl.provider.WebDataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnXMLService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationServiceExploitable;
import net.kozon.helpers.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.UUID;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HarvesterAppConfig.class);

        WebDataProvider webDataProvider = context.getBean(WebDataProvider.class);
        webDataProvider.setUrl(Configuration.getInstance().source);

        DataFromSource dataFromSource = new DataFromSource(webDataProvider.prepareSourceName(), webDataProvider.prepareListFromData());

        DataPresentationOnConsoleService dataPresentationOnConsoleService = context.getBean(DataPresentationOnConsoleService.class);
        dataPresentationOnConsoleService.saveToFile(dataFromSource, UUID.randomUUID() + ".txt");

        DataPresentationOnXMLService dataPresentationOnXMLService = context.getBean(DataPresentationOnXMLService.class);
        dataPresentationOnXMLService.saveToFile(dataFromSource, UUID.randomUUID() + ".xml");

        DataPresentationOnJSONService dataPresentationOnJSONService = context.getBean(DataPresentationOnJSONService.class);
        dataPresentationOnJSONService.saveToFile(dataFromSource, UUID.randomUUID() + ".json");

        DataPresentationServiceExploitable dataPresentationServiceExploitable = context.getBean(DataPresentationServiceExploitable.class);
        dataPresentationServiceExploitable.saveToFile(dataFromSource, "exploitResult.xml");
    }
}
