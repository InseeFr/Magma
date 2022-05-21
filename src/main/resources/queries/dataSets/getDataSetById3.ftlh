select distinct ?uri ?id ?labelOperationLg1 ?labelOperationLg2 
FROM  <http://rdf.insee.fr/graphes/operations>
where {
              ?uri  dcterms:isPartOf ?identifier.
   		   			?uri dcterms:isPartOf+ ?identifier .    		  	  			
    					FILTER(str(?uri) = '${URI}')
   				bind(strafter(str(?uri),"/operation/") as ?id)
				?uri skos:prefLabel ?labelOperation ;
    			skos:prefLabel ?labelOperationLg1 ;
           	    skos:prefLabel ?labelOperationLg2 .
    			FILTER (lang(?labelOperationLg1) = 'fr')
				FILTER (lang(?labelOperationLg2) = 'en') 
    
}