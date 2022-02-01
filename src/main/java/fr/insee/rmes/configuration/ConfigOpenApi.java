package fr.insee.rmes.configuration;


import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.core.env.Environment;


@Configuration

public class ConfigOpenApi {
    @Autowired
    private Environment env;


    @Value("${fr.insee.rmes.magma.api.host}")
    public String apiScheme;

    @Bean
    public OpenAPI openAPI () {
    Server server = new Server();
    server.setUrl("http://"+apiScheme  );
    OpenAPI openAPI = (OpenAPI) new OpenAPI().addServersItem(server).info(new Info().title("Magma API")
            .description(
                    "Rest Endpoints and services used by Magma"
                ));
	return openAPI;
                        }


}
