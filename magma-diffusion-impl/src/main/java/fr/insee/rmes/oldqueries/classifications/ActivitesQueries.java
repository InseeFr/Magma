package fr.insee.rmes.oldqueries.classifications;

import java.time.LocalDateTime;

public class ActivitesQueries {
    
    private ActivitesQueries() {
        throw new IllegalStateException("Utility class");
      }

    public static String getActiviteByCodeAndDate(String code, LocalDateTime date) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
            + "?classification dcterms:issued ?dateDebutValidite . \n"
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
            + "VALUES ?classification { <"
            //+ Configuration.getBaseHost()
            + "/codes/na73/na> \n"
            + "<"
           // + Configuration.getBaseHost()
            + "/codes/naf/naf> \n"
            + "<"
                // + Configuration.getBaseHost()
            + "/codes/nafr1/naf> \n"
            + "<"
                // + Configuration.getBaseHost()
            + "/codes/nafr2/naf> } \n"
            + "}";
    }

    public static String getActiviteByCode(String code) {
        return getActiviteByCodeAndDate(code, LocalDateTime.now());
    }

    public static String getActivites(String code) {
        return "SELECT ?code ?uri ?intitule ?dateDebutValidite ?dateFinValidite WHERE { \n"
            + "?classification dcterms:issued ?dateDebutValidite . \n"
            + "OPTIONAL {?classification dcterms:valid ?dateFinValidite .} \n"
            + "?uri skos:inScheme ?classification . \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "?uri skos:notation ?code . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "VALUES ?classification { <"
                // + Configuration.getBaseHost()
            + "/codes/na73/na> \n"
            + "<"
                // + Configuration.getBaseHost()
            + "/codes/naf/naf> \n"
            + "<"
                //  + Configuration.getBaseHost()
            + "/codes/nafr1/naf> \n"
            + "<"
                // + Configuration.getBaseHost()
            + "/codes/nafr2/naf> } \n"
            + "} \n"
            + "ORDER BY ?dateDebutValidite";
    }

}
