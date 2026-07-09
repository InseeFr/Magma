ASK FROM <http://rdf.insee.fr/graphes/geo/cog>
WHERE {
        {
            ?origine a igeo:Commune ;
            igeo:codeINSEE '${code}' .
        }
       UNION
        {
            ?origine a igeo:ArrondissementMunicipal ;
            igeo:codeINSEE '${code}' .
        }
        ?uri igeo:subdivisionDirecteDe+ ?origine .
        ?uri a ?type .
        FILTER(STRENDS(STR(?type), "#Iris"))
}