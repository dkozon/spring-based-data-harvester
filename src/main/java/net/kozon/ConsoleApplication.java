package net.kozon;

import net.kozon.dataharvester.dto.DataFromWebSource;
import net.kozon.dataharvester.impl.presentation.DataPresentationOnConsoleService;
import net.kozon.dataharvester.impl.presentation.DataPresentationOnJSONService;
import net.kozon.dataharvester.impl.presentation.DataPresentationOnXMLService;
import net.kozon.dataharvester.impl.presentation.DataPresentationServiceExploitable;
import net.kozon.dataharvester.impl.provider.WebDataProvider;
import net.kozon.helpers.Configuration;
import net.kozon.dataanalyzer.WebXPathGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;

public class ConsoleApplication {

    public static void main(String[] args) throws IOException, URISyntaxException {
        ApplicationContext context = new AnnotationConfigApplicationContext(HarvesterAppConfig.class);

        final String source = Configuration.getInstance().source;
        final List<String> selectors = Configuration.getInstance().selectors;

        WebDataProvider webDataProvider = context.getBean(WebDataProvider.class);
        webDataProvider.setUrl(source);
        final List<String> listFromData = webDataProvider.prepareListFromData(selectors);
        final String sourceName = webDataProvider.prepareSourceName();
        WebXPathGenerator webXPathGenerator = context.getBean(WebXPathGenerator.class);

        final String cssSelectorsFileName = format("cssSelectorsFor%s.csv", sourceName);
        final String xmlFileName = format("DataFrom%s.xml", sourceName);
        final String jsonFileName = format("DataFrom%s.json", sourceName);
        final String txtFileName = format("DataFrom%s.txt", sourceName);
        final Date date = new Date();
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        webXPathGenerator.saveToCSV(cssSelectorsFileName, source);

        DataFromWebSource dataFromWebSource = new DataFromWebSource(sourceName, listFromData);

        DataPresentationOnConsoleService dataPresentationOnConsoleService = context.getBean(DataPresentationOnConsoleService.class);
        dataPresentationOnConsoleService.saveToFile(dataFromWebSource, txtFileName);

        DataPresentationOnXMLService dataPresentationOnXMLService = context.getBean(DataPresentationOnXMLService.class);
        dataPresentationOnXMLService.saveToFile(dataFromWebSource, xmlFileName);

        DataPresentationOnJSONService dataPresentationOnJSONService = context.getBean(DataPresentationOnJSONService.class);
        dataPresentationOnJSONService.saveToFile(dataFromWebSource, jsonFileName);

        DataPresentationServiceExploitable dataPresentationServiceExploitable = context.getBean(DataPresentationServiceExploitable.class);
        dataPresentationServiceExploitable.saveToFile(dataFromWebSource, "exploitResult.xml");
    }
}
