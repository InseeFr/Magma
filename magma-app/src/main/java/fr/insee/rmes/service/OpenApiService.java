package fr.insee.rmes.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Service responsable du chargement et du filtrage de la spécification OpenAPI.
 *
 * <p>Charge le fichier {@code openapi.yaml} depuis le classpath, injecte le numéro de version,
 * et filtre optionnellement les tags et paths géographiques selon la propriété
 * {@code fr.insee.rmes.magma.display.geo}.</p>
 */
@Service
public class OpenApiService {

    private static final String VERSION = "3.0.0";

    @Value("${fr.insee.rmes.magma.display.geo:true}")
    private boolean displayGeo;

    public String getOpenApiAsYaml() throws IOException {
        String content = StreamUtils.copyToString(
                new ClassPathResource("openapi.yaml").getInputStream(), StandardCharsets.UTF_8);
        content = content.replace("${version}", VERSION);

        Yaml yaml = new Yaml();
        Map<String, Object> openApi = yaml.load(content);

        if (!displayGeo) {
            filterGeo(openApi);
        }

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml dumper = new Yaml(options);

        return dumper.dump(openApi);
    }

    @SuppressWarnings("unchecked")
    private void filterGeo(Map<String, Object> openApi) {
        List<Map<String, Object>> tags = (List<Map<String, Object>>) openApi.get("tags");
        if (tags != null) {
            openApi.put("tags", tags.stream()
                    .filter(tag -> !Boolean.TRUE.equals(tag.get("x-geo")))
                    .toList());
        }
        Map<String, Object> paths = (Map<String, Object>) openApi.get("paths");
        if (paths != null) {
            paths.entrySet().removeIf(entry -> entry.getKey().startsWith("/geo"));
        }
    }
}