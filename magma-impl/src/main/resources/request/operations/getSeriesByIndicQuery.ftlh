SELECT ?id ?labelFr ?labelEn ?uri ?simsId 
           {	 
           	?indic a insee:StatisticalIndicator . 
           	FILTER(STRAFTER(STR(?series),'/produits/indicateur/') = '${idIndic}') 	 
           	?indic prov:wasGeneratedBy ?series . 		 
           			?series a insee:StatisticalOperationSeries . 		 
           			?series skos:prefLabel ?labelFr . 		 
           			FILTER (lang(?labelFr) = 'fr') 		 
           			?series skos:prefLabel ?labelEn . 		 
           			FILTER (lang(?labelEn) = 'en') 		 
           			BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?id) . 	
           			OPTIONAL { ?sims sdmx-mm:target ?series . 			 
           				?sims a sdmx-mm:MetadataReport . 				 
           				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
           			} 	 	 
           } 