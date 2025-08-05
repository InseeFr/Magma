package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class LabelTest {

    @Test
    void shouldCheckAttributesValues(){
        List<LangContent> label = List.of(new LangContent(),new LangContent());
        Label firstLabel = new Label(label);
        assertEquals(label,firstLabel.getLabel());
    }

}