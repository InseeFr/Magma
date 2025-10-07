SELECT ?uri	?code ?uriParent ?codeParent ?intituleFr ?intituleEn ?noteGenerale ?contenuLimite ?contenuCentral ?contenuExclu

FROM <http://rdf.insee.fr/graphes/codes/${classification}>

WHERE {
    ?uri rdf:type skos:Concept .

    ?uri skos:notation ?code .

    OPTIONAL {
        ?uriParent skos:narrower ?uri .

        ?uriParent skos:notation ?codeParent .
    }

    OPTIONAL {
        ?uri skos:prefLabel ?intituleFr .

        FILTER langMatches( lang(?intituleFr ), 'fr' )
    }

    OPTIONAL {
        ?uri skos:prefLabel ?intituleEn .

        FILTER langMatches( lang(?intituleEn ), 'en' )
    }

    OPTIONAL {
        ?uri skos:definition ?uriNoteGenerale .

        ?uriNoteGenerale evoc:noteLiteral ?noteGenerale .

        ?uriNoteGenerale insee:validFrom ?dateDebNG .

        FILTER ( NOT EXISTS{ ?uriNoteGenerale insee:validUntil ?dateFinNG } )
    }

    OPTIONAL {
        ?uri xkos:additionalContentNote ?uriACNote .

        ?uriACNote evoc:noteLiteral ?contenuLimite .

        ?uriACNote insee:validFrom ?dateDebACN .

        FILTER ( NOT EXISTS{ ?uriACNote insee:validUntil ?dateFinACN } )
    }

    OPTIONAL {
        ?uri xkos:coreContentNote ?uriCCNote .

        ?uriCCNote evoc:noteLiteral ?contenuCentral .

        ?uriCCNote insee:validFrom ?dateDebCCN .

        FILTER ( NOT EXISTS{ ?uriCCNote insee:validUntil ?dateFinCCN } )
    }

    OPTIONAL {
        ?uri xkos:exclusionNote ?uriENote .

        ?uriENote evoc:noteLiteral ?contenuExclu .

        ?uriENote insee:validFrom ?dateDebEN .

        FILTER ( NOT EXISTS{ ?uriENote insee:validUntil ?dateFinEN } )
    }
}

ORDER BY ?code
