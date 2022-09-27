SELECT ?id  ?dateMiseAjour (max(?numVersion) as?version)
FROM <${CONCEPTS_GRAPH}>
WHERE {
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .
    ?uri dcterms:modified ?dateMiseAjour .
}
GROUP BY ?id ?dateMiseAjour
LIMIT 1