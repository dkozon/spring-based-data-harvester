package net.kozon;

import net.kozon.dataanalyzer.impl.WebDataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import net.kozon.dataanalyzer.service.DataPresentationOnConsoleService;
import net.kozon.dataanalyzer.service.DataPresentationOnJSONService;
import net.kozon.dataanalyzer.service.DataPresentationOnXMLService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ConsoleApplication {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        WebDataProvider webDataProvider = context.getBean(WebDataProvider.class);
        webDataProvider.setUrl("http://www.79element.pl");

        DataPresentationOnConsoleService dataPresentationOnConsoleService = context.getBean(DataPresentationOnConsoleService.class);
        dataPresentationOnConsoleService.presentDataBy("#spot_block_left > div:nth-child(2) > p:nth-child(1)", "Złoto");

        Map<String, String> map = new HashMap<>();
        map.put("zloto", webDataProvider.extractDetailedData("#spot_block_left > div:nth-child(2) > p:nth-child(1)", "Złoto"));

        DataFromSource dataFromSource = new DataFromSource(webDataProvider.extractSourceName(), map);

        DataPresentationOnXMLService dataPresentationOnXMLService = context.getBean(DataPresentationOnXMLService.class);
        dataPresentationOnXMLService.presentDataBy("wynik_XML.xml", dataFromSource);

        DataPresentationOnJSONService dataPresentationOnJSONService = context.getBean(DataPresentationOnJSONService.class);
        dataPresentationOnJSONService.presentDataBy("wynik_JSON.json", dataFromSource);
    }
}
