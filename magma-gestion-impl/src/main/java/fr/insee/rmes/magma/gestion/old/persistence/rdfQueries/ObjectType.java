package fr.insee.rmes.magma.gestion.old.persistence.rdfQueries;

import fr.insee.rmes.magma.gestion.old.persistence.ontologies.ORG;
import fr.insee.rmes.magma.gestion.old.utils.Constants;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.vocabulary.SKOS;

import java.util.HashMap;
import java.util.Map;

public enum ObjectType {
	CONCEPT("concept", SKOS.CONCEPT,  Config.CONCEPTS_BASE_URI),
	ORGANIZATION("organization",ORG.ORGANIZATION, ""),
	UNDEFINED(Constants.UNDEFINED,null, "");
	
	private String labelType;
	private IRI uri;
	private String baseUri;

	ObjectType(String labelType, IRI uri, String baseUri){
		this.labelType=labelType;
		this.uri=uri;
		this.baseUri=baseUri;
	}

	public IRI getUri() {
		return this.uri;
	}
	
	public String getLabelType() {
		return this.labelType;
	}
	
	public String getBaseUri() {
		return Config.BASE_URI_GESTION + this.baseUri;
	}
	
	private static Map<String, ObjectType> lookupLabel = new HashMap<>();
	private static Map<IRI, ObjectType> lookupUri = new HashMap<>();
	
	static {
		// Populate out lookup when enum is created
		for (ObjectType e : ObjectType.values()) {
			lookupLabel.put(e.getLabelType(), e);
			lookupUri.put(e.getUri(), e);
		}
	}
	
	/**
	 * Get Enum type by label
	 * @param label
	 * @return
	 */
	public static ObjectType getEnum(String labelType) {
		return lookupLabel.get(labelType)!=null ? lookupLabel.get(labelType): UNDEFINED;
	}
	
	/**
	 * Get URI by label
	 * @param label
	 * @return
	 */
	public static IRI getUri(String labelType) {
		return getEnum(labelType).uri;
	}
	
	/**
	 * Get URI by label
	 * @param label
	 * @return
	 */
	public static String getBaseUri(String labelType) {
		return getEnum(labelType).baseUri;
	}

	/**
	 * Get Enum type by URI
	 * @param labelType
	 * @return
	 */
	public static ObjectType getEnum(IRI uri) {
		return lookupUri.get(uri)!=null ? lookupUri.get(uri): UNDEFINED;
	}
	
	/**
	 * Get label by URI
	 * @param labelType
	 * @return
	 */
	public static String getLabelType(IRI uri) {
		return getEnum(uri).labelType;
	}

	
	/**
	 * Get label by URI
	 * @param label
	 * @return
	 */
	public static String getCompleteUriGestion(String labelType, String id) {
		String baseUri = getBaseUri(labelType);
		return Config.BASE_URI_GESTION + baseUri + "/" + id;
	}
}
