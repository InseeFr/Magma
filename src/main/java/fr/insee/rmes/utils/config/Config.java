package fr.insee.rmes.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Config {

	@Value("${fr.insee.rmes.magma.concepts.baseURI}")
	public static String CONCEPTS_BASE_URI;
	@Value("${fr.insee.rmes.magma.structures.baseURI}")
	public static String STRUCTURES_BASE_URI;
	@Value("${fr.insee.rmes.magma.codeLists.baseURI}")
	public static String CODELISTS_BASE_URI;
	@Value("${fr.insee.rmes.magma.gestion.baseURI}")
	public static String BASE_URI_GESTION;
	
	@Value("${fr.insee.rmes.magma.lg1}")
	public static String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public static String LG2;	
	
	@Value("${fr.insee.rmes.magma.baseGraph}")
	public static String BASE_GRAPH;
	
	@Value("${fr.insee.rmes.magma.codeLists.graph}")
	public static String CODELIST_GRAPH; 
	


	@Value("${fr.insee.rmes.magma.concepts.graph}")
	public static String CONCEPTS_GRAPH;
	

	@Value("${fr.insee.rmes.magma.structures.graph}")
	public static String STRUCTURES_COMPONENTS_GRAPH ;
	@Value("${fr.insee.rmes.magma.structures.graph}")
	public static String STRUCTURES_GRAPH;
	
	private Config() {
		throw new IllegalStateException("Utility class");
	}

}