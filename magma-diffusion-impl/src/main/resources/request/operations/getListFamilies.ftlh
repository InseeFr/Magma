SELECT DISTINCT ?id ?uri ?labelLg1 ?labelLg2 ?altlabelLg1 ?altlabelLg2
WHERE      { 
	  
           	?uri a insee:StatisticalOperationFamily .  
            BIND(STRAFTER(STR(?uri),'/operations/famille/') AS ?id ) . 
          
			?uri skos:prefLabel ?labelLg1 . 
			FILTER (lang(?labelLg1) = 'fr' ) 
			?uri skos:prefLabel ?labelLg2 . 
			FILTER (lang(?labelLg2) = 'en' ) 
	   
			OPTIONAL {?uri skos:altLabel ?altlabelLg1 . 
			FILTER ( lang(?altlabelLg1) = 'fr')  }
			OPTIONAL {?uri skos:altLabel ?altlabelLg2 . 
			FILTER ( lang(?altlabelLg2) = 'en')  }
     
           }
            order by ?id