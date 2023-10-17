SELECT DISTINCT  ?dsd ?structureId

FROM <${DATASETS_GRAPH}>
FROM <${STRUCTURES_GRAPH}>

WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL {?structure dct:identifier ?structureId .
		?structure skos:notation ?dsd .}

}
