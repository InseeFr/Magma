package fr.insee.rmes.configuration;

import fr.insee.rmes.utils.config.Config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClient;

@Configuration
@Profile("!test")
public class RestClientConfiguration {

    public RestClient restClient(Config config){
        return RestClient.builder()
                .baseUrl(config.getBauhausUrl()+config.getDatasetsBaseUri())
                .build();
    }


}
