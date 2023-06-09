select distinct ?id  ?dateModification
FROM <http://rdf.insee.fr/graphes/catalogue>

where {
				?uri a dcat:Dataset ;
                dcterms:identifier "${ID}" ;
                dcterms:identifier ?id ;
	 			OPTIONAL {?uri dcterms:modified ?dateModification . }

}  group by ?id ?dateModification