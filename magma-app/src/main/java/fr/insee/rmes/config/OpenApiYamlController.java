package fr.insee.rmes.config;

import fr.insee.rmes.service.OpenApiService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Contrôleur exposant la spécification OpenAPI au format YAML.
 *
 * <p>Délègue le chargement et le filtrage de la spécification à {@link OpenApiService}.</p>
 */
@RestController
public class OpenApiYamlController {

    private final OpenApiService openApiService;

    public OpenApiYamlController(OpenApiService openApiService) {
        this.openApiService = openApiService;
    }

    @GetMapping(value = "/openapi.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> openApiYaml() throws IOException {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(openApiService.getOpenApiAsYaml());
    }
}