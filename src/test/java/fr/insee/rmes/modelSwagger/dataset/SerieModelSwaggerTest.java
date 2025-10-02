package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SerieModelSwaggerTest {

        List<LabelDataSet> labelDataSetS  = List.of(
                new LabelDataSet(),
                new LabelDataSet(),
                new LabelDataSet()
        );

    List<LabelDataSet> otherLabelDataSetS  = List.of(
            new LabelDataSet(),
            new LabelDataSet(),
            new LabelDataSet()
    );

        SerieModelSwagger serieModelSwagger = new SerieModelSwagger("mockedUri","mockedId",labelDataSetS);

    @Test
    void shouldCheckUriMethods(){
        serieModelSwagger.setUri("newUri");
        assertEquals("newUri", serieModelSwagger.getUri());
    }

    @Test
    void shouldCheckIdMethods(){
        serieModelSwagger.setId("newId");
        assertEquals("newId", serieModelSwagger.getId());
    }

    @Test
    void shouldCheckLabelMethods(){
        serieModelSwagger.setLabel(otherLabelDataSetS );
        assertEquals(otherLabelDataSetS ,serieModelSwagger.getLabel());
    }
}