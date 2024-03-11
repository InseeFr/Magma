package fr.insee.rmes.utils.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


@Component

@PropertySource("classpath:rmeswsgi-magma.properties")
public class Config {

	private static final Logger LOG = LoggerFactory.getLogger(Config.class);


	@Autowired
	private Environment env;

	public static String CONCEPTS_BASE_URI;
	public static String STRUCTURES_BASE_URI;
	public static String CODELISTS_BASE_URI;
	public static String DATASETS_BASE_URI;
	public static String BASE_URI_GESTION;
	public static String LG1;
	public static String LG2;

	public static String DATASETS_URL;
	public static Integer PERPAGE;
	public static String BASE_GRAPH;
	public static String DATASETS_GRAPH;
	public static String CODELIST_GRAPH;
	public static String CONCEPTS_GRAPH;
	public static String ADMS_GRAPH;
	public static String STRUCTURES_COMPONENTS_GRAPH;
	public static String STRUCTURES_GRAPH;
	public static String STRUCTURES_COMPONENTS_BASE_URI;
	public static String OPERATIONS_SERIES_GRAPH;
	public static String OPERATIONS_BASE_URI;

	public static String ORGANISATIONS_GRAPH;

	public static String ONTOLOGIES_BASE_URI;

	public static String BAUHAUS_URL;

	@Value("${fr.insee.rmes.magma.force.ssl}")
	private boolean requiresSsl = false;

	@Value("${fr.insee.rmes.magma.collections.baseURI}")
	private String collectionsBaseUri;

	@Value("${fr.insee.rmes.magma.operations.families.baseURI}")
	private String opFamiliesBaseUri;

	@Value("${fr.insee.rmes.magma.operations.series.baseURI}")
	private String opSeriesBaseUri;

	@Value("${fr.insee.rmes.magma.products.baseURI}")
	private String productsBaseUri;

	@Value("${fr.insee.rmes.magma.documentations.baseURI}")
	private String documentationsBaseUri;

	@Value("${fr.insee.rmes.magma.documents.baseURI}")
	private String documentsBaseUri;

	@Value("${fr.insee.rmes.magma.links.baseURI}")
	private String linksBaseUri;

	@Value("${fr.insee.rmes.magma.documentation.geographie.baseURI}")
	private String documentationsGeoBaseUri;

	public boolean isRequiresSsl() {
		return requiresSsl;
	}


	public void init() {
		CONCEPTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.concepts.baseURI");
		STRUCTURES_BASE_URI = env.getProperty("fr.insee.rmes.magma.structures.baseURI");
		CODELISTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.codeLists.baseURI");
		DATASETS_BASE_URI= env.getProperty("fr.insee.rmes.magma.datasets.baseURI");
		BASE_URI_GESTION = env.getProperty("fr.insee.rmes.magma.gestion.baseURI");
		LG1 = env.getProperty("fr.insee.rmes.magma.lg1");
		LG2 = env.getProperty("fr.insee.rmes.magma.lg2");
		PERPAGE = Integer.valueOf(env.getProperty("fr.insee.rmes.magma.perPage"));
		BASE_GRAPH = env.getProperty("fr.insee.rmes.magma.baseGraph");
		DATASETS_GRAPH=env.getProperty("fr.insee.rmes.magma.datasets.graph") ;
		DATASETS_URL = env.getProperty("fr.insee.rmes.magma.datasets.url") ;
		CODELIST_GRAPH = env.getProperty("fr.insee.rmes.magma.codeLists.graph");
		CONCEPTS_GRAPH = env.getProperty("fr.insee.rmes.magma.concepts.graph");
		ADMS_GRAPH = env.getProperty("fr.insee.rmes.magma.adms.graph");
		STRUCTURES_COMPONENTS_GRAPH = env.getProperty("fr.insee.rmes.magma.structures.components.graph");
		STRUCTURES_GRAPH = env.getProperty("fr.insee.rmes.magma.structures.graph");
		STRUCTURES_COMPONENTS_BASE_URI= env.getProperty("fr.insee.rmes.magma.structures.components.baseURI");
		OPERATIONS_SERIES_GRAPH = env.getProperty("fr.insee.rmes.magma.operations.graph");
		OPERATIONS_BASE_URI = env.getProperty("fr.insee.rmes.magma.operations.baseURI");
		ORGANISATIONS_GRAPH = env.getProperty("fr.insee.rmes.magma.organisations.graph");
		ONTOLOGIES_BASE_URI = env.getProperty("fr.insee.rmes.magma.ontologies.baseURI");
		BAUHAUS_URL = env.getProperty("fr.insee.rmes.magma.bauhaus.url");

		listStaticFieldsValue();
	}

	private void listStaticFieldsValue() {

		LOG.info("=========== Config class static fields ==============");
		
		Field[] declaredFields = Config.class.getDeclaredFields();

		for (Field currentField : declaredFields) {
			if (Modifier.isStatic(currentField.getModifiers())) {
				try {
					LOG.info("{} {}",currentField.getName(), currentField.get(null) );
				} catch (IllegalArgumentException | IllegalAccessException iac) {
					LOG.error("Error retrieving Config Values");
				}

			}

		}
		
		LOG.info("======================================================");


	}

	public String getConceptsBaseUri() {
		return CONCEPTS_BASE_URI;
	}

	public String getStructuresBaseUri() {
		return STRUCTURES_BASE_URI;
	}

	public String getCodelistsBaseUri() {
		return CODELISTS_BASE_URI;
	}

	public String getDatasetsBaseUri() {
		return DATASETS_BASE_URI;
	}

	public String getBaseUriGestion() {
		return BASE_URI_GESTION;
	}

	public String getLG1() {
		return LG1;
	}

	public String getLG2() {
		return LG2;
	}

	public String getBaseGraph() {
		return BASE_GRAPH;
	}

	public String getDatasetsGraph() {
		return DATASETS_GRAPH;
	}

	public String getCodelistGraph() {
		return CODELIST_GRAPH;
	}

	public String getConceptsGraph() {
		return CONCEPTS_GRAPH;
	}

	public String getStructuresComponentsGraph() {
		return STRUCTURES_COMPONENTS_GRAPH;
	}

	public String getStructuresGraph() {
		return STRUCTURES_GRAPH;
	}

	public String getStructuresComponentsBaseUri() {
		return STRUCTURES_COMPONENTS_BASE_URI;
	}

	public String getOperationsSeriesGraph() {
		return OPERATIONS_SERIES_GRAPH;
	}

	public String getOperationsBaseUri() {
		return OPERATIONS_BASE_URI;
	}

	public String getCollectionsBaseUri() {
		return collectionsBaseUri;
	}

	public String getOpFamiliesBaseUri() {
		return opFamiliesBaseUri;
	}

	public String getOpSeriesBaseUri() {
		return opSeriesBaseUri;
	}

	public String getProductsBaseUri() {
		return productsBaseUri;
	}

	public String getDocumentationsBaseUri() {
		return documentationsBaseUri;
	}

	public String getDocumentsBaseUri() {
		return documentsBaseUri;
	}

	public String getLinksBaseUri() {
		return linksBaseUri;
	}

	public String getDocumentationsGeoBaseUri() {
		return documentationsGeoBaseUri;
	}

}
