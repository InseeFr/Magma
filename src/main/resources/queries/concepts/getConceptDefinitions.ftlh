SELECT ?id (max(?numVersion) as?version)  ?definitionCourteFR ?definitionCourteEN

FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?uri skos:prefLabel ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = 'fr') .
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .
    ?uri skos:prefLabel ?prefLabelLg2 .
    FILTER (lang(?prefLabelLg2) = 'en') .
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .
    
    OPTIONAL {
        ?uri skos:scopeNote ?definitionCourteFR .
        ?definitionCourteFR dcterms:language "fr"^^xsd:language .
        ?definitionCourteFR insee:conceptVersion ?numVersionCourteFR .
    } .
    
        OPTIONAL {
        ?uri skos:scopeNote ?definitionCourteEN .
        ?definitionCourteEN dcterms:language "en"^^xsd:language .
        ?definitionCourteEN insee:conceptVersion ?numVersionCourteEN .
    } .
}
GROUP BY ?id ?definitionCourteFR ?definitionCourteEN
