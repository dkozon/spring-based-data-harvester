package net.kozon.dataanalyzer.impl.presentation;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import net.kozon.dataanalyzer.impl.provider.WebDataProvider;
import net.kozon.dataanalyzer.pojo.DataFromSource;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public class DataPresentationOnXMLServiceTest {

    private static final String TEST_URL = "http://localhost:8100/endpoint";

    @Rule
    public static WireMockRule service = new WireMockRule(8100);

    @BeforeClass
    public static void setUp() {
        // given
        service.stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("<div><p style=\"margin:0;padding:5px 0;\">Złoto 5398.38 PLN / UNCJA</p></div>")));
    }

    @Test
    public void saveToFile() throws IOException {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        DataPresentationOnXMLService dataPresentationOnConsoleService = new DataPresentationOnXMLService();

        List<String> list = new ArrayList<>();
        list.add(webDataProvider.extractDetailedData("p:nth-child(1)"));

        DataFromSource dataFromSource = new DataFromSource(webDataProvider.prepareSourceName(), list);

        // then
        assertThat(webDataProvider.getUrl()).isEqualTo(TEST_URL);
        assertThat(dataPresentationOnConsoleService.saveToFile(dataFromSource, "test.xml")).isTrue();
    }
}