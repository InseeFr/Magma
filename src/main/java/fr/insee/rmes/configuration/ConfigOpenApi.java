package fr.insee.rmes.configuration;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
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

    @Value("${keycloak.auth-server-url:}")
    private String keycloakUrl;

    @Value("${keycloak.realm:}")
    private String realmName;

    private final String SCHEMEKEYCLOAK = "oAuthScheme";
    @Bean
    public OpenAPI customOpenAPIKeycloak() {
        // configuration pour récupérer un jeton auprès de Keycloak
        Server server = new Server();
        server.setUrl("http://" + apiScheme);
        final OpenAPI openapi = new OpenAPI().info(new Info().title("Swagger MAGMA API REST"));
        openapi.components(new Components().addSecuritySchemes(SCHEMEKEYCLOAK, new SecurityScheme()
                .type(SecurityScheme.Type.OAUTH2).in(SecurityScheme.In.HEADER).description("Authentification keycloak")
                .flows(new OAuthFlows().authorizationCode(new OAuthFlow()
                        .authorizationUrl(keycloakUrl + "/realms/" + realmName + "/protocol/openid-connect/auth")
                        .tokenUrl(keycloakUrl + "/realms/" + realmName + "/protocol/openid-connect/token")
                        .refreshUrl(keycloakUrl + "/realms/" + realmName + "/protocol/openid-connect/token")))));
        return openapi;
    }



    @Bean
    public OperationCustomizer ajouterKeycloak() {
        // configuration pour que Swagger utilise le jeton récupéré auprès de Keycloak
        return (operation, handlerMethod) -> {
            return operation.addSecurityItem(new SecurityRequirement().addList(SCHEMEKEYCLOAK));
        };
    }

    @Value("${fr.insee.rmes.magma.api.host}")
    public String apiScheme;






}
