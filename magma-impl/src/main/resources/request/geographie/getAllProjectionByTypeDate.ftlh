SELECT DISTINCT ?origine ?type ?codeOrigine ?typeArticleOrigine ?intituleOrigine ?intituleSansArticleOrigine ?dateCreationOrigine ?dateSuppressionOrigine ?chefLieuOrigine
	?uri ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>

WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE ?codeOrigine ;
			       	igeo:codeArticle ?typeArticleOrigine;
					igeo:nom ?intituleOrigine;
					igeo:nomSansArticle ?intituleSansArticleOrigine .
			 BIND('${typeOrigine}' as ?type)       
			 
			 		
			OPTIONAL {
			 	?uri (igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion) ?chefLieuRDF .
			 	?chefLieuRDF igeo:codeINSEE ?chefLieuOrigine .
			 	
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
			       
				   
<#if previous = "true">
			?evenementCreationOrigine igeo:creation+ ?origine ;
					     			  igeo:date ?dateCreationOrigine.
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date) 
			OPTIONAL{	
				?evenementCreationOrigine igeo:suppression+ ?uriProj .
			}
			BIND(IF((!BOUND(?uriProj)&&(?dateCreationOrigine <= '${dateProjection}'^^xsd:date)),
				?origine, 
				IF(!BOUND(?uriProj),"no response",?uriProj)) 
				AS ?uri).

<#else>
			?evenementSuppressionOrigine igeo:suppression+ ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
		      OPTIONAL{              
				?evenementSuppressionOrigine igeo:creation+ ?uriProj .
			}
			BIND(IF((!BOUND(?uriProj)&&(?dateSuppressionOrigine >= '${dateProjection}'^^xsd:date)),
				?origine, 
				IF(!BOUND(?uriProj),"no response",?uriProj)) 
				AS ?uri).
</#if>					

			?uri a igeo:${typeOrigine} ;
					igeo:codeINSEE ?code ;
					igeo:codeArticle ?typeArticle;
					igeo:nom ?intitule;
					igeo:nomSansArticle ?intituleSansArticle .
			OPTIONAL {
				?evenementCreation igeo:creation ?uri ;
					               igeo:date ?dateCreation .
			}
			OPTIONAL {
				?evenementSuppression igeo:suppression ?uri ;
					                  igeo:date ?dateSuppression.
			}
			
			OPTIONAL {
			 	?uri (igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion) ?chefLieuRDF .
			 	?chefLieuRDF igeo:codeINSEE ?chefLieu .
			 	
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
			
			FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${dateProjection}'^^xsd:date)
			FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${dateProjection}'^^xsd:date)
				   
		
}
ORDER BY  ?codeOrigine ?code