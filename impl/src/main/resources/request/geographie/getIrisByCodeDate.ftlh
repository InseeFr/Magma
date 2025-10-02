SELECT DISTINCT ?uri ?type ?code ?intituleSansArticle ?intitule ?typeDIris ?dateCreation ?dateSuppression ?typeArticle
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {

		?s a igeo:Iris ;
			igeo:typeDIRIS ?uriTypeDIris ;
			igeo:codeINSEE '${code}';
			igeo:codeArticle ?typeArticle ;
			igeo:codeINSEE ?code .
		BIND(SUBSTR(STR(?uriTypeDIris ), STRLEN(STR(?uriTypeDIris )), 1) AS ?typeDIris)

        OPTIONAL {
			?iris igeo:subdivisionDirecteDe+ ?s ; a igeo:Iris .
		}
		FILTER (!BOUND(?iris))
   	    FILTER(?code = '${code}')
    	?s igeo:nom ?intitule;
        igeo:nomSansArticle ?intituleSansArticle ;
		a ?typeRDF .
		BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
		BIND(STR(?s) AS ?uri).
    	OPTIONAL {?s ^igeo:creation/igeo:date ?dateCreation }
	OPTIONAL {?s ^igeo:suppression/igeo:date ?dateSuppression }
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
	}