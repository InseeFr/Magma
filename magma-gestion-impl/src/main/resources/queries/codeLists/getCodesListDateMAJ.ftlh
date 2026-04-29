SELECT  ?id  ?dateMiseAJour
FROM <${CODELIST_GRAPH}>
WHERE {
    {
        ?uri rdf:type skos:ConceptScheme .
        ?uri skos:notation "${notation}" .
        BIND("${notation}" AS ?id) .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
    UNION
    {
        ?uri rdf:type skos:Collection .
        ?uri skos:notation "${notation}" .
        BIND("${notation}" AS ?id) .
        OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
    }
}