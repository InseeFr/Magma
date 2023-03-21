SELECT ?defcourteLg1 ?defcourteLg2

FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?uri skos:prefLabel ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = '${LG1}') .
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .


    OPTIONAL {
    ?uri skos:scopeNote ?definitionCourteFR .
    ?definitionCourteFR dcterms:language "fr"^^xsd:language .
    ?definitionCourteFR pav:version ?numVersionCourteFR .
    } .

    OPTIONAL {?definitionCourteFR evoc:noteLiteral ?defcourteLg1 .}

    OPTIONAL {
    ?uri skos:scopeNote ?definitionCourteEN .
    ?definitionCourteEN dcterms:language "en"^^xsd:language .
    ?definitionCourteEN pav:version ?numVersionCourteEN .
    } .

    OPTIONAL {?definitionCourteEN evoc:noteLiteral ?defcourteLg2 .}


}
