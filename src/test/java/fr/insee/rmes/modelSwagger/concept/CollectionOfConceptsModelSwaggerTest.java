package fr.insee.rmes.modelSwagger.concept;

import fr.insee.rmes.model.concept.ConceptDefCourte;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CollectionOfConceptsModelSwaggerTest {

    List<LangContent> label = List.of(new LangContent(),new LangContent());
    List<ConceptDefCourte> description = List.of(new ConceptDefCourte(),new ConceptDefCourte());

    CollectionOfConceptsModelSwagger firstConcept = new CollectionOfConceptsModelSwagger("uri","id","dateOfUpdate");
    CollectionOfConceptsModelSwagger secondConcept = new CollectionOfConceptsModelSwagger();

    @Test
    void shouldCheckAttributeValueWhenInitialize(){
        firstConcept.setLabel(label);
        secondConcept.setDescription(description);
        assertTrue(firstConcept.getLabel()==label && secondConcept.getDescription()==description);

    }

}