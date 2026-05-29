package fr.insee.rmes.model.datasets;

import fr.insee.rmes.modelSwagger.dataset.Label;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProcessStepTest {

    @ParameterizedTest
    @ValueSource(strings = { "mockedElement1", "mockedElement2", "mockedElement3" })
    void shouldReturnNotNullProcessStep(String mockedString){
        ProcessStep processStep = new ProcessStep(mockedString,new Label(List.of(new LangContent())));
        assertNotNull(processStep);
    }

}