package fr.insee.rmes.oldqueries.classifications;


public class Na1973Queries {
	
	  private Na1973Queries() {
		    throw new IllegalStateException("Utility class");
	  }

    public static String getGroupeNA1973(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "<"
            //+ Configuration.getBaseHost()
            + "/codes/na73/groupes> skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

}
