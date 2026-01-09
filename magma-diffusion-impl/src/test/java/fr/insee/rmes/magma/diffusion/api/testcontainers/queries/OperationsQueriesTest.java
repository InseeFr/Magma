package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.insee.rmes.magma.diffusion.api.OperationsEndpoints;
import org.json.JSONException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.JsonNode;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.SerializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class OperationsQueriesTest extends TestcontainerTest {

    @Autowired
    OperationsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /// //////////////////////////////////////////////////////////////////
    ///             operations/rapportQualite/{id}                    ///
    /// //////////////////////////////////////////////////////////////////

    //    operations/rapportQualite/1979
    @Test
    void should_return_rapportQualite1979_when_OperationsRapportQualite_id1979() throws IOException {
        var response = endpoints.getRapportQualiteByCode("1979");
        var result = response.getBody();

        Path resourcePath = Paths.get("src/test/resources/testcontainers/rapport-qualite-1979.json");
        String expectedJson = Files.readString(resourcePath);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        String actualJson = mapper.writeValueAsString(result);

        // Trier les JSON avant comparaison
        String sortedActual = sortJsonArrays(actualJson, mapper);
        String sortedExpected = sortJsonArrays(expectedJson, mapper);

        assertThat(sortedActual).isEqualTo(sortedExpected);
    }

    private String sortJsonArrays(String json, ObjectMapper mapper) throws IOException {
        JsonNode node = mapper.readTree(json);
        sortNode(node);
        return mapper.writeValueAsString(node);
    }

    private void sortNode(JsonNode node) {
        if (node.isObject()) {
            node.fields().forEachRemaining(entry -> sortNode(entry.getValue()));
        } else if (node.isArray() && node.size() > 0) {
            // Trier le tableau si les éléments ont un champ "id"
            if (node.get(0).has("id")) {
                List<JsonNode> list = new ArrayList<>();
                node.forEach(list::add);
                list.sort(Comparator.comparing(n -> n.get("id").asText()));

                // Remplacer les éléments dans l'ordre trié
                ArrayNode arrayNode = (ArrayNode) node;
                arrayNode.removeAll();
                list.forEach(item -> {
                    arrayNode.add(item);
                    sortNode(item); // Continuer récursivement
                });
            } else {
                // Si pas d'id, trier quand même les enfants
                node.forEach(this::sortNode);
            }
        }
    }
}

