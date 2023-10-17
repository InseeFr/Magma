SELECT DISTINCT
?idarchiveUnit ?labelarchiveUnitLg1 ?labelarchiveUnitLg2
FROM <${DATASETS_GRAPH}>
FROM <${ADMS_GRAPH}>

WHERE {
		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL{?uri adms:identifier ?identifierADMS  .
		?identifierADMS skos:notation ?identifier . }

		OPTIONAL{?uri insee:archiveUnit ?archiveUnit .
		?archiveUnit skos:notation ?idarchiveUnit .
		?archiveUnit skos:prefLabel ?labelarchiveUnitLg1 .
		FILTER(lang(?labelarchiveUnitLg1)= '${LG1}')
		?archiveUnit skos:prefLabel ?labelarchiveUnitLg2 .
		FILTER(lang(?labelarchiveUnitLg2)= '${LG2}') .}

}
