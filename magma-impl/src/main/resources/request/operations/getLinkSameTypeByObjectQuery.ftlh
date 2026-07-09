SELECT ?id ?labelFr ?labelEn ?uri ?simsId 
            {	 
            	?obj a ${typeObject}   . 
            	FILTER(STRAFTER(STR(?obj),'${uriObject}') = '${idObject}') 	 
            	?obj ${linkPredicate} ?uri . 		 
            	?uri a ${typeObject}  . 		
            	?uri skos:prefLabel ?labelFr . 		 
            	FILTER (lang(?labelFr) = 'fr') 		 
            	?uri skos:prefLabel ?labelEn . 		 
            	FILTER (lang(?labelEn) = 'en') 		 
            	BIND(STRAFTER(STR(?uri),'${uriObject}') AS ?id) . 
            	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
            		?sims a sdmx-mm:MetadataReport . 				 
            		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 		
            	} 
            } 