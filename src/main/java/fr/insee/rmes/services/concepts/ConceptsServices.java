package fr.insee.rmes.services.concepts;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;



@Service
public interface ConceptsServices {
    String getDetailedConcept(String id) throws RmesException, JsonProcessingException;

    String getAllConcepts() throws RmesException;

    String getDetailedConceptDateMAJ(String id) throws RmesException, JsonProcessingException;
}
