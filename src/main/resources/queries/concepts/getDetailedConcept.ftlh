SELECT ?id ?prefLabelLg1 ?prefLabelLg2 ?namingHint ?dateCreation ?dateMiseAJour ?dateFinValidite ?statutValidation ?identifierADMS ?uri (max(?numVersion) as?version)
FROM <${CONCEPTS_GRAPH}>
WHERE {
    ?uri skos:prefLabel ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = '${LG1}') .
    FILTER(REGEX(STR(?uri),'/concepts/definition/${ID}')) .
    BIND(STRAFTER(STR(?uri),'/definition/') AS ?id) .
    ?uri skos:prefLabel ?prefLabelLg2 .
    FILTER (lang(?prefLabelLg2) = '${LG2}') .
    ?uri dcterms:created ?dateCreation .
    ?uri dcterms:modified ?dateMiseAJour .
    ?uri insee:isValidated ?isValidated .
    OPTIONAL{
        ?uri adms:identifier ?identifierADMS .
    } .
    BIND( if(?isValidated,"Publiée","Provisoire, jamais publiée") as ?statutValidation )    OPTIONAL { ?uri dcterms:valid ?dateFinValidite . } .
    OPTIONAL {
        ?uri skos:definition ?definition .
        ?definition dcterms:language "fr"^^xsd:language .
        ?definition insee:conceptVersion ?numVersion .
    } .
}
GROUP BY ?id ?uri ?prefLabelLg1 ?prefLabelLg2 ?namingHint ?dateCreation ?dateMiseAJour ?dateFinValidite ?statutValidation ?identifierADMS
LIMIT 1