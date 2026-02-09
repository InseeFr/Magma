package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ThemeModelSwaggerTest {

        List<LabelDataSet> labelDataSetS = List.of(
                new LabelDataSet(),
                new LabelDataSet(),
                new LabelDataSet()
        );

        ThemeModelSwagger themeModelSwagger = new ThemeModelSwagger("mockedUri",labelDataSetS,"mockedTaxonomy");

    @Test
    void shouldCheckUriMethods(){
        themeModelSwagger.setUri("newUri");
        assertEquals("newUri", themeModelSwagger.getUri());
    }

    @Test
    void shouldCheckThemeTaxonomyMethods(){
        themeModelSwagger.setThemeTaxonomy("newTaxonomy");
        assertEquals("newTaxonomy", themeModelSwagger.getThemeTaxonomy());
    }

    @Test
    void shouldCheckThemeLabelDataset(){
        themeModelSwagger.setLabel(labelDataSetS);
        assertEquals(labelDataSetS, themeModelSwagger.getLabel());
    }

    }