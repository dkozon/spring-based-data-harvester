package net.kozon.dataanalyzer.impl;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class WebDataProviderTest {

    private static final String TEST_URL = "http://localhost:8100/endpoint";

    @Rule
    public static WireMockRule service = new WireMockRule(8100);

    @BeforeClass
    public static void setUp() {
        service.stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("<div><p style=\"margin:0;padding:5px 0;\">Złoto 5398.38 PLN / UNCJA</p></div>")));
    }

    @Test
    public void setUrl() throws IOException {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        assertThat(webDataProvider.getUrl()).isEqualTo(TEST_URL);
    }

    @Test
    public void getDocument() throws IOException {

        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        log.info(webDataProvider.getDocument().outerHtml());
        assertThat(webDataProvider.getDocument().outerHtml().contains("Złoto 5398.38 PLN"));
    }

    @Test
    public void extractDetailedData() throws IOException {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        log.info(webDataProvider.extractDetailedData("p:nth-child(1)"));
        assertThat(webDataProvider.extractDetailedData("p:nth-child(1)")).contains("Złoto 5398.38 PLN / UNCJA");
    }

    @Test
    public void extractSourceName() throws IOException {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        log.info(webDataProvider.prepareSourceName());
        assertThat(webDataProvider.prepareSourceName()).isEqualTo("page_localhost8100endpoint");
    }
}