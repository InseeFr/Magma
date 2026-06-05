SELECT ?code ?uri ?type ?intitule ?relation
       ?dateCreation ?dateSuppression
       ?intituleSansArticle ?typeArticle
FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
    BIND('${date}'^^xsd:dateTime AS ?date)

    # Sous-requête : résout ?source une seule fois avant les branches UNION
    {
        SELECT ?source WHERE {
            ?source a igeo:${typeOrigine} ;
                    igeo:codeINSEE '${code}' .
            OPTIONAL { ?source ^igeo:creation/igeo:date ?dateCreationSource }
            OPTIONAL { ?source ^igeo:suppression/igeo:date ?dateSuppressionSource }
            FILTER(!BOUND(?dateCreationSource) || ?dateCreationSource <= '${date}'^^xsd:dateTime)
            FILTER(!BOUND(?dateSuppressionSource) || ?dateSuppressionSource > '${date}'^^xsd:dateTime)
        } LIMIT 1
    }

    # Résolution de ?uri via la relation (ancre ?uri depuis ?source, ensemble restreint)
    {
        ?source (igeo:subdivisionDirecteDe|^igeo:subdivisionDirecte) ?uri .
        BIND('inclus' AS ?relation)
    }
    UNION
    {
        ?source (^igeo:subdivisionDirecteDe|igeo:subdivisionDirecte) ?uri .
        BIND('contient' AS ?relation)
    }
    UNION
    {
        ?source (igeo:territoireCommunAvec|^igeo:territoireCommunAvec) ?uri .
        BIND('intersecte' AS ?relation)
    }

    # Attributs de ?uri (évalués sur un ?uri déjà contraint)
    ?uri a ?typeRDF ;
         igeo:codeINSEE ?code ;
         igeo:codeArticle ?typeArticle ;
         igeo:nom ?intitule ;
         igeo:nomSansArticle ?intituleSansArticle .

    BIND(STRAFTER(STR(?typeRDF), "http://rdf.insee.fr/def/geo#") AS ?type)
    FILTER(?type IN (${territoriesFilter}))

    # Filtrage temporel de ?uri
    OPTIONAL { ?uri ^igeo:creation/igeo:date ?dateCreation }
    OPTIONAL { ?uri ^igeo:suppression/igeo:date ?dateSuppression }
    FILTER(!BOUND(?dateCreation) || ?dateCreation <= ?date)
    FILTER(!BOUND(?dateSuppression) || ?dateSuppression > ?date)
}
ORDER BY ?code