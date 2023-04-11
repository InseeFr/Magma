package fr.insee.rmes.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigOpenApi {

    @Value("${keycloak.auth-server-url:}")
    private String keycloakUrl;

    @Value("${keycloak.realm:}")
    private String realmName;

    @Value("${fr.insee.rmes.magma.api.host}")
    public String apiScheme;
    private final String SCHEMEKEYCLOAKBEARER= "bearerScheme";
    @Bean
    public OpenAPI customOpenAPIKeycloak() {
        // configuration pour récupérer un jeton auprès de Keycloak
        final OpenAPI openapi = new OpenAPI().info(new Info().title("Swagger MAGMA API REST"));
        openapi.components(new Components().addSecuritySchemes(SCHEMEKEYCLOAKBEARER,
                        new SecurityScheme()
                                .name(SCHEMEKEYCLOAKBEARER)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                )
        ) ;
        return openapi;
    }



    @Bean
    public OperationCustomizer ajouterKeycloak() {
        // configuration pour que Swagger utilise le jeton récupéré auprès de Keycloak
        return (operation, handlerMethod) -> {
            return operation.addSecurityItem(new SecurityRequirement().addList(SCHEMEKEYCLOAKBEARER));
        };
    }









}
