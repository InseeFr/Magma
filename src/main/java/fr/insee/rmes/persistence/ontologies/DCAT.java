package fr.insee.rmes.persistence.ontologies;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Namespace;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleNamespace;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;

public class DCAT {
    public static final String NAMESPACE = "http://www.w3.org/dcat#";

    /**
     * The recommended prefix for the INSEE namespace: "insee"
     */
    public static final String PREFIX = "dcat";

    public static final Namespace NS = new SimpleNamespace(PREFIX, NAMESPACE);
}
