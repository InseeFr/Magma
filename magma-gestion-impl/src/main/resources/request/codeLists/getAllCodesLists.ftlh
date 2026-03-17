SELECT DISTINCT ?id ?dateMiseAJour ?statutValidation
FROM <${CODELIST_GRAPH}>
WHERE {
    ?listeCode rdf:type skos:ConceptScheme .
    ?listeCode skos:notation ?id .
    OPTIONAL {?listeCode dcterms:modified ?dateMiseAJour . }
    OPTIONAL {?listeCode insee:validationState ?statutValidation . }
}

