package fr.insee.rmes.oldqueries.classifications;

public class ClassificationsQueries {
	
	  private ClassificationsQueries() {
		    throw new IllegalStateException("Utility class");
	  }

    public static String getClassification(String classificationId) {	
        return "SELECT ?uri	?code ?uriParent ?codeParent ?intituleFr ?intituleEn "
            + "?noteGenerale ?contenuLimite ?contenuCentral ?contenuExclu \n"
            + " FROM <http://rdf.insee.fr/graphes/codes/"
            + classificationId.toLowerCase()
            + ">\n"
            + " WHERE{ \n"
            + " 	?uri rdf:type skos:Concept. \n"
            + " 	?uri skos:notation ?code. \n"
            + " 		OPTIONAL { \n"
            + "    		?uriParent skos:narrower ?uri. \n"
            + "			?uriParent skos:notation ?codeParent. \n"
            + " 	} \n"
            + " 	OPTIONAL { \n"
            + "			?uri skos:prefLabel ?intituleFr. \n"
            + " 		FILTER langMatches( lang(?intituleFr ), 'fr' ) \n"
            + "		} \n"
            + " 	OPTIONAL {  \n"
            + "			?uri skos:prefLabel ?intituleEn. \n"
            + " 		FILTER langMatches( lang(?intituleEn ), 'en' ) \n"
            + "		} \n"
            + "		OPTIONAL { \n"
            + "			?uri skos:definition ?uriNoteGenerale. \n"
            + " 		?uriNoteGenerale evoc:noteLiteral ?noteGenerale . \n"
            + " 		?uriNoteGenerale insee:validFrom ?dateDebNG. \n"
            + " 		FILTER (NOT EXISTS {?uriNoteGenerale insee:validUntil ?dateFinNG} ) \n"
            + " 	}\n"
            + " 	OPTIONAL { \n"
            + "			?uri xkos:additionalContentNote ?uriACNote. \n"
            + " 		?uriACNote evoc:noteLiteral ?contenuLimite . \n"
            + " 		?uriACNote insee:validFrom ?dateDebACN. \n"
            + " 		FILTER (NOT EXISTS {?uriACNote insee:validUntil ?dateFinACN} ) \n"
            + " 	}\n"
            + " 	OPTIONAL { \n"
            + " 		?uri xkos:coreContentNote ?uriCCNote. \n"
            + " 		?uriCCNote 	evoc:noteLiteral ?contenuCentral. \n"
            + "			?uriCCNote insee:validFrom ?dateDebCCN. \n"
            + " 		FILTER (NOT EXISTS {?uriCCNote insee:validUntil ?dateFinCCN} ) \n"
            + " 	}\n"
            + " 	OPTIONAL { \n"
            + "			?uri xkos:exclusionNote ?uriENote. \n"
            + " 		?uriENote evoc:noteLiteral  ?contenuExclu. \n"
            + " 		?uriENote insee:validFrom ?dateDebEN. \n"
            + " 		FILTER (NOT EXISTS {?uriENote insee:validUntil ?dateFinEN} )\n"
            + " 	}\n"
            + "}\n"
            + "ORDER BY ?code";
    }

    public static String getAllClassifications() {
        return "SELECT ?code ?uri ?intitule  \n"
            + "WHERE {\n"
            + "	?uri skos:prefLabel ?intitule .  \n"
            + "	?uri rdf:type skos:ConceptScheme .  \n"
            + "	BIND  (strbefore(strafter(str(?uri),'/codes/'),'/') as ?code)  \n"
            + "	FILTER(regex(str(?uri),'/codes/(.*)/(.*)'))  \n"
            + "	FILTER(!regex(str(?uri),'/cj/'))  \n"
            + "	FILTER(lang(?intitule) = 'fr')\n"
            + "}  \n"
            + "ORDER BY ?intitule  \n";
    }
}
