SELECT DISTINCT
?idarchiveUnit ?labelarchiveUnitLg1 ?labelarchiveUnitLg2
FROM <${ADMS_GRAPH}>

WHERE {
        <${URI}> skos:notation ?idarchiveUnit .
        <${URI}> skos:prefLabel ?labelarchiveUnitLg1 .
        FILTER(lang(?labelarchiveUnitLg1)= '${LG1}')
        <${URI}> skos:prefLabel ?labelarchiveUnitLg2 .
        FILTER(lang(?labelarchiveUnitLg2)= '${LG2}') .
}
