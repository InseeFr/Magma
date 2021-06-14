package fr.insee.rmes.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import fr.insee.rmes.utils.exceptions.RmesException;

public abstract class RdfService {

	@Autowired
	protected RepositoryGestion repoGestion;

	public abstract String getDetailedConcept(String id) throws RmesException ;

	public abstract String getAllConcepts() throws RmesException ;

	
	
}
