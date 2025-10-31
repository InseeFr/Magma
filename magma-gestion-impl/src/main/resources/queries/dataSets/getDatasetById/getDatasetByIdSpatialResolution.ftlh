SELECT DISTINCT
?spatialResolutionId ?labelspatialResolutionLg1 ?labelspatialResolutionLg2
FROM <${CODES_GRAPH}>

WHERE {
    <${URI}> skos:notation ?spatialResolutionId .
    <${URI}> skos:prefLabel ?labelspatialResolutionLg1 .
    FILTER (lang(?labelspatialResolutionLg1) = '${LG1}') .
    <${URI}> skos:prefLabel ?labelspatialResolutionLg2 .
    FILTER (lang(?labelspatialResolutionLg2) = '${LG2}') .
}