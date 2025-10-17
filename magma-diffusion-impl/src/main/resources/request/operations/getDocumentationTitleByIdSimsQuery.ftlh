 SELECT ?id ?uri ?labelLg1 ?labelLg2 ?idCible ?cible ?labelCibleLg1 ?labelCibleLg2 
             FROM <http://rdf.insee.fr/graphes/qualite/rapport/${idSims}> 
             WHERE { 
             	?uri rdf:type sdmx-mm:MetadataReport .   
             		 
             OPTIONAL{ 
             	 ?uri sdmx-mm:target ?cible .  
             	 BIND(STRAFTER(STR(?cible),'/') AS ?idCible) . 
             			  
             	 ?cible rdfs:label ?labelCibleLg1 . 
             			  FILTER(lang(?labelCibleLg1) = 'fr') 
             			  
             	OPTIONAL{?cible rdfs:label ?labelCibleLg2 . 
             			  FILTER(lang(?labelCibleLg2) = 'en') }  
             } 
             				 
             	 OPTIONAL{ ?uri rdfs:label ?labelLg1 .  
             		FILTER(lang(?labelLg1) = 'fr') 
             	 } 
             	 OPTIONAL{ ?uri rdfs:label ?labelLg2 .  
             		FILTER(lang(?labelLg2) = 'en') 
             	 } 
             				 
             	FILTER(STRENDS(STR(?uri), '${idSims}')) 
             BIND ( ${idSims} AS ?id )  
             } 