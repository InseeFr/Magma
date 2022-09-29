SELECT  ?id  ?dateMiseAJour
FROM <${CODELIST_GRAPH}>
WHERE {
    {
        ?uri rdf:type skos:ConceptScheme .
        ?uri skos:notation "${NOTATION}" .
        BIND("${NOTATION}" AS ?id) .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
    UNION
    {
        ?uri rdf:type skos:Collection .
        ?uri skos:notation "${NOTATION}" .
        BIND("${NOTATION}" AS ?id) .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
}