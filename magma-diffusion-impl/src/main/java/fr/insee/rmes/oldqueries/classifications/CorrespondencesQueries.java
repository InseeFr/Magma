package fr.insee.rmes.oldqueries.classifications;

public class CorrespondencesQueries {
	
	  private CorrespondencesQueries() {
		    throw new IllegalStateException("Utility class");
	  }

    /**
     * correspondence/{id correspondence}
     * @param correspondenceId
     * @return
     */
    public static String getCorrespondenceById(String correspondenceId) {

        return
            "select distinct ?uriPoste1 ?codePoste1 ?intituleFrPoste1 ?intituleEnPoste1 ?uriPoste2 ?codePoste2 ?intituleFrPoste2 ?intituleEnPoste2 \n"
                + "where {  \n"
                + "  ?tableCorrespondance xkos:compares ?nomenclature.\n"
                + "  filter ( strafter(str(?tableCorrespondance),'/codes/') = '"
                + correspondenceId
                + "'  ) \n"
                + "  ?tableCorrespondance skos:prefLabel ?preflabel.\n"
                + "  ?tableCorrespondance dc:description ?description.\n"
                + "  ?tableCorrespondance xkos:madeOf ?association.\n"
                + "  ?association xkos:sourceConcept ?uriPoste1.\n"
                + "  ?uriPoste1 skos:notation ?codePoste1.\n"
                + "  ?uriPoste1 skos:prefLabel ?intituleFrPoste1.\n"
                + "  FILTER (lang(?intituleFrPoste1) = 'fr')  .\n"
                + "  ?uriPoste1 skos:prefLabel ?intituleEnPoste1.\n"
                + "  FILTER (lang(?intituleEnPoste1) = 'en')  .\n"
                + "  ?association xkos:targetConcept ?uriPoste2.\n"
                + "  ?uriPoste2 skos:notation ?codePoste2.\n"
                + "  ?uriPoste2 skos:prefLabel ?intituleFrPoste2.\n"
                + "  FILTER (lang(?intituleFrPoste2) = 'fr')  .\n"
                + "  ?uriPoste2 skos:prefLabel ?intituleEnPoste2.\n"
                + "  FILTER (lang(?intituleEnPoste2) = 'en')  .\n"
                + "} \n"
                + "ORDER BY ?uriPoste1 ?uriPoste2";

    }

	/**
	 * correspondence/{id1 classification}/{id2 classification}
	 * @param classificationCode
	 * @param targetClassificationCode
	 * @return
	 */
    public static String getCorrespondenceByIds(String classificationCode, String targetClassificationCode) {

        return 
            "select distinct ?uriPoste1 ?codePoste1 ?intituleFrPoste1 ?intituleEnPoste1 ?uriPoste2 ?codePoste2 ?intituleFrPoste2 ?intituleEnPoste2 \n"
                + "where {  \n"
                + "  ?tableCorrespondance xkos:compares ?nomenclature.\n"
                + "  ?tableCorrespondance skos:prefLabel ?preflabel.\n"
                + "  ?tableCorrespondance dc:description ?description.\n"
                + "  ?tableCorrespondance xkos:madeOf ?association.\n"
                + "  ?association xkos:sourceConcept ?uriPoste1.\n"
                + "  ?uriPoste1 skos:notation ?codePoste1.\n"
                + "  ?uriPoste1 skos:prefLabel ?intituleFrPoste1.\n"
                + "  FILTER (lang(?intituleFrPoste1) = 'fr')  .\n"
                + "  ?uriPoste1 skos:prefLabel ?intituleEnPoste1.\n"
                + "  FILTER (lang(?intituleEnPoste1) = 'en')  .\n"
                + "  ?association xkos:targetConcept ?uriPoste2.\n"
                + "  ?uriPoste2 skos:notation ?codePoste2.\n"
                + "  ?uriPoste2 skos:prefLabel ?intituleFrPoste2.\n"
                + "  FILTER (lang(?intituleFrPoste2) = 'fr')  .\n"
                + "  ?uriPoste2 skos:prefLabel ?intituleEnPoste2.\n"
                + "  FILTER (lang(?intituleEnPoste2) = 'en')  .\n"
                // ex : correspondance nafrCible->cpfrCibleSource
                + "  filter  ( (contains(str(?uriPoste1),'/codes/"
                + classificationCode.toLowerCase()
                + "/') && contains(str(?uriPoste2),'/codes/"
                + targetClassificationCode.toLowerCase()
                + "/')  )\n"
                // ou dans l'autre sens ex : cpfrCibleSource->nafrCible
                + "   || (contains(str(?uriPoste1),'/codes/"
                + targetClassificationCode.toLowerCase()
                + "/') && contains(str(?uriPoste2),'/codes/"
                + classificationCode.toLowerCase()
                + "/')  ) )\n"
                + "  bind( if (contains(str(?uriPoste1),'/codes/"
                + classificationCode.toLowerCase()
                + "'),?codePoste1,?codePoste2) as ?orderingSource )\n"
                + "  bind( if (contains(str(?uriPoste2),'/codes/"
                + targetClassificationCode.toLowerCase()
                + "'),?codePoste2,?codePoste1) as ?orderingTarget )\n"
                + "} \n"
                + "ORDER BY ?orderingSource ?orderingTarget";

    }

    public static String getAllCorrespondences() {

        return
            "select ?id ?idSource ?idCible ?uri  ?intituleFr ?intituleEn ?descriptionFr ?descriptionEn\n"
                + "where {  \n"
                + "  ?uri rdf:type xkos:Correspondence.\n"
                + "  optional{?uri skos:prefLabel ?intituleFr.\n"
                + "  filter (lang(?intituleFr) = 'fr')}\n"
                + "  optional{?uri skos:prefLabel ?intituleEn.\n"
                + "  filter (lang(?intituleEn) = 'en')}\n"
                + "  optional{?uri dc:description ?descriptionFr.\n"
                + "  filter (lang(?descriptionFr) = 'fr')}\n"
                + "  optional{?uri dc:description ?descriptionEn.\n"
                + "  filter (lang(?descriptionEn) = 'en')}\n"
                + "  bind ( strafter(str(?uri ), '/codes/' ) as ?id )\n"
                + "  optional { bind ( strbefore(str(?id ), '-' ) as ?idSource ) }\n"
                + "  optional { bind ( strafter(str(?id ), '-' ) as ?idCible ) }\n"
                + "} \n";

    }

}
