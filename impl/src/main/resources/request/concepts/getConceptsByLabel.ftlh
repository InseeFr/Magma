SELECT ?id ?uri ?intituleFr ?hasLink
FROM <http://rdf.insee.fr/graphes/concepts/definitions>
WHERE {
?uri skos:inScheme ?conceptScheme .
FILTER(REGEX(STR(?conceptScheme),'/concepts/definitions/scheme'))
?uri skos:notation ?id .
?uri skos:prefLabel ?intitule .
FILTER(lang(?intitule) = 'fr')
FILTER(CONTAINS(LCASE(STR(?intitule)), LCASE("${label}")))
BIND(STR(?intitule) AS ?intituleFr)
BIND(EXISTS{?uri dcterms:replaces|^dcterms:replaces ?repl } AS ?hasLink)
}
 ORDER BY ?intituleFr