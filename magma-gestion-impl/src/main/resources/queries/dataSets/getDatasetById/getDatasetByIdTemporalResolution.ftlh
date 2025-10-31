SELECT DISTINCT
?labeltemporalResolutionLg1 ?labeltemporalResolutionLg2
FROM <${CODES_GRAPH}>

WHERE {
    <${URI}> skos:prefLabel ?labeltemporalResolutionLg1 .
    FILTER (lang(?labeltemporalResolutionLg1) = '${LG1}') .
    <${URI}> skos:prefLabel ?labeltemporalResolutionLg2 .
    FILTER (lang(?labeltemporalResolutionLg2) = '${LG2}') .
}