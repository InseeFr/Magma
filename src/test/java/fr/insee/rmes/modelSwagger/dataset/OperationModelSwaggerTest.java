package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OperationModelSwaggerTest {

    List<LabelDataSet> labelDataSet = List.of(new LabelDataSet(),new LabelDataSet());
    List<LabelDataSet> newLabelDataSet = List.of(new LabelDataSet(),new LabelDataSet());

    OperationModelSwagger operationModelSwagger = new OperationModelSwagger("mockedUri",
            "mockedid",
            labelDataSet);

    @Test
    void shouldCheckUriMethods(){
        operationModelSwagger.setUri("newUri");
        assertEquals("newUri", operationModelSwagger.getUri());
    }

    @Test
    void shouldCheckIdMethods(){
        operationModelSwagger.setId("newId");
        assertEquals("newId", operationModelSwagger.getId());
    }

    @Test
    void shouldCheckLabelMethods(){
        operationModelSwagger.setLabel(newLabelDataSet);
        assertEquals(newLabelDataSet, operationModelSwagger.getLabel());
    }

}