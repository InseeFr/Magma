select distinct ?Id ?prefLabelLg1 ?prefLabelLg2 ?altLabelLg1 ?altLabelLg2 ?uri ?abreviation ?uniteDe ?sousTelleDe
from <http://rdf.insee.fr/graphes/organisations>
from <http://rdf.insee.fr/graphes/organisations/insee>
where {
    ?uri a <http://www.w3.org/ns/org#Organization> .
    ?uri dcterms:identifier '${ID}'
    BIND ('${ID}' as ?Id)
    OPTIONAL{ ?uri skos:prefLabel ?prefLabelLg1 .
        FILTER(lang(?prefLabelLg1) = '${LG1}')
    }
    OPTIONAL{ ?uri skos:altLabel ?altLabelLg1 .
        FILTER(lang(?altLabelLg1) = '${LG1}')
    }

    OPTIONAL{ ?uri skos:prefLabel ?prefLabelLg2 .
        FILTER(lang(?prefLabelLg2) = '${LG2}')
    }
    OPTIONAL{ ?uri skos:altLabel ?altLabelLg2 .
        FILTER(lang(?altLabelLg2) = '${LG2}')
    }

    OPTIONAL{ ?uri skosxl:altLabel ?uriabreviation .
        ?uriabreviation skosxl:literalForm ?abreviation .
    }

    OPTIONAL{
        ?uri org:unitOf ?UriUniteDe
        BIND(STRAFTER(STR(?UriUniteDe), "http://lannuaire.service-public.fr/") AS ?uniteDe)

    }
    OPTIONAL{
        ?uri org:reportsTo ?UriSousTelleDe
        BIND(STRAFTER(STR(?UriSousTelleDe), "http://lannuaire.service-public.fr/") AS ?sousTelleDe)
    }

}
