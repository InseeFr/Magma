select distinct ?uri ?id ?labelSerieLg1 ?labelSerieLg2 
FROM  <http://rdf.insee.fr/graphes/operations>
where {
              ?uri  dcterms:isPartOf ?identifier.
   		   			?uri dcterms:isPartOf+ ?identifier .    		  	  			
    					FILTER(str(?uri) = '${URI}')
   				bind(strafter(str(?uri),"/serie/") as ?id)
				?uri skos:prefLabel ?labelSerie ;
    			skos:prefLabel ?labelSerieLg1 ;
           	    skos:prefLabel ?labelSerieLg2 .
    			FILTER (lang(?labelSerieLg1) = 'fr')
				FILTER (lang(?labelSerieLg2) = 'en') 
    
}