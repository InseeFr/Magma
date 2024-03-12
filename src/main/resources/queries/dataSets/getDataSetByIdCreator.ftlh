SELECT DISTINCT
?idCreator ?labelCreatorLg1 ?labelCreatorLg2
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>

WHERE {
        ?uri a dcat:CatalogRecord ;
                foaf:primaryTopic ?uriDataset .
        ?uriDataset dcterms:identifier "${ID}" .

        OPTIONAL{?uriDataset dcterms:creator ?creator  .
        		?uriDataset dcterms:identifier ?idCreator .
        		?uriDataset dcterms:title ?labelCreatorLg1 .
        		FILTER(lang(?labelCreatorLg1)= '${LG1}') .
        		?uriDataset dcterms:title ?labelCreatorLg2 .
        		FILTER(lang(?labelCreatorLg2)= '${LG2}') .}
}
