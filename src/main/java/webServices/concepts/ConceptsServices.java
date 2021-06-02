package webServices.concepts;

import utils.exceptions.RmesException;

public interface ConceptsServices {
    String getDetailedConcept(String id) throws RmesException;

    String getAllConcepts() throws RmesException;
}
