SELECT ?id ?labelFr ?labelEn ?uri ?simsId
           FROM <http://rdf.insee.fr/graphes/operations> 
           FROM <http://rdf.insee.fr/graphes/produits> 
           WHERE { 
           	?obj a ${typeObject} . 
           	FILTER(STRAFTER(STR(?obj),'${uriObject}') = '${idObject}') 	 
           	?obj ${linkPredicate} ?uri . 		 
           		?uri skos:prefLabel ?labelFr . 		 
           		FILTER (lang(?labelFr) = 'fr') 		 
           		?uri skos:prefLabel ?labelEn . 		 
           		FILTER (lang(?labelEn) = 'en') 		 
      BIND (REPLACE(str(?uri), "^http://(.*)/(.*)/(.*)/", "") AS ?id)           	
      OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
           		?sims a sdmx-mm:MetadataReport . 				 
           		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 		
           	} 
           } 