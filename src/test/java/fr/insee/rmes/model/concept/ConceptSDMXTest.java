package fr.insee.rmes.model.concept;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConceptSDMXTest {

    String agence = "mockedAgence";
    String id = "mockedId";

    ConceptSDMX firstConceptSDMX = new ConceptSDMX(agence,id) ;
    ConceptSDMX secondConceptSDMX = new ConceptSDMX();

    @ParameterizedTest
    @ValueSource(strings = { "mockedAgence1", "mockedAgence2", "mockedAgence3" })
    void shouldCheckAgence(String mockedString){
        firstConceptSDMX.setAgence(mockedString);
        secondConceptSDMX.setAgence(mockedString);
        assertTrue(Objects.equals(firstConceptSDMX.getAgence(), secondConceptSDMX.getAgence()) &&
                Objects.equals(firstConceptSDMX.getAgence(), mockedString));
    }


    @ParameterizedTest
    @ValueSource(strings = { "mockedId1", "mockedId2", "mockedId3" })
    void shouldCheckId(String mockedString){
        firstConceptSDMX.setId(mockedString);
        secondConceptSDMX.setId(mockedString);
        assertTrue(Objects.equals(firstConceptSDMX.getId(), secondConceptSDMX.getId()) &&
                        Objects.equals(firstConceptSDMX.getId(), mockedString));
    }

}