SELECT ?uri ?type ?code ?intitule ?intituleComplet ?iso3166alpha2 ?iso3166alpha3 ?iso3166num ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
{
# Sélection de la ressource d'origine (et de son événement destructeur)
SELECT DISTINCT ?origine ?evenement (?dateSuppressionOrigine AS ?dateCreation)
WHERE {
?origine a igeo:Pays ;
igeo:codeINSEE '${code}' .
?evenement igeo:suppression ?origine;
igeo:date ?dateSuppressionOrigine.
OPTIONAL {?origine (^igeo:creation/igeo:date) ?dateCreationOrigine.}
FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
}
}
?evenement igeo:creation ?ressource .
?ressource a ?typeRDF ;
a igeo:Pays ;
igeo:codeINSEE ?code ;
igeo:codeArticle ?typeArticle;
igeo:nom ?intitule.
OPTIONAL {?ressource igeo:nomLong ?intituleComplet .}
OPTIONAL {?ressource igeo:codeIso3166alpha2 ?iso3166alpha2 .}
OPTIONAL {?ressource igeo:codeIso3166alpha3 ?iso3166alpha3 .}
OPTIONAL {?ressource igeo:codeIso3166num ?iso3166num .}
BIND(STRAFTER(STR(?typeRDF),"#") AS ?type).
BIND(STR(?ressource) AS ?uri).
OPTIONAL { ?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
}
ORDER BY ?code