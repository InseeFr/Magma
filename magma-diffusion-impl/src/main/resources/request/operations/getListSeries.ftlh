SELECT DISTINCT ?seriesId  ?series ?seriesLabelLg1 ?seriesLabelLg2 ?seriesAltLabelLg1 ?seriesAltLabelLg2 ?family ?simsId
WHERE      { 
	  
			?series (^dcterms:hasPart|dcterms:isPartOf) ?family .
           	?series a insee:StatisticalOperationSeries .  
            BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId ) . 
          
			?series skos:prefLabel ?seriesLabelLg1 . 
			FILTER (lang(?seriesLabelLg1) = 'fr' ) 
			?series skos:prefLabel ?seriesLabelLg2 . 
			FILTER (lang(?seriesLabelLg2) = 'en' ) 
	   
			OPTIONAL {?series skos:altLabel ?seriesAltLabelLg1 . 
			FILTER ( lang(?seriesAltLabelLg1) = 'fr')  }
			OPTIONAL {?series skos:altLabel ?seriesAltLabelLg2 . 
			FILTER ( lang(?seriesAltLabelLg2) = 'en')  }
			
			OPTIONAL { ?sims sdmx-mm:target ?series . 			 
				?sims a sdmx-mm:MetadataReport . 				 
				BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
			} 	
     
     
           }
            order by ?seriesId
