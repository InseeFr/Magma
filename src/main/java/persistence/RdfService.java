package persistence;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class RdfService {

	@Autowired
	protected RepositoryGestion repoGestion;

}
