SELECT ?match ?matchNotation
FROM <${CONCEPTS_GRAPH}>
WHERE {
    BIND(IRI("${URI}") AS ?concept)
    ?concept skos:exactMatch | skos:closeMatch ?match .
    ?match skos:notation ?matchNotation .
}