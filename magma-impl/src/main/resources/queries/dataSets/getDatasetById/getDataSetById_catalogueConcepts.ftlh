SELECT DISTINCT  ?themeLabelLg1 ?themeLabelLg2 ?themeTaxonomy

FROM <${DATASETS_GRAPH}>
FROM <${CONCEPTS_GRAPH}>

WHERE {

    ?uri a dcat:Dataset ;
    dcterms:identifier "${ID}" ;

    OPTIONAL {
    ?uri dcat:theme ?theme .
    ?theme skos:prefLabel ?themeLabelLg1 .
    FILTER (lang(?themeLabelLg1) = '${LG1}') .
    ?theme skos:prefLabel ?themeLabelLg2 .
    FILTER (lang(?themeLabelLg2) = '${LG2}').
    ?theme skos:inScheme ?themeDetail .
    ?themeDetail skos:notation ?themeTaxonomy}


}