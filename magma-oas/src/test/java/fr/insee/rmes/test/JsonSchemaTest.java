package fr.insee.rmes.test;

import io.vertx.core.json.JsonObject;
import io.vertx.json.schema.Draft;
import io.vertx.json.schema.JsonSchema;
import io.vertx.json.schema.JsonSchemaOptions;
import io.vertx.json.schema.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class JsonSchemaTest {

    @Test
    void checkRubriqueSchema() throws IOException, URISyntaxException {
        String schemaFileName = "rubrique.schema.json";
        JsonObject rubriqueSchema = new JsonObject(readInClasspath(schemaFileName));
        var validator = Validator.create(JsonSchema.of(rubriqueSchema), new JsonSchemaOptions().setDraft(Draft.DRAFT202012));
        JsonObject rubriqueInstance = new JsonObject(readInClasspath("rubrique.json"));
        assertThat(validator.validate(rubriqueInstance).getValid()).isTrue();
    }

    @NonNull
    private static String readInClasspath(String schemaFileName) throws IOException, URISyntaxException {
        Path path = Path.of(JsonSchemaTest.class.getResource("/").toURI()).getParent()
                .getParent()
                .resolve("src/main/resources")
                .resolve(schemaFileName);
        return String.join("\n", Files.readAllLines(path));
    }

}
