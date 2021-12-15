SELECT DISTINCT ?id ?dateMiseAJour ?statutValidation ?version ?ordre
FROM <${CODELIST_GRAPH}>
WHERE {
    ?listeCode rdf:type skos:ConceptScheme .
    ?listeCode skos:notation ?id .
    OPTIONAL {?listeCode dcterms:modified ?dateMiseAJour . }
    OPTIONAL {?listeCode insee:validationState ?statutValidation . }
    OPTIONAL { ?uriListe pav:version ?version . }

}

