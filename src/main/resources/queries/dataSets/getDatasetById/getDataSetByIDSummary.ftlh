select distinct ?id  ?dateModification
FROM <http://rdf.insee.fr/graphes/catalogue>

where {
		?uri a dcat:Dataset ;
        dcterms:identifier "${ID}" ;
        dcterms:identifier ?id ;
        OPTIONAL {
            ?record a dcat:CatalogRecord ;
            foaf:primaryTopic ?uri ;
            dcterms:modified ?dateModification .
    }

}