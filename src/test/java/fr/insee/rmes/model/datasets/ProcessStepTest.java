package fr.insee.rmes.model.datasets;

import fr.insee.rmes.modelSwagger.dataset.Label;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProcessStepTest {

    @Test
    void shouldInitializeProcessStep(){
        String id = "mockedId";
        Label label = new Label(List.of(LangContent.lg1("lg1"),LangContent.lg2("lg2")));
        ProcessStep processStep = new ProcessStep(id,label);
        assertNotNull(processStep);
    }
}