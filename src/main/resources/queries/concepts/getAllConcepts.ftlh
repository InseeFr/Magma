SELECT DISTINCT ?id ?dateMiseAJour ?statutValidation
FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?concept rdf:type skos:Concept .
    BIND(STRAFTER(STR(?concept),'/concepts/definition/') AS ?id) .
    ?concept dcterms:modified ?dateMiseAJour .
    ?concept insee:isValidated ?isValidated .
    BIND( if(?isValidated,"Publié","Provisoire, jamais publiée") as ?statutValidation )
}