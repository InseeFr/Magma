SELECT ?id  ?dateMiseAJour
FROM <${STRUCTURES_GRAPH}>
WHERE {
    ?uri dcterms:identifier "${id}" .
    BIND("${id}" AS ?id) .
    ?uri dcterms:modified ?dateMiseAJour .

}