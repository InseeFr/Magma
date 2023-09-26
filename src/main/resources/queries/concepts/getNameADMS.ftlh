SELECT ?name
FROM <${ADMS_GRAPH}>
WHERE {
BIND(IRI('${IDENTIFIERADMS}') as ?uri)
?uri skos:notation ?name .
}