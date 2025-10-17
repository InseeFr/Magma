SELECT ?id (?ConceptLinked as ?uri) ?typeOfLink 
FROM <http://rdf.insee.fr/graphes/concepts/definitions>
		WHERE {  
			 <${uriConcept}> (skos:broader|skos:narrower|skos:related|dcterms:replaces|dcterms:isReplacedBy|skos:closeMatch|dcterms:references) ?ConceptLinked ;
			 ?predicate ?ConceptLinked.
			 BIND(REPLACE(STR(?predicate), '^.+[/#]([^/#]+)$', '$1') AS ?typeOfLink)
			 BIND(STRAFTER(STR(?ConceptLinked),'/definition/') AS ?id) .
}
ORDER BY ?typeOfLink