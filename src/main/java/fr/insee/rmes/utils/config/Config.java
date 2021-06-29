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
	@Value("${fr.insee.rmes.magma.sesame.gestion.baseURI}")
	public static String BASE_URI_GESTION;
	
	private Config() {
		throw new IllegalStateException("Utility class");
	}

}