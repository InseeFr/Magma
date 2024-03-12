SELECT DISTINCT  ?dsd ?structureId

FROM <${DATASETS_GRAPH}>
FROM <${STRUCTURES_GRAPH}>

WHERE {
        ?uri a dcat:CatalogRecord ;
        foaf:primaryTopic ?z .
    	?z dcterms:identifier "${ID}" .

		OPTIONAL {
		?z insee:structure ?structure .
		?structure dcterms:identifier ?structureId .
		?structure skos:notation ?dsd .}

}
