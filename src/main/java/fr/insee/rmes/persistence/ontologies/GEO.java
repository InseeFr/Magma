package fr.insee.rmes.persistence.ontologies;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleNamespace;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

public class GEO {

	private GEO(){}

    public static final String NAMESPACE = "http://www.opengis.net/ont/geosparql#";

	public static final String PREFIX = "geo";
	
	public static final Namespace NS = new SimpleNamespace(PREFIX, NAMESPACE);
	
	public static final IRI FEATURE;
	
	static {
		final ValueFactory f = SimpleValueFactory.getInstance();

		FEATURE = f.createIRI(NAMESPACE, "Feature");
	
	}

}
