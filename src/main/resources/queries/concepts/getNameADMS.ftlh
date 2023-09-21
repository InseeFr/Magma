SELECT ?name
FROM <${ADMS_GRAPH}>
WHERE {
?uri skos:notation ?name .
FILTER(STR("http://bauhaus/identifierSchemes/conceptsNamingScheme/identifier/TEST")) .
}