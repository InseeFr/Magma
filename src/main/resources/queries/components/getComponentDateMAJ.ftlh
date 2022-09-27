SELECT ?id ?type ?notation
    ?dateMiseAJour
    ?version

FROM <${STRUCTURES_COMPONENTS_GRAPH}>
FROM <${CODELIST_GRAPH}>
WHERE {
    ?uriComposant dcterms:identifier "${ID}" .
    BIND("${ID}" AS ?id) .

    ?uriComposant dcterms:modified ?dateMiseAJour .
    OPTIONAL {
        ?uriComposant pav:version ?version .
    }

}