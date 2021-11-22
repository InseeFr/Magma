SELECT DISTINCT ?notation ?dateMiseAJour ?statutValidation ?version ?ordre
FROM <${CODELIST_GRAPH}>
WHERE {
      ?listeCode rdf:type skos:ConceptScheme .
    ?listeCode skos:notation ?notation .
    OPTIONAL {?listeCode dcterms:modified ?dateMiseAJour . }
    OPTIONAL {?listeCode insee:validationState ?statutValidation . }
    OPTIONAL { ?uriListe pav:version ?version . }
    OPTIONAL { ?uriListe ?predicatRdfSeq ?uriCode .
		FILTER(contains(str(?predicatRdfSeq), "http://www.w3.org/1999/02/22-rdf-syntax-ns#_" )) .
		BIND(strafter(str(?predicatRdfSeq),"http://www.w3.org/1999/02/22-rdf-syntax-ns#_") as ?ordre) .
	}
}

