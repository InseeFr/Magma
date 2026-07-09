PREFIX : <http://rdf.insee.fr/def/geo#>
SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?inclusion ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
?canton a :Canton ;
:codeINSEE '${code}' .
?ressource a :Commune;
:subdivisionDirecteDe|:territoireCommunAvec ?canton ;
?typeRelation ?canton ;
a ?typeRDF;
:codeINSEE ?code;
:codeArticle ?typeArticle ;
:nom ?intitule ;
:nomSansArticle ?intituleSansArticle .
FILTER(?typeRelation = :territoireCommunAvec || ?typeRelation = :subdivisionDirecteDe)
OPTIONAL {BIND(IF(?typeRelation = :territoireCommunAvec, 'partielle', 'totale') AS ?inclusion)}
BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
BIND(STR(?ressource) AS ?uri).
OPTIONAL {?canton (^:creation/:date) ?dateCreationCanton. }
OPTIONAL {?canton (^:suppression/:date) ?dateSuppressionCanton. }
OPTIONAL {?ressource (^:creation/:date) ?dateCreation. }
OPTIONAL {?ressource (^:suppression/:date) ?dateSuppression. }
FILTER(!BOUND(?dateCreationCanton) || ?dateCreationCanton <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionCanton) || ?dateSuppressionCanton > '${date}'^^xsd:date)
FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?code