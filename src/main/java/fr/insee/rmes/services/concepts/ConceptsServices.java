package fr.insee.rmes.services.concepts;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public interface ConceptsServices {
    String getDetailedConcept(String id) throws RmesException;

    String getAllConcepts() throws RmesException;
}
