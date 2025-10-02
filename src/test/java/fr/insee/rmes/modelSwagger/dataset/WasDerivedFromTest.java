package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class WasDerivedFromTest {

    List<String> datasets = List.of(
            "firstMockedDataSet",
            "secondMockedDataSet",
            "thirdMockedDataSet");

    List<LangContent> description = List.of(
            new LangContent(),
            new LangContent(),
            new LangContent()
    );

    @Test
    void shouldCheckConstructorInitialization(){
        WasDerivedFrom firstWasDerivedFrom = new WasDerivedFrom(datasets);
        WasDerivedFrom secondWasDerivedFrom = new WasDerivedFrom(datasets,description);
        assertEquals(firstWasDerivedFrom.getDatasets(),secondWasDerivedFrom.getDatasets());

    }

}