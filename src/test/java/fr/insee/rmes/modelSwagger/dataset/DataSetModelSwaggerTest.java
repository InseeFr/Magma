package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.model.datasets.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DataSetModelSwaggerTest {


    @Test
    void verify_jacksonAnnotation_test() throws JsonProcessingException, JSONException {
        var dataSetModelSwagger=new DataSetModelSwagger();
        dataSetModelSwagger.setCatalogRecordContributor(new CatalogRecordContributor("test_contributor"));
        dataSetModelSwagger.setCatalogRecordCreated(new CatalogRecordCreated("recordCreated"));
        dataSetModelSwagger.setCatalogRecordCreator(new CatalogRecordCreator("creator"));
        dataSetModelSwagger.setCatalogRecordModified(new CatalogRecordModified("modified"));
        ObjectMapper mapper=new JsonMapper();
        var result=mapper.writeValueAsString(dataSetModelSwagger);
        var json=new JSONObject(result);

        assertThat(json.keySet()).contains("catalogRecordContributor", "catalogRecordCreated", "catalogRecordCreator", "catalogRecordModified");
        assertThat(result).contains("\"catalogRecordContributor\":\"test_contributor\"",
                "\"catalogRecordCreated\":\"recordCreated\"",
                "\"catalogRecordCreator\":\"creator\"",
                "\"catalogRecordModified\":\"modified\"");
    }

    @Test
    void constructor_withIdTitleUriValidationStateAndCatalogFields_shouldSetAllFields() {
        Id id = new Id("jd1000");
        Uri uri = new Uri("http://bauhaus/catalogues/jeuDeDonnees/jd1000");
        List<LangContent> title = List.of(new LangContent("fr", "Titre FR"), new LangContent("en", "Title EN"));
        String validationState = "Published";
        CatalogRecordCreated created = new CatalogRecordCreated("2024-01-01T00:00:00");
        CatalogRecordModified modified = new CatalogRecordModified("2024-06-15T10:00:00");
        CatalogRecordCreator creator = new CatalogRecordCreator("creator1");
        CatalogRecordContributor contributor = new CatalogRecordContributor("contributor1");

        DataSetModelSwagger result = new DataSetModelSwagger(id, title, uri, validationState, created, modified, creator, contributor);

        assertThat(result.getId()).isEqualTo("jd1000");
        assertThat(result.getUri()).isEqualTo("http://bauhaus/catalogues/jeuDeDonnees/jd1000");
        assertThat(result.getTitle()).isEqualTo(title);
        assertThat(result.getValidationState()).isEqualTo("Published");
        assertThat(result.getCatalogRecordCreated()).isEqualTo(created);
        assertThat(result.getCatalogRecordModified()).isEqualTo(modified);
        assertThat(result.getCatalogRecordCreator()).isEqualTo(creator);
        assertThat(result.getCatalogRecordContributor()).isEqualTo(contributor);
    }

    @Test
    void constructor_withIdTitleUriValidationStateAndCatalogFields_shouldSerializeToJson() throws JsonProcessingException, JSONException {
        Id id = new Id("jd1000");
        Uri uri = new Uri("http://bauhaus/catalogues/jeuDeDonnees/jd1000");
        List<LangContent> title = List.of(new LangContent("fr", "Titre FR"), new LangContent("en", "Title EN"));
        CatalogRecordCreated created = new CatalogRecordCreated("2024-01-01T00:00:00");
        CatalogRecordModified modified = new CatalogRecordModified("2024-06-15T10:00:00");
        CatalogRecordCreator creator = new CatalogRecordCreator("creator1");
        CatalogRecordContributor contributor = new CatalogRecordContributor("contributor1");

        DataSetModelSwagger result = new DataSetModelSwagger(id, title, uri, "Published", created, modified, creator, contributor);

        ObjectMapper mapper = new JsonMapper();
        String json = mapper.writeValueAsString(result);
        JSONObject jsonObject = new JSONObject(json);

        assertThat(jsonObject.getString("id")).isEqualTo("jd1000");
        assertThat(jsonObject.getString("uri")).isEqualTo("http://bauhaus/catalogues/jeuDeDonnees/jd1000");
        assertThat(jsonObject.getString("validationState")).isEqualTo("Published");
        assertThat(jsonObject.getString("catalogRecordCreated")).isEqualTo("2024-01-01T00:00:00");
        assertThat(jsonObject.getString("catalogRecordModified")).isEqualTo("2024-06-15T10:00:00");
        assertThat(jsonObject.getString("catalogRecordCreator")).isEqualTo("creator1");
        assertThat(jsonObject.getString("catalogRecordContributor")).isEqualTo("contributor1");
        assertThat(jsonObject.getJSONArray("title")).hasSize(2);
    }

}