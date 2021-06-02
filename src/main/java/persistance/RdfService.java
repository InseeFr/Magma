package persistance;

import org.springframework.beans.factory.annotation.Autowired;

import fr.insee.rmes.bauhaus_services.rdf_utils.RepositoryGestion;

public abstract class RdfService {

	@Autowired
	protected RepositoryGestion repoGestion;

}
