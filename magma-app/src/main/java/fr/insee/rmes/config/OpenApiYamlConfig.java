package fr.insee.rmes.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenApiYamlConfig {

    @GetMapping(value = "/openapi-diffusion.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Resource> openApiYaml() {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(new ClassPathResource("openapi-diffusion.yaml"));
    }

    @GetMapping(value = "/openapi-gestion.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<Resource> openApiGestionYaml() {
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(new ClassPathResource("openapi-gestion.yaml"));
    }

}