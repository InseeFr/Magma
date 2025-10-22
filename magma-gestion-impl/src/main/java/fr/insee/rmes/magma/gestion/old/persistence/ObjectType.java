package fr.insee.rmes.magma.gestion.old.persistence;

import fr.insee.rmes.magma.gestion.old.persistence.ontologies.*;
import fr.insee.rmes.magma.gestion.old.utils.Constants;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import fr.insee.rmes.persistence.ontologies.*;
import jakarta.annotation.PostConstruct;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.model.vocabulary.SKOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;

public enum ObjectType {
    CONCEPT(Constants.CONCEPT, SKOS.CONCEPT, Config::getConceptsBaseUri),
    COLLECTION(Constants.COLLECTION, SKOS.COLLECTION, Config::getCollectionsBaseUri),
    FAMILY(Constants.FAMILY, INSEE.FAMILY, Config::getOpFamiliesBaseUri),
    SERIES("series", INSEE.SERIES, Config::getOpSeriesBaseUri),
    OPERATION("operation", INSEE.OPERATION, Config::getOperationsBaseUri),
    INDICATOR("indicator", INSEE.INDICATOR, Config::getProductsBaseUri),
    DOCUMENTATION("documentation", SDMX_MM.METADATA_REPORT, Config::getDocumentationsBaseUri),
    DOCUMENT(Constants.DOCUMENT, FOAF.DOCUMENT, Config::getDocumentsBaseUri),
    LINK("link", FOAF.DOCUMENT, Config::getLinksBaseUri),
    GEO_STAT_TERRITORY("geoFeature", GEO.FEATURE, Config::getDocumentationsGeoBaseUri),
    ORGANIZATION("organization", ORG.ORGANIZATION, c->""),
    STRUCTURE("structure", QB.DATA_STRUCTURE_DEFINITION, Config::getStructuresBaseUri),
    CODE_LIST(Constants.CODELIST, QB.CODE_LIST, Config::getCodelistsBaseUri),
    MEASURE_PROPERTY("measureProperty",QB.MEASURE_PROPERTY, c-> c.getStructuresComponentsBaseUri()  + "mesure"),
    ATTRIBUTE_PROPERTY("attributeProperty", QB.ATTRIBUTE_PROPERTY, c->c.getStructuresComponentsBaseUri() + "attribut"),
    DIMENSION_PROPERTY("dimensionProperty", QB.DIMENSION_PROPERTY, c->c.getStructuresComponentsBaseUri() + "dimension"),
    UNDEFINED(Constants.UNDEFINED, null, c -> "");

    private final String labelType;
    private final IRI uri;
    private final Function<Config, String> getBaseUriFromConfig;

    ObjectType(String labelType, IRI uri, Function<Config, String> getBaseUriFromConfig){
        this.labelType =labelType;
        this.uri=uri;
        this.getBaseUriFromConfig = getBaseUriFromConfig;
    }

    private Config config;

    private void setConfig(Config configParam) {
        config = configParam;
    }


    @Component
    public static class ConfigServiceInjector {
        @Autowired
        private Config config;

        @PostConstruct
        public void postConstruct() {
            Arrays.stream(ObjectType.values())
                    .forEach(o->o.setConfig(config));
        }
    }

    public String getLabelType(){
        return labelType;
    }

    public  IRI getUri(){
        return this.uri;
    }

    public  String getBaseUri(){
        return this.getBaseUriFromConfig.apply(this.config);
    }


    public String getBaseUriGestion() {
        return config.getBaseUriGestion() + this.getBaseUri() ;
    }

}
