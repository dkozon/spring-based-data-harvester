package net.kozon.dataharvester.impl.presentation;

import com.github.tomakehurst.wiremock.WireMockServer;
import net.kozon.dataharvester.dto.DataFromWebSource;
import net.kozon.dataharvester.impl.provider.WebDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

public class DataPresentationOnJSONServiceTest {

    private static final String TEST_URL = "http://localhost:8101/endpoint";

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        // given
        wireMockServer = new WireMockServer(8101);
        wireMockServer.start();
        wireMockServer.stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("<div><p style=\"margin:0;padding:5px 0;\">Złoto 5398.38 PLN / UNCJA</p></div>")));
    }

    @Test
    void saveToFile() throws IOException {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        DataPresentationOnJSONService dataPresentationOnConsoleService = new DataPresentationOnJSONService();

        List<String> list = new ArrayList<>();
        list.add(webDataProvider.extractDetailedData("p:nth-child(1)"));

        DataFromWebSource dataFromWebSource = new DataFromWebSource(webDataProvider.prepareSourceName(), list);

        // then
        assertThat(webDataProvider.getUrl()).isEqualTo(TEST_URL);
        assertThat(dataPresentationOnConsoleService.saveToFile(dataFromWebSource, "test.json")).isTrue();
    }
}