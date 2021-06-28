package fr.insee.rmes.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class Config {

	public static final Object CODELIST_GRAPH = null; //TODO

	public static final String BASE_URI_GESTION = null; //TODO
	
	@Value("${fr.insee.rmes.magma.lg1}")
	public static String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public static String LG2;

	@Value("${fr.insee.rmes.magma.concepts.baseURI}")
	public static String CONCEPTS_BASE_URI;


	private Config() {
		throw new IllegalStateException("Utility class");
	}

}