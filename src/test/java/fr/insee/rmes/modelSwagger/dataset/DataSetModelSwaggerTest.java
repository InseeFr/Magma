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
import static org.junit.jupiter.api.Assertions.*;

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
    void verify_creatorToJson_test() throws JsonProcessingException, JSONException {
        var dataSetModelSwagger=new DataSetModelSwagger();
        dataSetModelSwagger.setCreator(new Creator(List.of("Leonardo", "Michelangelo", "Donatello", "Rafaello")));

        ObjectMapper mapper=new JsonMapper();
        var result=mapper.writeValueAsString(dataSetModelSwagger);
        assertThat((new JSONObject(result)).getJSONArray("creator")).asString().isEqualTo("[\"Leonardo\",\"Michelangelo\",\"Donatello\",\"Rafaello\"]");
    }


}