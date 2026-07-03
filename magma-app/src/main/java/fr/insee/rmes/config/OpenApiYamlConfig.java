package fr.insee.rmes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class OpenApiYamlConfig {

    private static final String VERSION_GESTION = "1.4.11";
    private static final String VERSION_DIFFUSION = "3.7.2";

    @Value("${fr.insee.rmes.magma.api.sparqlEndpoint.module}")
    private String module;

    @GetMapping(value = "/openapi.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> openApiYaml() throws IOException {
        boolean isGestion = "gestion".equals(module);
        String fileName = isGestion ? "openapi-gestion.yaml" : "openapi-diffusion.yaml";
        String version = isGestion ? VERSION_GESTION : VERSION_DIFFUSION;

        String content = StreamUtils.copyToString(new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8);
        String contentWithVersion = content.replace("${version}", version);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(contentWithVersion);
    }

}