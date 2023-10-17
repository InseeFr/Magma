SELECT DISTINCT  ?labeldisseminationStatusLg1

FROM <${DATASETS_GRAPH}>
FROM <${ONTOLOGIES_GRAPH}>

WHERE {

	?uri a dcat:Dataset ;
	dcterms:identifier "${ID}" ;
  	insee:disseminationStatus ?disseminationStatus .

    ?disseminationStatus skos:prefLabel ?labeldisseminationStatusLg1 .
    FILTER (lang(?labeldisseminationStatusLg1) = '${LG1}') .

}

