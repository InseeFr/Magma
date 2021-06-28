package fr.insee.rmes.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Config {

	public static final String BASE_URI_GESTION = null; //TODO
	
	@Value("${fr.insee.rmes.magma.lg1}")
	public static String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public static String LG2;

	@Value("${fr.insee.rmes.magma.concepts.baseURI}")
	public static String CONCEPTS_BASE_URI;

	@Value("${fr.insee.rmes.magma.structures.baseURI}")
	public static String STRUCTURES_BASE_URI;

	@Value("${fr.insee.rmes.magma.codeLists.baseURI}")
	public static String CODELISTS_BASE_URI;
	
	@Value("${fr.insee.rmes.magma.codeLists.graph}")
	public static Object CODELIST_GRAPH; 
	
	
	private Config() {
		throw new IllegalStateException("Utility class");
	}

}