package fr.insee.rmes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class OpenApiYamlConfig {

    private static final String VERSION = "2.0.0";

    @Value("${fr.insee.rmes.magma.display.geo:true}")
    private boolean displayGeo;

    @GetMapping(value = "/openapi.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> openApiYaml() throws IOException {
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

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(dumper.dump(openApi));
    }

    @SuppressWarnings("unchecked")
    private void filterGeo(Map<String, Object> openApi) {
        List<Map<String, Object>> tags = (List<Map<String, Object>>) openApi.get("tags");
        if (tags != null) {
            openApi.put("tags", tags.stream()
                    .filter(tag -> !((String) tag.get("name")).startsWith("geo"))
                    .collect(Collectors.toList()));
        }
        Map<String, Object> paths = (Map<String, Object>) openApi.get("paths");
        if (paths != null) {
            paths.entrySet().removeIf(entry -> entry.getKey().startsWith("/geo"));
        }
    }

}