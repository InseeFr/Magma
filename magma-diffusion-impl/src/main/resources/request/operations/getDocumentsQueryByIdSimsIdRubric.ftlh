SELECT DISTINCT ?url ?labelLg1 ?labelLg2 ?dateMiseAJour ?langue
FROM <http://rdf.insee.fr/graphes/qualite/rapport/2292>
FROM <http://rdf.insee.fr/graphes/qualite/documents>
WHERE {
    BIND('${LANG}' AS ?LANG)
    ?text rdf:type dcmitype:Text .
    ?text dcterms:language ?langueURI .
    FILTER(STRENDS(STR(?langueURI), CONCAT("/codes/langue/", ?LANG)))
    FILTER(REGEX(STR(?text), '${idRubric}'))

    {
        SELECT ?text ?document (COUNT(DISTINCT ?intermediate) AS ?position)
        WHERE {
            ?text insee:additionalMaterial ?startList .
            FILTER(isBlank(?startList))
            ?startList rdf:rest* ?element .
            ?startList rdf:rest* ?intermediate .
            ?intermediate rdf:rest* ?element .
            ?element rdf:first ?document .
        }
        GROUP BY ?text ?document
    }

    ?document rdf:type foaf:Document .
    ?document <http://schema.org/url> ?url .

    OPTIONAL { ?document rdfs:label ?labelLg1 . FILTER(lang(?labelLg1) = '${LG1}') }
    OPTIONAL { ?document rdfs:label ?labelLg2 . FILTER(lang(?labelLg2) = '${LG2}') }
    OPTIONAL { ?document pav:lastRefreshedOn ?dateMiseAJour . }
    OPTIONAL { ?document dc:language ?langue . }
}
ORDER BY ?position