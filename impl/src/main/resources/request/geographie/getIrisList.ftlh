SELECT DISTINCT ?uri (STRAFTER(STR(?typeAvecUri), "#") AS ?type) ?code ?intitule ?intituleSansArticle ?typeArticle ?typeDIris ?dateCreation ?dateSuppression
WHERE {
	{
		?s a igeo:Iris ;
			igeo:typeDIRIS ?uriTypeDIris ;
<#if com != "true">
        (igeo:subdivisionDirecteDe)+ <http://id.insee.fr/geo/pays/france>;
</#if>
			igeo:codeINSEE ?code .
		BIND(SUBSTR(STR(?uriTypeDIris ), STRLEN(STR(?uriTypeDIris )), 1) AS ?typeDIris)
	}
	UNION
	{
		?s a igeo:Commune ;
<#if com != "true">
        (igeo:subdivisionDirecteDe)+ <http://id.insee.fr/geo/pays/france>;
</#if>
			igeo:codeINSEE ?codeCommune .
		OPTIONAL {
			?iris igeo:subdivisionDirecteDe+ ?s ; a igeo:Iris .
			OPTIONAL {?iris ^igeo:creation/igeo:date ?dateCreationIris }
			OPTIONAL {?iris ^igeo:suppression/igeo:date ?dateSuppressionIris }
			FILTER (!BOUND(?dateCreationIris) || ?dateCreationIris <= '${date}'^^xsd:date )
			FILTER (!BOUND(?dateSuppressionIris) || ?dateSuppressionIris > '${date}'^^xsd:date )
		}
		FILTER (!BOUND(?iris))
		BIND(CONCAT(?codeCommune, '0000') AS ?code)
	}
	?s igeo:nom ?intitule;
		igeo:codeArticle ?typeArticle ;
		igeo:nomSansArticle ?intituleSansArticle ;
		a ?typeRDF .
	BIND(STR(?typeRDF) AS ?typeAvecUri).
	BIND(STR(?s) AS ?uri).
	OPTIONAL {?s ^igeo:creation/igeo:date ?dateCreation }
	OPTIONAL {?s ^igeo:suppression/igeo:date ?dateSuppression }
	FILTER (!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date )
	FILTER (!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date )
}
ORDER BY ?code