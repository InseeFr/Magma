SELECT ?notation
FROM <${CODES_GRAPH}>

WHERE {
    <${URI}> skos:notation ?notation .
}