package fr.insee.rmes.configuration;

import fr.insee.rmes.utils.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;
import static org.junit.jupiter.api.Assertions.*;

class RestClientConfigurationTest {

    @Test
    void shouldInitializeDefaultRestClient(){
        RestClientConfiguration restClientConfiguration = new RestClientConfiguration();
        RestClient restClient = restClientConfiguration.restClient(new Config());
        assertTrue(restClient.head().toString().contains("DefaultRestClient")&&
                restClient.patch().toString().contains("DefaultRestClient") &&
                restClient.get().toString().contains("DefaultRestClient") &&
                restClient.delete().toString().contains("DefaultRestClient") &&
                restClient.mutate().toString().contains("DefaultRestClient") &&
                restClient.post().toString().contains("DefaultRestClient") &&
                restClient.put().toString().contains("DefaultRestClient") &&
                restClient.options().toString().contains("DefaultRestClient")
        );

    }
}