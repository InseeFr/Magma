package fr.insee.rmes.oldqueries.classifications;

public class Naf2008Queries {
	
	  private Naf2008Queries() {
		    throw new IllegalStateException("Utility class");
	  }

    public static String getClasseNAF2008(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "<"
                //+ Configuration.getBaseHost()
            + "/codes/nafr2/classes> skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

    public static String getSousClasseNAF2008(String code) {
        return "SELECT ?uri ?intitule WHERE { \n"
            + "?uri skos:notation '"
            + code
            + "' . \n"
            + "<"
                //+ Configuration.getBaseHost()
            + "/codes/nafr2/sousClasses> skos:member ?uri . \n"
            + "?uri skos:prefLabel ?intitule  \n"
            + "FILTER (lang(?intitule) = 'fr') \n"
            + "}";
    }

}
