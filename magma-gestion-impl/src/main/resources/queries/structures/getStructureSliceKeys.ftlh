SELECT ?sliceKey ?prefLabelLg1 ?prefLabelLg2 ?typeSliceKey
(GROUP_CONCAT(?componentId; separator=",") AS ?componentIds)
FROM <${STRUCTURES_GRAPH}>
FROM <${STRUCTURES_COMPONENTS_GRAPH}>
FROM <${CODELIST_GRAPH}>
WHERE {
	?structure dcterms:identifier '${ID}' ;
    	qb:sliceKey ?sliceKey .

    ?sliceKey rdf:type qb:SliceKey ;
    	qb:componentProperty/dcterms:identifier ?componentId .

    OPTIONAL{?sliceKey rdfs:label ?prefLabelLg1 .
    FILTER(lang(?prefLabelLg1) = '${LG1}')}

    OPTIONAL{?sliceKey rdfs:label ?prefLabelLg2 .
    FILTER(lang(?prefLabelLg2) = '${LG2}')}

    OPTIONAL{?sliceKey dcterms:type/skos:notation ?typeSliceKey}
} GROUP BY ?sliceKey ?prefLabelLg1 ?prefLabelLg2 ?typeSliceKey