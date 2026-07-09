 SELECT distinct ?id ?labelFr ?labelEn ?uri ?simsId 
             {	 
             	?series a insee:StatisticalOperationSeries . 
             	FILTER(STRAFTER(STR(?series),'/operations/serie/') = '${idSeries}') 	 
             	?series dcterms:hasPart|^dcterms:isPartOf ?uri . 		 
             	?uri a insee:StatisticalOperation . 		 
             	?uri skos:prefLabel ?labelFr . 		 
             	FILTER (lang(?labelFr) = 'fr') 		 
             	?uri skos:prefLabel ?labelEn . 		 
             	FILTER (lang(?labelEn) = 'en') 		 
             	BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?id) . 	 
             	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
             		?sims a sdmx-mm:MetadataReport . 				 
             		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
             	} 	 
             } 