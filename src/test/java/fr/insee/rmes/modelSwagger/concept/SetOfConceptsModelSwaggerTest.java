package fr.insee.rmes.modelSwagger.concept;

import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SetOfConceptsModelSwaggerTest {

    SetOfConceptsModelSwagger model = new SetOfConceptsModelSwagger();

    @Test
    void shouldCheckGetterAndSetter(){
        var beforeModelUri= model.getUri();
        var beforeModelId=model.getId();
        model.setId("mockedID");
        model.setUri("mockedURI");
        var afterModelUri= model.getUri();
        var afterModelId = model.getId();
        assertTrue(!Objects.equals(beforeModelUri, afterModelUri) && !Objects.equals(beforeModelId, afterModelId));

    }

}