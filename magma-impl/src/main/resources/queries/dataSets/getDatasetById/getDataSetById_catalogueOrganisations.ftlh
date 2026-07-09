SELECT DISTINCT  ?idPublisher ?labelPublisherLg1 ?labelPublisherLg2
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>

WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL{?uri dcterms:publisher ?publisher  .
		?publisher dcterms:identifier ?idPublisher .
		?publisher skos:prefLabel ?labelPublisherLg1 .
		FILTER(lang(?labelPublisherLg1)= '${LG1}') .
		?publisher skos:prefLabel ?labelPublisherLg2 .
		FILTER(lang(?labelPublisherLg2)= '${LG2}') .}

}
