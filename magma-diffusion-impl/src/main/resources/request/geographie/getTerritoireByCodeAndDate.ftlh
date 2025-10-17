SELECT DISTINCT ?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>
		WHERE {
			?uri a igeo:${territoire} ;
				 igeo:codeArticle ?typeArticle ;
				 igeo:nom ?intitule ;
				 igeo:nomSansArticle ?intituleSansArticle .
<#if code != "none">
			?uri igeo:codeINSEE '${code}' .
			BIND('${code}' as ?code)
<#else>
			?uri igeo:codeINSEE ?code .
</#if>
			OPTIONAL {
				?evenementCreation igeo:creation ?uri ;
					               igeo:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression igeo:suppression ?uri ;
					                  igeo:date ?dateSuppression.
			}

<#if chefLieu != "none">
			OPTIONAL {
				?uri igeo:${chefLieu} ?chefLieuRDF .
				?chefLieuRDF igeo:codeINSEE ?chefLieu.
				OPTIONAL {
					?evenementCreationChefLieu igeo:creation ?chefLieuRDF ;
					igeo:date ?dateCreationChefLieu .
				}
				OPTIONAL {
					?evenementSuppressionChefLieu igeo:suppression ?chefLieuRDF ;
					igeo:date ?dateSuppressionChefLieu.
				}
				<#if date != "*">
					FILTER(!BOUND(?dateCreationChefLieu) || ?dateCreationChefLieu <= '${date}'^^xsd:date)
					FILTER(!BOUND(?dateSuppressionChefLieu) || ?dateSuppressionChefLieu > '${date}'^^xsd:date)
				</#if>
			}
</#if>
<#if date != "*">
			FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
			FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
</#if>
		}
				ORDER BY ?code
		