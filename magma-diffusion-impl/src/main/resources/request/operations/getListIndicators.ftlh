SELECT DISTINCT ?id ?indic ?labelLg1 ?labelLg2 ?altLabelLg1 ?altLabelLg2  ?simsId
WHERE      { 
	  
           	?indic a insee:StatisticalIndicator .  
            BIND(STRAFTER(STR(?uri),'/produits/indicateur/') AS ?id ) . 
          
			?indic skos:prefLabel ?labelLg1 . 
			FILTER (lang(?labelLg1) = 'fr' ) 
			?indic skos:prefLabel ?labelLg2 . 
			FILTER (lang(?labelLg2) = 'en' ) 
	   
			OPTIONAL {?indic skos:altLabel ?altLabelLg1 . 
			FILTER ( lang(?altLabelLg1) = 'fr')  }
			OPTIONAL {?indic skos:altLabel ?altLabelLg2 . 
			FILTER ( lang(?altLabelLg2) = 'en')  }
			
			OPTIONAL { ?sims sdmx-mm:target ?indic . 			 
				?sims a sdmx-mm:MetadataReport . 				 
				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
			} 	
     
       }
       order by ?id