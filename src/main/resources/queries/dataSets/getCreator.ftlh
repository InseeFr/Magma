SELECT DISTINCT  ?creator
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>

WHERE {
<${URI}> dcterms:identifier ?creator
}