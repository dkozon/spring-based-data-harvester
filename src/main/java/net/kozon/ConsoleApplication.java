package net.kozon;

import net.kozon.dataanalyzer.dto.DataFromWebSource;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationOnXMLService;
import net.kozon.dataanalyzer.impl.presentation.DataPresentationServiceExploitable;
import net.kozon.dataanalyzer.impl.provider.WebDataProvider;
import net.kozon.helpers.Configuration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException, URISyntaxException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HarvesterAppConfig.class);

        WebDataProvider webDataProvider = context.getBean(WebDataProvider.class);
        webDataProvider.setUrl(Configuration.getInstance().source);

        DataFromWebSource dataFromWebSource = new DataFromWebSource(webDataProvider.prepareSourceName(), webDataProvider.prepareListFromData(Configuration.getInstance().selectors));

        DataPresentationOnConsoleService dataPresentationOnConsoleService = context.getBean(DataPresentationOnConsoleService.class);
        dataPresentationOnConsoleService.saveToFile(dataFromWebSource, UUID.randomUUID() + ".txt");

        DataPresentationOnXMLService dataPresentationOnXMLService = context.getBean(DataPresentationOnXMLService.class);
        dataPresentationOnXMLService.saveToFile(dataFromWebSource, UUID.randomUUID() + ".xml");

        DataPresentationOnJSONService dataPresentationOnJSONService = context.getBean(DataPresentationOnJSONService.class);
        dataPresentationOnJSONService.saveToFile(dataFromWebSource, UUID.randomUUID() + ".json");

        DataPresentationServiceExploitable dataPresentationServiceExploitable = context.getBean(DataPresentationServiceExploitable.class);
        dataPresentationServiceExploitable.saveToFile(dataFromWebSource, "exploitResult.xml");
    }
}
