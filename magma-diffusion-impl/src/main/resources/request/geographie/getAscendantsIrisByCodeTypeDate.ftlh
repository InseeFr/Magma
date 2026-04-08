SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?cheflieu ?dateCreation ?dateSuppression
WHERE {
	{
		SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?cheflieu ?dateCreation ?dateSuppression
		WHERE {
		{
			?enfant a igeo:Iris ;
			igeo:codeINSEE '${code}';
			igeo:subdivisionDirecteDe+ ?ressource .
		}
		UNION
		{
			BIND(SUBSTR('${code}', 1, 5) AS ?codeCommune)
			?enfant a igeo:Commune ;
				igeo:codeINSEE ?codeCommune .
			OPTIONAL {
				?iris igeo:subdivisionDirecteDe+ ?enfant ; a igeo:Iris .
				OPTIONAL {?iris ^igeo:creation/igeo:date ?dateDebutIris }
				OPTIONAL {?iris ^igeo:suppression/igeo:date ?dateFinIris }
				FILTER (!BOUND(?dateDebutIris) || ?dateDebutIris <= '${date}'^^xsd:date )
				FILTER (!BOUND(?dateFinIris) || ?dateFinIris > '${date}'^^xsd:date )
			}
			FILTER (!BOUND(?iris))
			BIND(CONCAT(?codeCommune, '0000') AS ?codeIris)
			FILTER(?codeIris = '${code}')
			?enfant igeo:subdivisionDirecteDe* ?ressource .
		}
			?ressource a ?typeRDF;
<#if type != "none">
			 a igeo:${type} ;
</#if>
				igeo:codeINSEE ?code ;
				igeo:codeArticle ?typeArticle ;
				igeo:nom ?intitule ;
				igeo:nomSansArticle ?intituleSansArticle .
			BIND(STR(?typeRDF) AS ?type).
			BIND(STR(?ressource) AS ?uri).
			OPTIONAL {?enfant (^igeo:creation/igeo:date) ?dateCreationEnfant.}
			OPTIONAL {?enfant (^igeo:suppression/igeo:date) ?dateSuppressionEnfant.}
			OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation.}
			OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
		}
	}
	FILTER(!BOUND(?dateCreationEnfant) || ?dateCreationEnfant <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppressionEnfant) || ?dateSuppressionEnfant > '${date}'^^xsd:date)
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?type ?code