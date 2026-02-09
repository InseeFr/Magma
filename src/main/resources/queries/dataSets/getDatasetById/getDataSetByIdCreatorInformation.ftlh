SELECT DISTINCT  (group_concat(distinct ?idCreator;separator=",") as ?names)

WHERE {
        ?uri a dcat:CatalogRecord ;
                foaf:primaryTopic ?uriDataset .
        ?uriDataset dcterms:identifier '${ID}' .

        OPTIONAL{?uriDataset dcterms:creator ?creator  .
                ?creator dcterms:identifier ?idCreator.
        		?creator skos:prefLabel ?labelCreatorLg1 .
        		FILTER(lang(?labelCreatorLg1)= '${LG1}') .
        		?creator skos:prefLabel ?labelCreatorLg2 .
        		FILTER(lang(?labelCreatorLg2)= '${LG2}') .}
}