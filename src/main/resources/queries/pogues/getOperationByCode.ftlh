SELECT DISTINCT  ?operationId  ?operationLabelLg1 ?operationLabelLg2
    ?uri ?seriesAltLabelLg1 ?seriesAltLabelLg2 ?simsId ?seriesId ?series ?seriesLabelLg1 ?seriesLabelLg2
    ?proprietaire ?millesime
            
 { 
    ?series a insee:StatisticalOperationSeries . 
    ?series dcterms:hasPart ?uri .
    
  	BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?operationId)
    filter (?operationId='${ID}' )
    
    BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId) 
    OPTIONAL {
	?series skos:prefLabel ?seriesLabelLg1 .  
    FILTER (lang(?seriesLabelLg1) = '${LG1}')
    ?series skos:prefLabel ?seriesLabelLg2 .  
    FILTER (lang(?seriesLabelLg2) = '${LG2}')
	}

    OPTIONAL {
    ?series dc:creator ?proprietaire .
    }

    OPTIONAL {
	?uri skos:prefLabel ?operationLabelLg1 . 
	FILTER (lang(?operationLabelLg1) = '${LG1}') .
	?uri skos:prefLabel ?operationLabelLg2 . 
	FILTER (lang(?operationLabelLg2) = '${LG2}') .
   }
    OPTIONAL {
        ?uri dcterms:temporal ?millesime .
    }
    OPTIONAL {
  			?series skos:altLabel ?seriesAltLabelLg1 .
  			FILTER (lang(?seriesAltLabelLg1) = '${LG1}')
  			?series skos:altLabel ?seriesAltLabelLg2 .
  			FILTER (lang(?seriesAltLabelLg2) = '${LG2}')
    }
	OPTIONAL { ?sims sdmx-mm:target ?uri . 			 
             		?sims a sdmx-mm:MetadataReport . 				 
             		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) .
    }

}