package fr.insee.rmes.queries.concepts;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fr.insee.rmes.queries.Queries;

public class ConceptsQueries extends Queries {
    
    private static final String QUERIES_FOLDER = "concepts/";

    public static String getConceptsByLabel(String label) {
        return "SELECT ?id ?uri ?intitule ?hasLink "
        		+ "FROM <http://rdf.insee.fr/graphes/concepts/definitions> "
        		+ "WHERE { \n"
            + "?uri skos:inScheme ?conceptScheme . \n"
            + "FILTER(REGEX(STR(?conceptScheme),'/concepts/definitions/scheme')) \n"
            + "?uri skos:notation ?id . \n"
            + "?uri skos:prefLabel ?intitule . \n"
            + "FILTER(lang(?intitule) = 'fr') \n"
            + "FILTER(CONTAINS(LCASE(STR(?intitule)),\""
            + (StringUtils.isEmpty(label) ? "" : label.toLowerCase())
            + "\")) \n"
            + "BIND(EXISTS{?uri dcterms:replaces|^dcterms:replaces ?repl } AS ?hasLink) \n"
            + "}"
            + "ORDER BY ?intitule";
    }

    public static String getConceptById(String id) {
        Map<String,Object> params = new HashMap<>();
        params.put("conceptId", id);
        return buildRequest(QUERIES_FOLDER, "getConceptByCode.ftlh", params);
    }

    public static String getConceptLinks(String id) {
        Map<String,Object> params = new HashMap<>();
        params.put("uriConcept", "http://id.insee.fr/concepts/definition/"+id);
        return buildRequest(QUERIES_FOLDER, "getLinkedConceptsQuery.ftlh", params);
    }

    
}
