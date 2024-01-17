SELECT DISTINCT
?idCreator ?labelCreatorLg1 ?labelCreatorLg2
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>

WHERE {
        ?uri a dcat:Dataset ;
        dcterms:identifier "${ID}" ;

        OPTIONAL{?uri dcterms:creator ?creator  .
        		?creator dcterms:identifier ?idCreator .
        		?creator skos:prefLabel ?labelCreatorLg1 .
        		FILTER(lang(?labelCreatorLg1)= '${LG1}') .
        		?creator skos:prefLabel ?labelCreatorLg2 .
        		FILTER(lang(?labelCreatorLg2)= '${LG2}') .}
}
