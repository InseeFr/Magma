SELECT ?uri ?id ?notation ?prefLabelLg1 ?prefLabelLg2 ?dateCreation ?dateMiseAJour
FROM <${STRUCTURES_GRAPH}>
WHERE {
    ?uri rdf:type qb:DataStructureDefinition .
    ?uri dcterms:identifier "${STRUCTURE_ID}" .
    BIND("${STRUCTURE_ID}" AS ?id) .
    OPTIONAL { ?uri skos:notation ?notation . }
    ?uri rdfs:label ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = '${LG1}') .
    ?uri rdfs:label ?prefLabelLg2 .
    FILTER (lang(?prefLabelLg2) = '${LG2}') .
    ?uri dcterms:created ?dateCreation .
    ?uri dcterms:modified ?dateMiseAJour .

}