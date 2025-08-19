package fr.insee.rmes.persistence.rdfQueries;

import fr.insee.rmes.persistence.ontologies.ORG;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.junit.jupiter.api.Test;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ObjectTypeTest {

    String mockedConcept = "concept";
    String mockedOrganization = "organization";
    String mockedUndefined = "undefined";

    @Test
    void shouldCheckGetUriValue(){
        boolean concept = "http://www.w3.org/2004/02/skos/core#Concept".equals(ObjectType.CONCEPT.getUri().toString());
        boolean organization = "http://www.w3.org/ns/org#Organization".equals(ObjectType.ORGANIZATION.getUri().toString());
        boolean undefined = null==ObjectType.UNDEFINED.getUri();
        assertTrue(concept && organization && undefined);
    }

    @Test
    void shouldCheckGetLabelType(){
        boolean concept = mockedConcept.equals(ObjectType.CONCEPT.getLabelType());
        boolean organization = mockedOrganization.equals(ObjectType.ORGANIZATION.getLabelType());
        boolean undefined =  mockedUndefined.equals(ObjectType.UNDEFINED.getLabelType());
        assertTrue(concept && organization && undefined);
    }

    @Test
    void shouldCheckGetBaseUri(){
        boolean compare= ObjectType.ORGANIZATION.getBaseUri().equals(ObjectType.UNDEFINED.getBaseUri());
        assertTrue(compare);
    }

    @Test
    void shouldCheckGetEnum(){
        boolean concept = ObjectType.getEnum(mockedConcept)==ObjectType.CONCEPT;
        boolean organization = ObjectType.getEnum(mockedOrganization)==ObjectType.ORGANIZATION;
        boolean undefined = ObjectType.getEnum(mockedUndefined)==ObjectType.UNDEFINED;
        assertTrue(concept && organization && undefined);
    }

    @Test
    void shouldCheckGetUri(){
       boolean concept = SKOS.CONCEPT.equals(ObjectType.getUri(mockedConcept));
       boolean organization = ORG.ORGANIZATION.equals(ObjectType.getUri(mockedOrganization));
       assertTrue(concept && organization);
    }

    @Test
    void shouldCheckGetBaseUriWithArguments(){
        assertNull(ObjectType.getBaseUri(mockedConcept));
    }

    @Test
    void shouldCheckGetEnumWithArguments(){
        boolean concept = ObjectType.getEnum(SKOS.CONCEPT)==ObjectType.CONCEPT;
        boolean organization = ObjectType.getEnum(ORG.ORGANIZATION)==ObjectType.ORGANIZATION;
        assertTrue( concept && organization);
    }

    @Test
    void shouldCheckGetLabelTypeWithArguments(){
        boolean concept = Objects.equals(ObjectType.getLabelType(SKOS.CONCEPT), ObjectType.CONCEPT.getLabelType());
        boolean organization = Objects.equals(ObjectType.getLabelType(ORG.ORGANIZATION), ObjectType.ORGANIZATION.getLabelType());
        assertTrue( concept && organization);
    }

}