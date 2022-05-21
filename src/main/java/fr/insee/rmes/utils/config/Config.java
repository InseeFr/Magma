package fr.insee.rmes.utils.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

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
	public static String BASE_GRAPH;
	public static String DATASETS_GRAPH;
	public static String CODELIST_GRAPH;
	public static String CONCEPTS_GRAPH;
	public static String STRUCTURES_COMPONENTS_GRAPH;
	public static String STRUCTURES_GRAPH;
	public static String STRUCTURES_COMPONENTS_BASE_URI;
	public static String OPERATIONS_SERIES_GRAPH;
	public static String OPERATIONS_BASE_URI;


	public void init() {
		CONCEPTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.concepts.baseURI");
		STRUCTURES_BASE_URI = env.getProperty("fr.insee.rmes.magma.structures.baseURI");
		CODELISTS_BASE_URI = env.getProperty("fr.insee.rmes.magma.codeLists.baseURI");
		DATASETS_BASE_URI= env.getProperty("fr.insee.rmes.magma.datasets.baseURI");
		BASE_URI_GESTION = env.getProperty("fr.insee.rmes.magma.gestion.baseURI");
		LG1 = env.getProperty("fr.insee.rmes.magma.lg1");
		LG2 = env.getProperty("fr.insee.rmes.magma.lg2");
		BASE_GRAPH = env.getProperty("fr.insee.rmes.magma.baseGraph");
		DATASETS_GRAPH=env.getProperty("fr.insee.rmes.magma.datasets.graph") ;
		CODELIST_GRAPH = env.getProperty("fr.insee.rmes.magma.codeLists.graph");
		CONCEPTS_GRAPH = env.getProperty("fr.insee.rmes.magma.concepts.graph");
		STRUCTURES_COMPONENTS_GRAPH = env.getProperty("fr.insee.rmes.magma.structures.components.graph");
		STRUCTURES_GRAPH = env.getProperty("fr.insee.rmes.magma.structures.graph");
		STRUCTURES_COMPONENTS_BASE_URI= env.getProperty("fr.insee.rmes.magma.structures.components.baseURI");
		OPERATIONS_SERIES_GRAPH = env.getProperty("fr.insee.rmes.magma.operations.graph");
		OPERATIONS_BASE_URI = env.getProperty("fr.insee.rmes.magma.operations.baseURI");


		listStaticFieldsValue();
	}

	private void listStaticFieldsValue() {

		LOG.info("=========== Config class static fields ==============");
		
		Field[] declaredFields = Config.class.getDeclaredFields();

		for (Field currentField : declaredFields) {
			if (Modifier.isStatic(currentField.getModifiers())) {
				try {
					LOG.info(currentField.getName() + "  " + (currentField.get(null)).toString());
				} catch (IllegalArgumentException | IllegalAccessException iac) {
					LOG.error("Error retrieving Config Values");
				}

			}

		}
		
		LOG.info("======================================================");


	}
}
