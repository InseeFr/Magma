package fr.insee.rmes.queries.classifications;

import fr.insee.rmes.config.Configuration;

public class Naf2003Queries {
	
	  private Naf2003Queries() {
		    throw new IllegalStateException("Utility class");
	  }

    public static String getClasseNAF2003(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "<"
            + Configuration.getBaseHost()
            + "/codes/nafr1/classes> skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

}
