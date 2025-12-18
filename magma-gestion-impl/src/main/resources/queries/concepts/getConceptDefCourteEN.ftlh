SELECT ?contenu

FROM <${CONCEPTS_GRAPH}>
WHERE {
?uri skos:prefLabel ?prefLabelLg2 .
FILTER (lang(?prefLabelLg2) = '${LG2}') .
FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .


OPTIONAL {
?uri skos:scopeNote ?definitionCourteEN .
?definitionCourteEN dcterms:language "en"^^xsd:language .
?definitionCourteEN pav:version ?numVersionCourteEN .
} .

OPTIONAL {?definitionCourteEN evoc:noteLiteral ?contenu .}
}