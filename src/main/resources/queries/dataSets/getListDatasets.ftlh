SELECT ?id ?uri ?titreLg1 ?titreLg2 ?dateMiseAJour ?statutValidation ?dateCreation
FROM <${DATASETS_GRAPH}>
WHERE {
    ?uriCatalogRecord rdf:type dcat:CatalogRecord .
    ?uriCatalogRecord foaf:primaryTopic ?uri .
    ?uri rdf:type dcat:Dataset .
    ?uri dcterms:identifier ?id .
    ?uri dcterms:title ?titreLg1 .
    FILTER (LANG(?titreLg1) = 'fr')
    ?uri dcterms:title ?titreLg2 .
    FILTER (LANG(?titreLg2) = 'en')
    ?uriCatalogRecord dcterms:modified ?dateMiseAJour .
    ?uriCatalogRecord dcterms:created ?dateCreation .
    ?uri insee:validationState ?statutValidation .
}
