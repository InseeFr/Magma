SELECT ?uri ?type ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
?commune a igeo:Commune ;
igeo:codeINSEE '${code}' .
?ressource a igeo:Canton;
a ?typeRDF;
^igeo:subdivisionDirecteDe|^igeo:territoireCommunAvec ?commune ;
igeo:codeINSEE ?code;
igeo:codeArticle ?typeArticle ;
igeo:nom ?intitule ;
igeo:nomSansArticle ?intituleSansArticle .
BIND(STRAFTER(STR(?typeRDF),"http://rdf.insee.fr/def/geo#") AS ?type).
BIND(STR(?ressource) AS ?uri).
OPTIONAL {?commune (^igeo:creation/igeo:date) ?dateCreationCommune. }
OPTIONAL {?commune (^igeo:suppression/igeo:date) ?dateSuppressionCommune. }
OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation. }
OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression. }
OPTIONAL {?ressource igeo:bureauCentralisateur ?chefLieuRDF. ?chefLieuRDF igeo:codeINSEE ?chefLieu.}
OPTIONAL {?chefLieuRDF ^igeo:creation/igeo:date ?dateCreationChefLieu.}
OPTIONAL {?chefLieuRDF ^igeo:suppression/igeo:date ?dateSuppressionChefLieu.}
FILTER(!BOUND(?dateCreationCommune) || (?dateCreationCommune) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionCommune) || xsd:dateTime(?dateSuppressionCommune) > '${date}'^^xsd:date)
FILTER(!BOUND(?dateCreation) || xsd:dateTime(?dateCreation) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppression) || xsd:dateTime(?dateSuppression) > '${date}'^^xsd:date)
FILTER(!BOUND(?dateCreationChefLieu) || xsd:dateTime(?dateCreationChefLieu) <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionChefLieu) || (?dateSuppressionChefLieu) > '${date}'^^xsd:date)
}
ORDER BY ?code