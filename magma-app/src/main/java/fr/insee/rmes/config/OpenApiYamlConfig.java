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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class OpenApiYamlConfig {

    private static final String VERSION = "2.0.2";

    @Value("${fr.insee.rmes.magma.display.geo:true}")
    private boolean displayGeo;

    @GetMapping(value = "/openapi.yaml", produces = "text/plain;charset=UTF-8")
    public ResponseEntity<String> openApiYaml() throws IOException {
        String gestionContent = loadYamlWithVersion("openapi-gestion.yaml", VERSION);
        String diffusionContent = loadYamlWithVersion("openapi-diffusion.yaml", VERSION);

        Yaml yaml = new Yaml();
        Map<String, Object> gestion = yaml.load(gestionContent);
        Map<String, Object> diffusion = yaml.load(diffusionContent);

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml dumper = new Yaml(options);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(dumper.dump(buildMergedYaml(gestion, diffusion)));
    }

    private String loadYamlWithVersion(String fileName, String version) throws IOException {
        String content = StreamUtils.copyToString(new ClassPathResource(fileName).getInputStream(), StandardCharsets.UTF_8);
        return content.replace("${version}", version);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> buildMergedYaml(Map<String, Object> gestion, Map<String, Object> diffusion) {
        Map<String, Object> merged = new LinkedHashMap<>();
        merged.put("openapi", "3.1.0");
        merged.put("info", buildInfo(gestion));
        if (diffusion.containsKey("externalDocs")) {
            merged.put("externalDocs", diffusion.get("externalDocs"));
        }
        merged.put("servers", gestion.get("servers"));
        merged.put("security", gestion.get("security"));
        merged.put("tags", mergeTags(gestion, diffusion));
        merged.put("paths", mergePaths(gestion, diffusion));
        merged.put("components", mergeComponents(
                (Map<String, Object>) gestion.getOrDefault("components", Collections.emptyMap()),
                (Map<String, Object>) diffusion.getOrDefault("components", Collections.emptyMap())));
        return merged;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> buildInfo(Map<String, Object> gestion) {
        Map<String, Object> info = new LinkedHashMap<>((Map<String, Object>) gestion.get("info"));
        info.put("title", "Magma Fusion API");
        info.put("description", "API Gestion et Diffusion des métadonnées de l'Insee");
        info.put("version", VERSION);
        return info;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> mergeTags(Map<String, Object> gestion, Map<String, Object> diffusion) {
        List<Map<String, Object>> gestionTags = (List<Map<String, Object>>) gestion.getOrDefault("tags", Collections.emptyList());
        List<Map<String, Object>> diffusionTags = (List<Map<String, Object>>) diffusion.getOrDefault("tags", Collections.emptyList());
        return Stream.concat(gestionTags.stream(), diffusionTags.stream())
                .filter(tag -> displayGeo || !((String) tag.get("name")).startsWith("geo"))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> mergePaths(Map<String, Object> gestion, Map<String, Object> diffusion) {
        Map<String, Object> mergedPaths = new LinkedHashMap<>();
        mergedPaths.putAll((Map<String, Object>) gestion.getOrDefault("paths", Collections.emptyMap()));
        ((Map<String, Object>) diffusion.getOrDefault("paths", Collections.emptyMap())).forEach((path, value) -> {
            if (displayGeo || !path.startsWith("/geo")) {
                mergedPaths.put(path, value);
            }
        });
        return mergedPaths;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> mergeComponents(Map<String, Object> gestion, Map<String, Object> diffusion) {
        Map<String, Object> result = new LinkedHashMap<>();
        Set<String> allKeys = new LinkedHashSet<>(gestion.keySet());
        allKeys.addAll(diffusion.keySet());
        for (String key : allKeys) {
            if (gestion.containsKey(key) && diffusion.containsKey(key)) {
                Map<String, Object> mergedSection = new LinkedHashMap<>();
                mergedSection.putAll((Map<String, Object>) gestion.get(key));
                mergedSection.putAll((Map<String, Object>) diffusion.get(key));
                result.put(key, mergedSection);
            } else {
                result.put(key, gestion.getOrDefault(key, diffusion.get(key)));
            }
        }
        return result;
    }

}