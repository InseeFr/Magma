package fr.insee.rmes.services.concepts;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.List;

@Service
public interface ConceptsServices {
    Object getDetailedConcept(String id) throws RmesException;

    List getAllConcepts() throws RmesException;
}
