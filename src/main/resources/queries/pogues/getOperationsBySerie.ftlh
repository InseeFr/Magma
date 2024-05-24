SELECT DISTINCT  ?operationId  ?operationLabelLg1 ?operationLabelLg2 ?operationAltLabelLg1 ?operationAltLabelLg2 ?uri ?simsId ?seriesId ?series ?seriesLabelLg1 ?seriesLabelLg2   ?typeLabelLg1 ?typeLabelLg2
				 ?periodicityId


           	{

            ?series a insee:StatisticalOperationSeries .
			FILTER(STRAFTER(STR(?series),'/operations/serie/')='${ID}')
           	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId)
  			?series skos:prefLabel ?seriesLabelLg1 .
           	FILTER (lang(?seriesLabelLg1) = '${LG1}')
           	?series skos:prefLabel ?seriesLabelLg2 .
           	FILTER (lang(?seriesLabelLg2) = '${LG2}')

            ?series dcterms:hasPart ?uri .
  			BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?operationId)
  			?uri skos:prefLabel ?operationLabelLg1 .
			FILTER (lang(?operationLabelLg1) = '${LG1}') .
			?uri skos:prefLabel ?operationLabelLg2 .
			FILTER (lang(?operationLabelLg2) = '${LG2}') .

			OPTIONAL {
            ?series dcterms:accrualPeriodicity ?periodicity .
            BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .
            ?periodicity skos:prefLabel ?periodicityLabelLg1 .
            FILTER (lang(?periodicityLabelLg1) = '${LG1}')
            ?periodicity skos:prefLabel ?periodicityLabelLg2 .
            FILTER (lang(?periodicityLabelLg2) = '${LG2}')
            }

    		OPTIONAL {
    		?uri skos:altLabel ?operationAltLabelLg1 .
			FILTER (lang(?operationAltLabelLg1) = '${LG1}') .
    		?uri skos:altLabel ?operationAltLabelLg2 .
			FILTER (lang(?operationAltLabelLg2) = '${LG2}') .
			}

  			OPTIONAL {
  			?uri dcterms:isPartOf ?uriSerie .
  			?uriSerie dcterms:type ?type .
  			BIND(STRAFTER(STR(?type),'/categorieSource/') AS ?typeId)
           	?type skos:prefLabel ?typeLabelLg1 .
  			FILTER (lang(?typeLabelLg1) = '${LG1}')
  			?type skos:prefLabel ?typeLabelLg2 .
           	FILTER (lang(?typeLabelLg2) = '${LG2}')
           	}

    		OPTIONAL { ?sims sdmx-mm:target ?uri .
             		?sims a sdmx-mm:MetadataReport .
             		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) .


  }


} order by desc (?operationId)


