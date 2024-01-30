package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.model.datasets.CatalogRecordContributor;
import fr.insee.rmes.model.datasets.CatalogRecordCreated;
import fr.insee.rmes.model.datasets.CatalogRecordCreator;
import fr.insee.rmes.model.datasets.CatalogRecordModified;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

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

}