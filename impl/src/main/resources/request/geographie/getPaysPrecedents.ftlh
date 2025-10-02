SELECT ?uri ?type ?code ?intitule ?intituleComplet ?iso3166alpha2 ?iso3166alpha3 ?iso3166num ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
{
SELECT DISTINCT ?origine ?evenement (?dateCreationOrigine AS ?dateSuppression)
WHERE {
?origine a igeo:Pays ;
igeo:codeINSEE '${code}' .
?evenement igeo:creation ?origine;
igeo:date ?dateCreationOrigine.
OPTIONAL {?origine (^igeo:suppression/igeo:date) ?dateSuppressionOrigine.}
FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)
}
}
?evenement igeo:suppression ?ressource .
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
OPTIONAL { ?ressource (^igeo:creation/igeo:date) ?dateCreation.}
}
ORDER BY ?code