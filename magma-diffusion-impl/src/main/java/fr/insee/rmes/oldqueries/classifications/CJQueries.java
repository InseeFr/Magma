package fr.insee.rmes.oldqueries.classifications;

import java.time.LocalDateTime;

public class CJQueries {

    public static String getCategorieJuridiqueNiveauII(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "FILTER(STRENDS(STR(?uri), '"
            + code
            + "')) \n"
            + "?lastCJThirdLevel skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "{ \n"
            + "SELECT ?lastCJThirdLevel WHERE { \n"
            + "?lastCJThirdLevel xkos:organizedBy <"
                //   + Configuration.getBaseHost()
            + "/concepts/cj/cjNiveauII> . \n"
            + "BIND(STRBEFORE(STRAFTER(STR(?lastCJThirdLevel), '"
                //  + Configuration.getBaseHost()
            + "/codes/cj/cj'), '/niveauII') AS ?lastCJVersion) \n"
            + "BIND(xsd:float(?lastCJVersion) AS ?lastCJVersionFloat)"
            + "} \n"
            + "ORDER BY DESC (?lastCJVersionFloat) \n"
            + "LIMIT 1 \n"
            + "} \n"
            + "}";
    }

    public static String getCategorieJuridiqueNiveauIII(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "FILTER(STRENDS(STR(?uri), '"
            + code
            + "')) \n"
            + "?lastCJThirdLevel skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "{ \n"
            + "SELECT ?lastCJThirdLevel WHERE { \n"
            + "?lastCJThirdLevel xkos:organizedBy <"
                //  + Configuration.getBaseHost()
            + "/concepts/cj/cjNiveauIII> . \n"
            + "BIND(STRBEFORE(STRAFTER(STR(?lastCJThirdLevel), '"
                //    + Configuration.getBaseHost()
            + "/codes/cj/cj'), '/niveauIII') AS ?lastCJVersion) \n"
            + "BIND(xsd:float(?lastCJVersion) AS ?lastCJVersionFloat)"
            + "} \n"
            + "ORDER BY DESC (?lastCJVersionFloat) \n"
            + "LIMIT 1 \n"
            + "} \n"
            + "}";
    }

    public static String getCJByCodeAndDate(String code, LocalDateTime date) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
            + "?classification dcterms:issued ?dateDebutValidite . \n"
            + "FILTER(REGEX(STR(?classification), '/codes/cj/')) \n"
            + "FILTER (?dateDebutValidite <= '"
            + date
            + "'^^xsd:dateTime) \n"
            + "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
            + "BIND(IF(!BOUND(?dateFinValidite), '9999-01-01T00:00:00.000+01:00'^^xsd:dateTime, "
            + "?dateFinValidite) as ?validFilter) \n"
            + "FILTER ('"
            + date
            + "'^^xsd:dateTime <= ?validFilter) \n"
            + "?uri skos:inScheme ?classification . \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "?uri skos:notation ?code . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

    public static String getCJByCode(String code) {
        return getCJByCodeAndDate(code, LocalDateTime.now());
    }

    public static String getCJ(String code) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite \n"
            + "WHERE { \n"
            + "{ \n"
            + "SELECT DISTINCT ?code ?uri ?intitule (min(?issued) AS ?dateDebutValidite) "
            + "(max(?validFilter) AS ?dateFin) \n"
            + "WHERE { \n"
            + "?uri skos:inScheme ?classification . \n"
            + "FILTER(REGEX(STR(?classification), '/codes/cj/')) \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "?uri skos:notation ?code . \n"
            + "?uri skos:prefLabel ?intitule . \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "?classification dcterms:issued ?issued . \n"
            + "OPTIONAL {?classification dcterms:valid ?valid} \n"
            + "BIND(IF(!BOUND(?valid), '9999-01-01T00:00:00.000+01:00'^^xsd:dateTime, ?valid) AS ?validFilter) \n"
            + "} \n"
            + "GROUP BY ?code ?uri ?intitule \n"
            + "} \n"
            + "BIND(IF(?dateFin='9999-01-01T00:00:00.000+01:00'^^xsd:dateTime,'',?dateFin) AS ?dateFinValidite)"
            + "} \n"
            + "ORDER BY ?dateDebutValidite";
    }
    
	  private CJQueries() {
		    throw new IllegalStateException("Utility class");
	  }

}
