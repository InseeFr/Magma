select distinct ?id ?uri ?titreLg1 ?titreLg2 ?dateCreation ?dateModification (GROUP_CONCAT(DISTINCT ?result;separator=" , ") AS ?names) (GROUP_CONCAT(DISTINCT ?antecedent;separator=" , ") AS ?operationStat)
FROM <http://rdf.insee.fr/graphes/catalogue>

where {

    			?uri a dcat:Dataset ;
                dcterms:identifier "${ID}" ;
    			dcterms:created+ ?dateCreation ;
                dcterms:identifier ?id ;
                dcterms:title ?titreLg1 ;
           	    dcterms:title ?titreLg2 .
    			FILTER (lang(?titreLg1) = 'fr')
           		FILTER (lang(?titreLg2) = 'en')
	 				OPTIONAL {?uri dcterms:modified ?dateModification . }

}  group by ?id ?uri ?titreLg1 ?titreLg2 ?dateCreation ?dateModification