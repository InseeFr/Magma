SELECT DISTINCT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu ?intituleComplet ?categorieJuridique
FROM <http://rdf.insee.fr/graphes/geo/cog>
FROM <http://rdf.insee.fr/graphes/codes/cj>

WHERE {
			?origine a igeo:${typeOrigine} ;
			       igeo:codeINSEE '${code}' .
			 BIND('${typeOrigine}' as ?type)       
			       
				   
<#if previous = "true">
			?evenementCreationOrigine igeo:creation ?origine ;
					     			  igeo:date ?dateCreationOrigine.
			
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)
			OPTIONAL{
					?evenementSuppressionOrigine igeo:suppression ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			 }
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
			

			?evenementCreationOrigine igeo:suppression ?uri .

<#else>
			?evenementSuppressionOrigine igeo:suppression ?origine ;
					                  igeo:date ?dateSuppressionOrigine.
			FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
		    OPTIONAL{
		    		?evenementCreationOrigine igeo:creation ?origine ;
									igeo:date ?dateCreationOrigine.
			}
			FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)
		    
			
			
			?evenementSuppressionOrigine igeo:creation ?uri .
</#if>					

			?uri a igeo:${typeOrigine} ;
					igeo:codeINSEE ?code ;
					igeo:codeArticle ?typeArticle;
					igeo:nom ?intitule;
					igeo:nomSansArticle ?intituleSansArticle .
<#if typeOrigine == 	"Intercommunalite">
					OPTIONAL {
					?uri a igeo:${typeOrigine} ;
					igeo:nomIntercommunalite ?intituleComplet .
					}
</#if>
					Optional {
					?uri a igeo:${typeOrigine} ;
					insee:categorieJuridique ?cj.
					?cj skos:prefLabel ?categorieJuridique.
					}

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
		   
				   
		
}
ORDER BY  ?code