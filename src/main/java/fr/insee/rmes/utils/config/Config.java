package fr.insee.rmes.utils.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class Config {

	private static final  Logger logger = LogManager.getLogger(Config.class);
	
	public static String APP_HOST = "";

	public static String ENV = "";

	public static boolean REQUIRES_SSL = false;

	public static String DEFAULT_CONTRIBUTOR = "";
	public static String DEFAULT_MAIL_SENDER = "";
	public static String MAX_LENGTH_SCOPE_NOTE = "";

	@Value("${fr.insee.rmes.magma.lg1}")
	public static String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public static String LG2;

	public static String BASE_GRAPH = "";


	public static String PASSWORD_GESTIONNAIRE = "";
	public static String PASSWORD_PRODUCTEUR = "";

	public static String CONCEPTS_GRAPH = "";
	public static String CONCEPTS_SCHEME = "";
	@Value("${fr.insee.rmes.magma.concepts.baseURI}")
	public static String CONCEPTS_BASE_URI;
	public static String COLLECTIONS_BASE_URI = "";

	public static String CLASSIF_FAMILIES_GRAPH = "";

	public static String OPERATIONS_GRAPH = "";
	public static String OPERATIONS_BASE_URI = "";
	public static String OP_SERIES_BASE_URI = "";
	public static String OP_FAMILIES_BASE_URI = "";
	public static String DOCUMENTATIONS_BASE_URI = "";
	public static String DOCUMENTATIONS_GRAPH = "";
	public static String DOCUMENTS_BASE_URI = "";

	public static String MSD_GRAPH= "";
	public static String MSD_CONCEPTS_GRAPH= "";
	public static String DOCUMENTATIONS_GEO_GRAPH = "";
	public static String DOCUMENTATIONS_GEO_BASE_URI = "";

	public static String LINKS_BASE_URI = "";
	public static String DOCUMENTS_GRAPH = "";
	public static String DOCUMENTS_STORAGE_GESTION = "";
	public static String DOCUMENTS_STORAGE_PUBLICATION_EXTERNE = "";
	public static String DOCUMENTS_STORAGE_PUBLICATION_INTERNE = "";
	public static String DOCUMENTS_BASEURL = "";

	public static String PRODUCTS_GRAPH = "";

	public static String PRODUCTS_BASE_URI = "";

	// STRUCTURE
	public static String STRUCTURES_GRAPH = "";
	public static String STRUCTURES_BASE_URI = "";
	public static String STRUCTURES_COMPONENTS_GRAPH = "";
	public static String STRUCTURES_COMPONENTS_BASE_URI = "";


	public static String CODELIST_GRAPH = "";
	public static String CODE_LIST_BASE_URI = "";


	public static String ORGANIZATIONS_GRAPH = "";
	public static String ORG_INSEE_GRAPH = "";
	
	public static String GEOGRAPHY_GRAPH = "";

	public static String SESAME_SERVER_GESTION = "";
	public static String REPOSITORY_ID_GESTION = "";
	public static String BASE_URI_GESTION = "";

	public static String SPOC_SERVICE_URL = "";
	public static String SPOC_USER = "";
	public static String SPOC_PASSWORD = "";

	public static String BROKER_URL = "";
	public static String BROKER_USER = "";
	public static String BROKER_PASSWORD = "";

	public static String LDAP_URL = "";

	public static String IGESA_URL = "";
	public static String IGESA_APP_ID = "";
	public static String IGESA_USER = "";
	public static String IGESA_PASSWORD = "";

	public static String SWAGGER_HOST = "";
	public static String SWAGGER_BASEPATH = "";
	public static String SWAGGER_URL = "";


	private Config() {
		throw new IllegalStateException("Utility class");
	}

	public static void setConfig(Environment env) {

		//Initialize general configurations
		Config.APP_HOST = env.getProperty("fr.insee.rmes.magma.appHost");

		Config.ENV = env.getProperty("fr.insee.rmes.magma.env");
		Config.REQUIRES_SSL = Boolean.valueOf(env.getProperty("fr.insee.rmes.magma.force.ssl"));

		Config.LG1 = env.getProperty("fr.insee.rmes.magma.lg1");
		Config.LG2 = env.getProperty("fr.insee.rmes.magma.lg2");

		Config.BASE_GRAPH = env.getProperty("fr.insee.rmes.magma.baseGraph");

		Config.PASSWORD_GESTIONNAIRE = env.getProperty("fr.insee.rmes.magma.gestionnaire.password");
		Config.PASSWORD_PRODUCTEUR = env.getProperty("fr.insee.rmes.magma.producteur.password");

		Config.SESAME_SERVER_GESTION = env.getProperty("fr.insee.rmes.magma.gestion.rdfServer");
		Config.REPOSITORY_ID_GESTION = env.getProperty("fr.insee.rmes.magma.gestion.repository");	

		Config.BASE_URI_GESTION = env.getProperty("fr.insee.rmes.magma.gestion.baseURI");
		
		//Initialize concepts configuration
		readConfigForConcepts(env);

		//Initialize Classifications
		Config.CLASSIF_FAMILIES_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.classifications.families.graph");

		//Initialize Operations
		readConfigForOperations(env);

		//Initialize Structures
		readConfigForStructures(env);

		//Initialize Code lists
		readConfigForCodeLists(env);

		//Initialize Organizations
		Config.ORGANIZATIONS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.organisations.graph");
		Config.ORG_INSEE_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.insee.graph");
		
		//Initialize Geography
		Config.GEOGRAPHY_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.geographie.graph");
		
		
		//Initialize other services

		Config.SPOC_SERVICE_URL = env.getProperty("fr.insee.rmes.magma.spoc.url");
		Config.SPOC_USER = env.getProperty("fr.insee.rmes.magma.spoc.user");
		Config.SPOC_PASSWORD = env.getProperty("fr.insee.rmes.magma.spoc.password");

		Config.BROKER_URL = env.getProperty("fr.insee.rmes.magma.broker.url");
		Config.BROKER_USER = env.getProperty("fr.insee.rmes.magma.broker.user");
		Config.BROKER_PASSWORD = env.getProperty("fr.insee.rmes.magma.broker.password");

		Config.LDAP_URL = env.getProperty("fr.insee.rmes.magma.ldap.url");

		Config.IGESA_URL = env.getProperty("fr.insee.rmes.magma.igesa.url");
		Config.IGESA_APP_ID = env.getProperty("fr.insee.rmes.magma.igesa.id");
		Config.IGESA_USER = env.getProperty("fr.insee.rmes.magma.igesa.user");
		Config.IGESA_PASSWORD = env.getProperty("fr.insee.rmes.magma.igesa.password");

		Config.SWAGGER_HOST = env.getProperty("fr.insee.rmes.magma.api.host");
		Config.SWAGGER_BASEPATH = env.getProperty("fr.insee.rmes.magma.api.basepath");
		Config.SWAGGER_URL = (Config.REQUIRES_SSL ? "https" : "http") + "://" + Config.SWAGGER_HOST + "/" + Config.SWAGGER_BASEPATH;
	}

	private static void readConfigForCodeLists(Environment env) {
		Config.CODE_LIST_BASE_URI = env.getProperty("fr.insee.rmes.magma.codeList.baseURI");
		Config.CODELIST_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.codelists.graph");
	}

	private static void readConfigForConcepts(Environment env) {
		Config.DEFAULT_CONTRIBUTOR = env.getProperty("fr.insee.rmes.magma.concepts.defaultContributor");
		Config.DEFAULT_MAIL_SENDER = env.getProperty("fr.insee.rmes.magma.concepts.defaultMailSender");
		Config.MAX_LENGTH_SCOPE_NOTE = env.getProperty("fr.insee.rmes.magma.concepts.maxLengthScopeNote");


		Config.CONCEPTS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.concepts.graph");
		Config.CONCEPTS_SCHEME = env.getProperty("fr.insee.rmes.magma.concepts.scheme");
		Config.CONCEPTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.concepts.baseURI");
		Config.COLLECTIONS_BASE_URI = env.getProperty("fr.insee.rmes.magma.collections.baseURI");
	}

	private static void readConfigForOperations(Environment env) {
		Config.OPERATIONS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.operations.graph");
		Config.OPERATIONS_BASE_URI = env.getProperty("fr.insee.rmes.magma.operations.baseURI");
		Config.OP_SERIES_BASE_URI = env.getProperty("fr.insee.rmes.magma.operations.series.baseURI");
		Config.OP_FAMILIES_BASE_URI = env.getProperty("fr.insee.rmes.magma.operations.families.baseURI");

		Config.DOCUMENTATIONS_BASE_URI = env.getProperty("fr.insee.rmes.magma.documentations.baseURI");
		Config.DOCUMENTATIONS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.documentations.graph");
		Config.MSD_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.documentations.msd.graph");
		Config.MSD_CONCEPTS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.documentations.concepts.graph");

		Config.DOCUMENTATIONS_GEO_BASE_URI = env.getProperty("fr.insee.rmes.magma.documentation.geographie.baseURI");
		Config.DOCUMENTATIONS_GEO_GRAPH = BASE_GRAPH +  env.getProperty("fr.insee.rmes.magma.documentation.geographie.graph");

		Config.DOCUMENTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.documents.baseURI");
		Config.LINKS_BASE_URI = env.getProperty("fr.insee.rmes.magma.links.baseURI");
		Config.DOCUMENTS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.documents.graph");
		Config.DOCUMENTS_STORAGE_GESTION = env.getProperty("fr.insee.rmes.magma.storage.document.gestion");
		Config.DOCUMENTS_STORAGE_PUBLICATION_EXTERNE = env.getProperty("fr.insee.rmes.magma.storage.document.publication");
		Config.DOCUMENTS_STORAGE_PUBLICATION_INTERNE = env.getProperty("fr.insee.rmes.magma.storage.document.publication.interne");
		Config.DOCUMENTS_BASEURL = env.getProperty("fr.insee.web4g.baseURL");


		Config.PRODUCTS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.products.graph");
		Config.PRODUCTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.products.baseURI");
	}

	private static void readConfigForStructures(Environment env) {
		Config.STRUCTURES_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.structures.graph");
		Config.STRUCTURES_BASE_URI = env.getProperty("fr.insee.rmes.magma.structures.baseURI");
		Config.STRUCTURES_COMPONENTS_GRAPH = BASE_GRAPH + env.getProperty("fr.insee.rmes.magma.structures.components.graph");
		Config.STRUCTURES_COMPONENTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.structures.components.baseURI");
	}

	public static void printMajorConfig() {
		logger.info("*********************** CONFIG USED ***********************************");

		logger.info("ENV : {}", ENV);
		
		logger.info("SERVEUR RDF : ");
		
		logger.info("   GESTION : {} _ REPO : {} _ BASEURI : {}",SESAME_SERVER_GESTION,REPOSITORY_ID_GESTION, BASE_URI_GESTION);
		
		logger.info("DOCUMENT STORAGE : ");
		
		logger.info("   GESTION : {}", DOCUMENTS_STORAGE_GESTION);
		logger.info("   PUB EXTERNE : {}", DOCUMENTS_STORAGE_PUBLICATION_EXTERNE);
		logger.info("   PUB INTERNE : {}", DOCUMENTS_STORAGE_PUBLICATION_INTERNE);


		
		logger.info("*********************** END CONFIG USED ***********************************");
		
		
	}

}