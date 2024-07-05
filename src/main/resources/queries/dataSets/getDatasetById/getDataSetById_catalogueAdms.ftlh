SELECT DISTINCT ?identifier
(GROUP_CONCAT(DISTINCT ?archiveUnit;separator=" , ") AS ?archiveUnits)
FROM <${DATASETS_GRAPH}>
FROM <${ADMS_GRAPH}>

WHERE {
        ?uri a dcat:CatalogRecord ;
        foaf:primaryTopic ?z .
    	?z dcterms:identifier "${ID}" .
        OPTIONAL{
    	    ?z adms:identifier ?ident .
            ?ident skos:notation ?identifier
        }
        OPTIONAL{
            ?z insee:archiveUnit ?archiveUnit .
        }

}
GROUP BY ?archiveUnit ?identifier
