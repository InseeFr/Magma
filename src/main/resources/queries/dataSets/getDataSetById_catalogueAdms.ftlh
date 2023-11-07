SELECT DISTINCT
?identifier
(GROUP_CONCAT(DISTINCT ?archiveUnit;separator=" , ") AS ?archiveUnits)
FROM <${DATASETS_GRAPH}>
FROM <${ADMS_GRAPH}>

WHERE {
		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL{?uri adms:identifier ?identifierADMS  .
		?identifierADMS skos:notation ?identifier . }

		OPTIONAL{?uri insee:archiveUnit ?archiveUnit .}

}
GROUP BY ?identifier
