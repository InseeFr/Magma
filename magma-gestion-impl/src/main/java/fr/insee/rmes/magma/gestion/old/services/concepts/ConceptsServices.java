package fr.insee.rmes.magma.gestion.old.services.concepts;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;



public interface ConceptsServices {
    String getDetailedConcept(String id) throws RmesException, JsonProcessingException;

    String getAllConcepts(String dateMiseAJour) throws RmesException;

    String getDetailedConceptDateMAJ(String id) throws RmesException, JsonProcessingException;

    String getCollectionOfConcepts(String id) throws RmesException, JsonProcessingException;

    String getSetOfConceptsInACollection(String id) throws RmesException, JsonProcessingException;

}
