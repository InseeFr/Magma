SELECT DISTINCT ?uri ?type ?code ?intitule ?intituleComplet ?iso3166alpha2 ?iso3166alpha3 ?iso3166num ?dateCreation ?dateSuppression
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
?s a igeo:Pays .
<#if code != "none">
    ?s igeo:codeINSEE '${code}' .
    BIND('${code}' as ?code)
    ?s a ?typeRDF ;
<#else>
    ?s igeo:codeINSEE ?code ;
    a ?typeRDF ;
</#if>
igeo:nom ?intitule .
OPTIONAL {?s igeo:nomLong ?intituleComplet .}
OPTIONAL {?s igeo:codeIso3166alpha2 ?iso3166alpha2 .}
OPTIONAL {?s igeo:codeIso3166alpha3 ?iso3166alpha3 .}
OPTIONAL {?s igeo:codeIso3166num ?iso3166num .}
BIND(STRAFTER(STR(?typeRDF),"#") AS ?type).
BIND(STR(?s) AS ?uri).
OPTIONAL {?s ^igeo:creation/igeo:date ?dateCreation }
OPTIONAL {?s ^igeo:suppression/igeo:date ?dateSuppression }

<#if date != "*">
    FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
    FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
</#if>
}

ORDER BY ?code