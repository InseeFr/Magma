package fr.insee.rmes.magma.gestion.old.persistence;

import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleIRI;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.springframework.stereotype.Service;


@Service
public class RdfUtils {

    private RdfUtils(){}


    static ValueFactory factory =  SimpleValueFactory.getInstance();


    public static IRI structureComponentAttributeIRI(String id) {
        return objectIRI(ObjectType.ATTRIBUTE_PROPERTY, id);
    }
    public static IRI structureComponentDimensionIRI(String id) {
        return objectIRI(ObjectType.DIMENSION_PROPERTY, id);
    }

    public static IRI structureComponentMeasureIRI(String id) {
        return objectIRI(ObjectType.MEASURE_PROPERTY, id);
    }
    public static IRI objectIRI(ObjectType objType, String id) {
        return factory.createIRI(objType.getBaseUriGestion() + "/" + id);
    }
    public static String toString(IRI iri) {
        return ((SimpleIRI)iri).toString();
    }

}
