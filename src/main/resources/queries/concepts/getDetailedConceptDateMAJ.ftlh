SELECT ?id ?prefLabelLg1 ?prefLabelLg2 ?namingHint ?dateCreation ?dateMiseAjour  ?uri (max(?numVersion) as?version)
FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?uri skos:prefLabel ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = '${LG1}') .
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .
    ?uri skos:prefLabel ?prefLabelLg2 .
    FILTER (lang(?prefLabelLg2) = '${LG2}') .
    ?uri dcterms:created ?dateCreation .
    ?uri dcterms:modified ?dateMiseAjour .

}
GROUP BY ?id ?uri ?prefLabelLg1 ?prefLabelLg2 ?namingHint ?dateCreation ?dateMiseAjour
LIMIT 1