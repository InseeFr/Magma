SELECT ?id ?labelFr ?labelEn ?uri ?simsId 
            {	 
            	?series a insee:StatisticalOperationSeries . 
            	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '${idSeries}') 	 
            	?uri prov:wasGeneratedBy ?series . 		 
            			?uri a insee:StatisticalIndicator . 		 
            			?uri skos:prefLabel ?labelFr . 		 
            			FILTER (lang(?labelFr) = 'fr') 		 
            			?uri skos:prefLabel ?labelEn . 		 
            			FILTER (lang(?labelEn) = 'en') 		 
            			BIND(STRAFTER(STR(?uri),'/produits/indicateur/') AS ?id) . 	
            			OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
            				?sims a sdmx-mm:MetadataReport . 				 
            				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
            			} 	 	 
            } 