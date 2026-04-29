SELECT DISTINCT (STR(?dataset) AS ?uri) ?id ?catalogRecordModified
FROM <${DATASETS_GRAPH}>
WHERE {
    ?dataset a dcat:Dataset ;
             dcterms:identifier "${id}" ;
             dcterms:identifier ?id .
    OPTIONAL {
        ?dataset ^foaf:primaryTopic ?catalogRecord .
        ?catalogRecord dcterms:modified ?catalogRecordModified .
    }
}