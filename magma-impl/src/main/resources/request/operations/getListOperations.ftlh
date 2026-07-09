SELECT DISTINCT ?id ?uri ?labelLg1 ?labelLg2 ?altlabelLg1 ?altlabelLg2 ?series ?simsId
WHERE      { 
	  
			?uri (^dcterms:hasPart|dcterms:isPartOf) ?series .
           	?uri a insee:StatisticalOperation .  
            BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?id ) . 
          
			?uri skos:prefLabel ?labelLg1 . 
			FILTER (lang(?labelLg1) = 'fr' ) 
			?uri skos:prefLabel ?labelLg2 . 
			FILTER (lang(?labelLg2) = 'en' ) 
	   
			OPTIONAL {?uri skos:altLabel ?altlabelLg1 . 
			FILTER ( lang(?altlabelLg1) = 'fr')  }
			OPTIONAL {?uri skos:altLabel ?altlabelLg2 . 
			FILTER ( lang(?altlabelLg2) = 'en')  }
			
			OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
				?sims a sdmx-mm:MetadataReport . 				 
				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
			} 	
     
     
           }
            order by ?id