SELECT DISTINCT   ?wasGeneratedById ?labelwasGeneratedByLg1  ?labelwasGeneratedByLg2 ?typeWasGeneratedBy

FROM <${OPERATIONS_GRAPH}>
FROM <${ONTOLOGIES_GRAPH}>

WHERE {

        BIND(STRAFTER(STR(<${URI}>), '/operations/') AS ?wasGeneratedByTempId)
        BIND(STRAFTER(STR(?wasGeneratedByTempId), '/') AS ?wasGeneratedById)
        <${URI}> rdf:type ?statisticalOperation .
        ?statisticalOperation rdfs:label ?typeWasGeneratedBy .
        FILTER(lang(?typeWasGeneratedBy)= '${LG1}') .
        <${URI}> rdf:type ?statisticalOperation .
        ?statisticalOperation rdfs:label ?typeWasGeneratedBy .
        FILTER(lang(?typeWasGeneratedBy)= '${LG1}') .
        <${URI}> skos:prefLabel ?labelwasGeneratedByLg1 .
        FILTER(lang(?labelwasGeneratedByLg1)= '${LG1}') .
        <${URI}> skos:prefLabel ?labelwasGeneratedByLg2 .
        FILTER(lang(?labelwasGeneratedByLg2)= '${LG2}') .

}