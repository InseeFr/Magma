SELECT ?id  ?dateMiseAJour
FROM <${STRUCTURES_GRAPH}>
WHERE {
    ?uri dcterms:identifier "${STRUCTURE_ID}" .
    BIND("${STRUCTURE_ID}" AS ?id) .
    ?uri dcterms:modified ?dateMiseAJour .

}