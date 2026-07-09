SELECT DISTINCT  ?uri ?dsd ?structureId ?type ?DataStructureDefinition
FROM <${DATASETS_GRAPH}>
FROM <${STRUCTURES_GRAPH}>
WHERE {
?subject a dcat:CatalogRecord ;
foaf:primaryTopic ?z .
?z dcterms:identifier "${ID}" .
?z insee:structure ?uri .

OPTIONAL {
?uri dcterms:identifier ?structureId .
?uri skos:notation ?dsd .
?uri rdf:type ?type.
}
BIND(IF(?type=qb:DataStructureDefinition,"true","false") AS ?DataStructureDefinition).

}
