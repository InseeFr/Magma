SELECT ?match ?matchNotation
FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?concept skos:notation "${CONCEPT_ID}" .
    ?concept skos:exactMatch | skos:closeMatch ?match .
    ?match skos:notation ?matchNotation .
}