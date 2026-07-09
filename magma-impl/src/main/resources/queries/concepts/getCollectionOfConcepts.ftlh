SELECT *
WHERE {
	?uri rdf:type skos:Collection .
	?uri dcterms:identifier "${ID}" .
    ?uri dcterms:modified ?date_mis_a_jour .
    ?uri dcterms:title ?intitule_fr .
    FILTER(LANG(?intitule_fr) = 'fr')
    OPTIONAL{
        ?uri dcterms:title ?intitule_en .
        FILTER(LANG(?intitule_en) = 'en')
    }
    OPTIONAL{
        ?uri dcterms:description ?description_fr .
        FILTER(LANG(?description_fr) = 'fr')
    }
    OPTIONAL{
        ?uri dcterms:description ?description_en .
        FILTER(LANG(?description_en) = 'en')
    }
} 