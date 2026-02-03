package fr.insee.rmes.magma.gestion.old.services.concepts;

import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import tools.jackson.core.JacksonException;


public interface ConceptsServices {
    String getDetailedConcept(String id) throws RmesException, JacksonException;

    String getAllConcepts(String dateMiseAJour) throws RmesException;

    String getDetailedConceptDateMAJ(String id) throws RmesException, JacksonException;

    String getCollectionOfConcepts(String id) throws RmesException, JacksonException;

    String getSetOfConceptsInACollection(String id) throws RmesException, JacksonException;

}
