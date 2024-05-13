SELECT DISTINCT
?creator ?labelCreatorLg1 ?labelCreatorLg2 ?idCreator
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}/insee>

WHERE {
        ?uri a dcat:CatalogRecord ;
                foaf:primaryTopic ?uriDataset .
        ?uriDataset dcterms:identifier "${ID}" .

        OPTIONAL{?uriDataset dcterms:creator ?creator  .
        		?creator dcterms:identifier ?idCreator .
        		?creator skos:prefLabel ?labelCreatorLg1 .
        		FILTER(lang(?labelCreatorLg1)= '${LG1}') .
        		?creator skos:prefLabel ?labelCreatorLg2 .
        		FILTER(lang(?labelCreatorLg2)= '${LG2}') .}
}
