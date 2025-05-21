SELECT DISTINCT  (group_concat(distinct ?response;separator=",") as ?names)

WHERE {
    ?uriStatisticalId dcterms:identifier "${ID}" .
    ?uriStatisticalId insee:statisticalUnit ?listUriStatistical .
    ?listUriStatistical skos:notation ?response.
    ?listUriStatistical skos:prefLabel ?labelUriStatisticalLg1.
    FILTER (lang(?labelUriStatisticalLg1) = '${LG1}') .
    ?listUriStatistical skos:prefLabel ?labelUriStatisticalLg2.
    FILTER (lang(?labelUriStatisticalLg2) = '${LG2}') .
}


