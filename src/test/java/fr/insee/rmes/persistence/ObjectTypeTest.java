package fr.insee.rmes.persistence;

import fr.insee.rmes.persistence.ontologies.*;
import fr.insee.rmes.utils.Constants;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class ObjectTypeTest {

    List<ObjectType> objects = List.of(
            ObjectType.CONCEPT,
            ObjectType.COLLECTION,
            ObjectType.FAMILY,
            ObjectType.SERIES,
            ObjectType.OPERATION,
            ObjectType.INDICATOR,
            ObjectType.DOCUMENTATION,
            ObjectType.DOCUMENT,
            ObjectType.LINK,
            ObjectType.GEO_STAT_TERRITORY,
            ObjectType.ORGANIZATION,
            ObjectType.STRUCTURE,
            ObjectType.CODE_LIST,
            ObjectType.MEASURE_PROPERTY,
            ObjectType.ATTRIBUTE_PROPERTY,
            ObjectType.DIMENSION_PROPERTY,
            ObjectType.UNDEFINED);

    @Test
    void shouldReturnLabelTypeForAllEnum(){

        List<String> labelTypes = List.of(
                Constants.CONCEPT,
                Constants.COLLECTION,
                Constants.FAMILY,
                "series",
                "operation",
                "indicator",
                "documentation",
                Constants.DOCUMENT,
                "link",
                "geoFeature",
                "organization",
                "structure",
                Constants.CODELIST,
                "measureProperty",
                "attributeProperty",
                "dimensionProperty",
                Constants.UNDEFINED
                );

        HashSet<Boolean> set = new HashSet<>();

        for (int i=0 ; i < objects.size() ; i++){
            boolean value = Objects.equals(objects.get(i).getLabelType(), labelTypes.get(i));
            set.add(value);
        }

        assertTrue(set.size()==1 && set.contains(true));
    }

    @Test
    void shouldReturnUriTypeForConcept(){
        assertTrue(ObjectType.CONCEPT.getUri()==SKOS.CONCEPT &&
                ObjectType.COLLECTION.getUri()== SKOS.COLLECTION &&
                ObjectType.FAMILY.getUri()== INSEE.FAMILY &&
                ObjectType.SERIES.getUri()==INSEE.SERIES &&
                ObjectType.OPERATION.getUri()==INSEE.OPERATION &&
                ObjectType.INDICATOR.getUri()== INSEE.INDICATOR &&
                ObjectType.DOCUMENTATION.getUri()==SDMX_MM.METADATA_REPORT &&
                ObjectType.DOCUMENT.getUri()==FOAF.DOCUMENT &&
                ObjectType.LINK.getUri()==FOAF.DOCUMENT &&
                ObjectType.GEO_STAT_TERRITORY.getUri()==GEO.FEATURE &&
                ObjectType.ORGANIZATION.getUri()==ORG.ORGANIZATION &&
                ObjectType.STRUCTURE.getUri()==QB.DATA_STRUCTURE_DEFINITION &&
                ObjectType.CODE_LIST.getUri()== QB.CODE_LIST &&
                ObjectType.MEASURE_PROPERTY.getUri()==QB.MEASURE_PROPERTY &&
                ObjectType.ATTRIBUTE_PROPERTY.getUri()==QB.ATTRIBUTE_PROPERTY &&
                ObjectType.DIMENSION_PROPERTY.getUri()==QB.DIMENSION_PROPERTY &&
                ObjectType.UNDEFINED.getUri()==null);
    }

}