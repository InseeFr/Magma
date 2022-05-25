select distinct ?id ?uri ?titreLg1 ?titreLg2 ?dateCreation ?dateModification ?statutValidation ?description (GROUP_CONCAT(DISTINCT ?result;separator=" , ") AS ?names) (GROUP_CONCAT(DISTINCT ?antecedent;separator=" , ") AS ?operationStat)
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
    				 	OPTIONAL {?uri insee:validationState ?statutValidation . }
       					 	OPTIONAL { ?uri dcat:theme ?result .}
							OPTIONAL  {?uri prov:wasGeneratedBy ?antecedent.
							#filter( STRBEFORE(str(?antecedent), "/serie" )= 'http://bauhaus/operations')
							} 
}  group by ?id ?uri ?titreLg1 ?titreLg2 ?dateCreation ?dateModification ?statutValidation ?description 