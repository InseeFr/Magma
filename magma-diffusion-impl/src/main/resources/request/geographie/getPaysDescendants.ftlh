SELECT ?uri ?type ?code ?intitule ?intituleComplet ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
{
SELECT DISTINCT ?uri (STRAFTER(STR(?typeAvecIri), "#") AS ?type) ?code ?intitule ?intituleComplet ?dateCreation ?dateSuppression ?dateCreationParent ?dateSuppressionParent
WHERE {
?parent a igeo:Pays ;
igeo:codeINSEE '${code}' ;

(^igeo:subdivisionDirecteDe)+ ?ressource .

<#if typeEnumDescendantsPays != "none">
    ?ressource a ?typeRDF .
    ?ressource a igeo:${type} ;
<#else>
    ?ressource a ?typeRDF ;
</#if>
igeo:codeINSEE ?code ;
igeo:nom ?intitule ;
OPTIONAL {?ressource igeo:nomLong ?intituleComplet .}
BIND(STR(?typeRDF) AS ?typeAvecIri).
BIND(STR(?ressource) AS ?uri).
OPTIONAL {?parent (^igeo:creation/igeo:date) ?dateCreationParent.}
OPTIONAL {?parent (^igeo:suppression/igeo:date) ?dateSuppressionParent.}
OPTIONAL {?ressource (^igeo:creation/igeo:date) ?dateCreation.}
OPTIONAL {?ressource (^igeo:suppression/igeo:date) ?dateSuppression.}
}
}
FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
}
ORDER BY ?type ?code