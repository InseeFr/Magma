SELECT DISTINCT  ?dsd ?structureId

FROM <${DATASETS_GRAPH}>
FROM <${STRUCTURES_GRAPH}>

WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL {
		?uri insee:structure ?structure .
		?structure dcterms:identifier ?structureId .
		?structure skos:notation ?dsd .}

}
