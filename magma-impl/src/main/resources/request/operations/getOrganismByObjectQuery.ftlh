SELECT ?id ?labelFr ?labelEn ?uri 
            {	 
            	?object a ${typeObject} . 
            	FILTER(STRAFTER(STR(?object),'${uriObject}') = '${idObject}') 	 
            	?object ${linkPredicate} ?uri. 		 
            	?uri skos:prefLabel ?labelFr . 		 
            	FILTER (lang(?labelFr) = 'fr') 		 
            	OPTIONAL{?uri skos:prefLabel ?labelEn . 		 
            	FILTER (lang(?labelEn) = 'en')} 		 
            	?uri dcterms:identifier ?id . 
            } 