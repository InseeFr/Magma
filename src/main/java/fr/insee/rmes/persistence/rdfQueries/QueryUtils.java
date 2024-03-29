package fr.insee.rmes.persistence.rdfQueries;

import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.insee.rmes.utils.Constants;


public class QueryUtils {

	public static final String PREFIXES =
			"PREFIX dcterms:<http://purl.org/dc/terms/> \n"
					+ "PREFIX xkos:<http://rdf-vocabulary.ddialliance.org/xkos#> \n"
					+ "PREFIX evoc:<http://eurovoc.europa.eu/schema#> \n"
					+ "PREFIX skos:<http://www.w3.org/2004/02/skos/core#> \n"
					+ "PREFIX skosxl:<http://www.w3.org/2008/05/skos-xl#> \n"
					+ "PREFIX dc:<http://purl.org/dc/elements/1.1/> \n"
					+ "PREFIX insee:<http://rdf.insee.fr/def/base#> \n"
					+ "PREFIX geo:<http://www.opengis.net/ont/geosparql#> \n"
					+ "PREFIX igeo:<http://rdf.insee.fr/def/geo#> \n"
					+ "PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
					+ "PREFIX pav:<http://purl.org/pav/> \n"
					+ "PREFIX foaf:<http://xmlns.com/foaf/0.1/> \n"
					+ "PREFIX org:<http://www.w3.org/ns/org#> \n"
					+ "PREFIX prov:<http://www.w3.org/ns/prov#> \n"
					+ "PREFIX xsd:<http://www.w3.org/2001/XMLSchema#> \n"
					+ "PREFIX sdmx-mm:<http://www.w3.org/ns/sdmx-mm#> \n"
					+ "PREFIX qb:<http://purl.org/linked-data/cube#> \n"
					+ "PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#> \n"
					+ "PREFIX dcmitype:<http://purl.org/dc/dcmitype/> \n"
					+ "PREFIX adms:<http://www.w3.org/ns/adms#> \n"
					+ "PREFIX stat-dcat-ap:<http://data.europa.eu/m8g/> \n"
					+ "PREFIX dcat: <http://www.w3.org/ns/dcat#> \n \n" ;


	/**
	 * Hack to fix Sparql groupconcat which returns an empty object in array
	 * instead of empty array
	 * @param res
	 * @return
	 */
	public static String correctEmptyGroupConcat(String res) {
		if(res.equals("[{\"altLabel\":\"\"}]")) {
			return "[]";
		}
		return res;
	}

	public static JSONArray transformRdfTypeInString(JSONArray jArray) {
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jsonObject = jArray.getJSONObject(i);
			if (jsonObject.has(Constants.TYPE_OF_OBJECT)) {
				String typeOfObject = jsonObject.getString(Constants.TYPE_OF_OBJECT);
				String type = ObjectType.getLabelType(SimpleValueFactory.getInstance().createIRI(typeOfObject));
				jsonObject.put("type", type);
				jsonObject.remove(Constants.TYPE_OF_OBJECT);
			}
		}
		return jArray;
	}

	private QueryUtils() {
		throw new IllegalStateException("Utility class");
	}


}
