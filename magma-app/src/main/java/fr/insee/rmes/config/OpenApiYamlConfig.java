package fr.insee.rmes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiYamlConfig {

    @Value("${fr.insee.rmes.magma.api.sparqlEndpoint.module}")
    private String module;

    @GetMapping(value = "/openapi.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Resource> openApiYaml() {
        String fileName = "gestion".equals(module) ? "openapi-gestion.yaml" : "openapi-diffusion.yaml";
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(new ClassPathResource(fileName));
    }


}