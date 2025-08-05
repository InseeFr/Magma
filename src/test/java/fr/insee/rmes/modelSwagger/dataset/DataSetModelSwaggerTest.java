package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.model.datasets.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    Id id = new Id("mockedId");
    Uri uri = new Uri("mockedUri");
    Modified modified = new Modified("mockedModified");
    String validationState = "mockedValidationState";
    CatalogRecordCreated catalogRecordCreated = new CatalogRecordCreated("mockedValue");
    CatalogRecordModified catalogRecordModified = new CatalogRecordModified("mockedValue");
    CatalogRecordCreator catalogRecordCreator = new CatalogRecordCreator("mockedValue");
    CatalogRecordContributor catalogRecordContributor = new CatalogRecordContributor("mockedValue");
    List<LangContent> title = List.of(new LangContent());
    List<ThemeModelSwagger> themeModelSwaggerS = List.of(new ThemeModelSwagger("mockedUri",List.of(new LabelDataSet()),"mockedThemeTaxonomy"));
    DisseminationStatus disseminationStatus= new DisseminationStatus("mockedDisseminationStatus");


    @Test
    void shouldInitializeConstructor(){

        DataSetModelSwagger firstDataSetModelSwagger = new DataSetModelSwagger();
        firstDataSetModelSwagger.setId("mockedId");
        firstDataSetModelSwagger.setTitle(title);
        firstDataSetModelSwagger.setUri("mockedUri");
        firstDataSetModelSwagger.setValidationState("mockedValidationState");
        firstDataSetModelSwagger.setCatalogRecordCreated(catalogRecordCreated);
        firstDataSetModelSwagger.setCatalogRecordModified(catalogRecordModified);
        firstDataSetModelSwagger.setCatalogRecordCreator(catalogRecordCreator);
        firstDataSetModelSwagger.setCatalogRecordContributor(catalogRecordContributor);

        DataSetModelSwagger secondDataSetModelSwagger= new DataSetModelSwagger(id, title, uri, validationState, disseminationStatus, catalogRecordCreated, catalogRecordModified, catalogRecordCreator, catalogRecordContributor);

        DataSetModelSwagger thirdDataSetModelSwagger= new DataSetModelSwagger(id, uri, modified);

        DataSetModelSwagger fourthDataSetModelSwagger= new DataSetModelSwagger(id, title, uri, modified, validationState);

        DataSetModelSwagger fifthDataSetModelSwagger= new DataSetModelSwagger(id, title, uri, modified, validationState, themeModelSwaggerS);

        assertTrue(Objects.equals(firstDataSetModelSwagger.getId(), secondDataSetModelSwagger.getId()) &&
                Objects.equals(secondDataSetModelSwagger.getId(), thirdDataSetModelSwagger.getId()) &&
                Objects.equals(thirdDataSetModelSwagger.getId(), fourthDataSetModelSwagger.getId()) &&
                Objects.equals(fourthDataSetModelSwagger.getId(), fifthDataSetModelSwagger.getId())
        );

    }

}