SELECT DISTINCT   ?wasGeneratedById ?labelwasGeneratedByLg1  ?labelwasGeneratedByLg2 ?typeWasGeneratedBy

FROM <${DATASETS_GRAPH}>
FROM <${OPERATIONS_GRAPH}>
FROM <${ONTOLOGIES_GRAPH}>

WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL{?uri prov:wasGeneratedBy ?wasGeneratedBy .
		BIND(STRAFTER(STR(?wasGeneratedBy), '/operations/operation/') AS ?wasGeneratedById)
		?wasGeneratedBy rdf:type ?statisticalOperation .
		?statisticalOperation rdfs:label ?typeWasGeneratedBy .
		FILTER(lang(?typeWasGeneratedBy)= 'fr') .
		?wasGeneratedBy rdf:type ?statisticalOperation .
		?statisticalOperation rdfs:label ?typeWasGeneratedBy .
		FILTER(lang(?typeWasGeneratedBy)= '${LG1}') .
		?wasGeneratedBy skos:prefLabel ?labelwasGeneratedByLg1 .
		FILTER(lang(?labelwasGeneratedByLg1)= '${LG1}') .
		?wasGeneratedBy skos:prefLabel ?labelwasGeneratedByLg2 .
		FILTER(lang(?labelwasGeneratedByLg2)= '${LG2}') .}

}