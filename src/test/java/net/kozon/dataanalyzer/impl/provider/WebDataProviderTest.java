package net.kozon.dataanalyzer.impl.provider;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import lombok.extern.slf4j.Slf4j;
import net.kozon.helpers.Configuration;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
        // given
        service.stubFor(get(urlEqualTo("/endpoint"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "text/plain")
                        .withBody("<div><p style=\"margin:0;padding:5px 0;\">Złoto 5398.38 PLN / UNCJA</p></div>")));
    }

    @Test
    public void setUrl() {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        // then
        assertThat(webDataProvider.getUrl()).isEqualTo(TEST_URL);
    }

    @Test
    public void getDocument() throws IOException {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        // then
        log.info(webDataProvider.getDocument().outerHtml());
        assertThat(webDataProvider.getDocument().outerHtml().contains("Złoto 5398.38 PLN"));
    }

    @Test
    public void extractDetailedData() throws IOException {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        // then
        log.info(webDataProvider.extractDetailedData("p:nth-child(1)"));
        assertThat(webDataProvider.extractDetailedData("p:nth-child(1)")).contains("Złoto 5398.38 PLN / UNCJA");
    }

    @Test
    public void extractSourceName() {
        // when
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        //then
        log.info(webDataProvider.prepareSourceName());
        assertThat(webDataProvider.prepareSourceName()).isEqualTo("page_localhost8100endpoint");
    }

    @Test
    public void prepareSourceName() {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        assertThat(webDataProvider.prepareSourceName()).isEqualTo("page_localhost_8100_endpoint");
    }

    @Test
    public void prepareListFromData() throws IOException, URISyntaxException {
        WebDataProvider webDataProvider = new WebDataProvider();
        webDataProvider.setUrl(TEST_URL);
        assertThat(webDataProvider.prepareListFromData(Configuration.getInstance().selectors)).contains("Złoto 5398.38 PLN / UNCJA");

    }
}