SELECT ?code ?uri ?type ?intitule ?relation ?dateCreation ?dateSuppression ?intitule ?intituleSansArticle ?typeArticle
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
	SELECT ?code ?uri ?type ?intitule ?relation ?dateCreation ?dateSuppression ?intitule ?intituleSansArticle ?typeArticle
	WHERE {
		BIND('${date}'^^xsd:dateTime AS ?date)
		?source a igeo:${typeOrigine};
		igeo:codeINSEE '${code}' .
		?uri a ?typeRDF ;
		igeo:codeINSEE ?code ;
		igeo:codeArticle ?typeArticle ;
		igeo:nom ?intitule;
		igeo:nomSansArticle ?intituleSansArticle.
        BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
        <#if type != "none">
            FILTER(?typeRDF = igeo:${type})
        </#if>
		{ ?source (igeo:subdivisionDirecteDe|^igeo:subdivisionDirecte) ?uri . BIND('inclus' AS ?relation) }
		UNION
		{ ?source (^igeo:subdivisionDirecteDe|igeo:subdivisionDirecte) ?uri . BIND('contient' AS ?relation)}
		UNION
		{ ?source (igeo:territoireCommunAvec|^igeo:territoireCommunAvec) ?uri . BIND('intersecte' AS ?relation) }
		OPTIONAL{?source ^igeo:creation/igeo:date ?dateCreationSource}
		OPTIONAL{?source ^igeo:suppression/igeo:date ?dateSuppressionSource}
		OPTIONAL{?uri ^igeo:creation/igeo:date ?dateCreation}
		OPTIONAL{?uri ^igeo:suppression/igeo:date ?dateSuppression}
		FILTER(!BOUND(?dateCreationSource) || ?dateCreationSource <= ?date)
		FILTER(!BOUND(?dateSuppressionSource) || ?dateSuppressionSource > ?date)
		FILTER(!BOUND(?dateCreation) || ?dateCreation <= ?date)
		FILTER(!BOUND(?dateSuppression) || ?dateSuppression > ?date)

	}
}
ORDER BY ?code