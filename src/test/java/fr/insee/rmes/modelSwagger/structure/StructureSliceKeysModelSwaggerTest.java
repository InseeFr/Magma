package fr.insee.rmes.modelSwagger.structure;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class StructureSliceKeysModelSwaggerTest {

    Map<String, Object> additionalProperties = Map.of("key1", "value2","key2", "value2");

    StructureSliceKeysModelSwagger structure = new StructureSliceKeysModelSwagger();

    List<String> id = List.of("firstMockedId","secondMockedId");
    List<Label> label = List.of(new Label());
    String type= "mockedType";

    @Test
    void shouldCheckStructureAttributes(){
        structure.setLabel(label);
        structure.setType(type);
        structure.setComposantsId(id);
        structure.setAdditionalProperties(additionalProperties);

        assertTrue(label==structure.getLabel() &&
                Objects.equals(type, structure.getType()) &&
                id == structure.getComposantsId() &&
                structure.getAdditionalProperties()==additionalProperties);

    }









}