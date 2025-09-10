package fr.insee.rmes.modelSwagger.dataset;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class IdLabelTest {

    @Test
    void shouldCheckAttributesValue(){

        List<LangContent> label = List.of(new LangContent(),new LangContent());
        IdLabel firstIdLabel = new IdLabel();
        IdLabel secondIdLabel = new IdLabel("mockedId",label);

        firstIdLabel.setId("mockedId");
        firstIdLabel.setLabel(label);

        assertTrue(Objects.equals(firstIdLabel.getId(), secondIdLabel.getId()) &&
                Objects.equals(firstIdLabel.getId(), "mockedId") &&
                        Objects.equals(firstIdLabel.getLabel(), label) &&
                        Objects.equals(firstIdLabel.getLabel(),secondIdLabel.getLabel())
                );

    }

}