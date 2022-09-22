SELECT ?uri ?id ?prefLabelLg1 ?prefLabelLg2  ?dateMiseAJour
FROM <${CODELIST_GRAPH}>
WHERE {
    {
        ?uri rdf:type skos:ConceptScheme .
        ?uri skos:notation "${NOTATION}" .
        BIND("${NOTATION}" AS ?id) .
        ?uri skos:prefLabel ?prefLabelLg1 .
        FILTER (lang(?prefLabelLg1) = '${LG1}') .
        ?uri skos:prefLabel ?prefLabelLg2 .
        FILTER (lang(?prefLabelLg2) = '${LG2}') .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
    UNION
    {
        ?uri rdf:type skos:Collection .
        ?uri skos:notation "${NOTATION}" .
        BIND("${NOTATION}" AS ?id) .
        ?uri skos:prefLabel ?prefLabelLg1 .
        FILTER (lang(?prefLabelLg1) = '${LG1}') .
        ?uri skos:prefLabel ?prefLabelLg2 .
        FILTER (lang(?prefLabelLg2) = '${LG2}') .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
}