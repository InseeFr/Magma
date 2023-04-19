 SELECT 				 ?id ?seriesLabelLg1 ?seriesLabelLg2 ?series ?seriesAltLabelLg1 ?seriesAltLabelLg2
						 ?typeId ?type  ?typeLabelLg1 ?typeLabelLg2
						 (count(?operationId) as ?nbOperation)
						 ?familyId ?family ?familyLabelLg1 ?familyLabelLg2  
						 ?periodicityId ?periodicity ?periodicityLabelLg1 ?periodicityLabelLg2 ?creator

 {
			    ?series a insee:StatisticalOperationSeries .
			    BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?id) .
           		?series skos:prefLabel ?seriesLabelLg1 .
           		FILTER (lang(?seriesLabelLg1) = '${LG1}')
           		?series skos:prefLabel ?seriesLabelLg2 .
           		FILTER (lang(?seriesLabelLg2) = '${LG2}')

 				?series dc:creator ?creator .

           		?series dcterms:isPartOf ?family .  
           		?family a insee:StatisticalOperationFamily .  
           		?family skos:prefLabel ?familyLabelLg1 .  
           		FILTER (lang(?familyLabelLg1 ) = '${LG1}')
           		?family skos:prefLabel ?familyLabelLg2 .
           		FILTER (lang(?familyLabelLg2 ) = '${LG2}')
           		BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) .
            
           		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg1 . 
           					FILTER (lang(?seriesAltLabelLg1) = '${LG1}') }
           		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg2 . 
           					FILTER (lang(?seriesAltLabelLg2) = '${LG2}') }
           		OPTIONAL { ?series dcterms:hasPart ?uri .
   							BIND(STRAFTER(STR(?uri),'/operations/operation/') AS ?operationId) } .
           		OPTIONAL { 
           				?series dcterms:accrualPeriodicity ?periodicity .
           				BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .
           				?periodicity skos:prefLabel ?periodicityLabelLg1 .  
           				FILTER (lang(?periodicityLabelLg1) = '${LG1}')
           				?periodicity skos:prefLabel ?periodicityLabelLg2 .  
           				FILTER (lang(?periodicityLabelLg2) = '${LG2}')

           		} 
            
           		OPTIONAL { 
           			?series dcterms:type ?type .
           			BIND(STRAFTER(STR(?type),'/codes/categorieSource/') AS ?typeId) .
           			?type skos:prefLabel ?typeLabelLg1 .
           			FILTER (lang(?typeLabelLg1) = '${LG1}')
					?type skos:prefLabel ?typeLabelLg2 .
           			FILTER (lang(?typeLabelLg2) = '${LG2}')

           		} 
            
           		OPTIONAL { ?sims sdmx-mm:target ?series .  
           			?sims a sdmx-mm:MetadataReport .  
           			BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) .  
           		} 

           		BIND(EXISTS{?series rdfs:seeAlso ?seeAlso} AS ?hasSeeAlso) 
           		BIND(EXISTS{?series dcterms:isReplacedBy ?isReplacedBy} AS ?hasIsReplacedBy) 
           		BIND(EXISTS{?series dcterms:replaces ?replaces} AS ?hasReplaces) 
           		BIND(EXISTS{?series dcterms:hasPart ?operation} AS ?hasOperation) 
           		BIND(EXISTS{?indic prov:wasGeneratedBy ?series} AS ?hasIndic) 
           		BIND(EXISTS{?series dcterms:publisher ?publisher} AS ?hasPublisher) 
           		BIND(EXISTS{?series dcterms:contributor ?contrib} AS ?hasContributor) 

            
           	} group by ?id ?seriesLabelLg1 ?seriesLabelLg2 ?series ?seriesAltLabelLg1 ?seriesAltLabelLg2
						    ?typeId ?type  ?typeLabelLg1 ?typeLabelLg2 ?familyId ?family ?familyLabelLg1 ?familyLabelLg2
						        ?periodicityId ?periodicity ?periodicityLabelLg1 ?periodicityLabelLg2 ?creator
 order by ?id ?seriesLabelLg1 ?seriesLabelLg2 ?series ?seriesAltLabelLg1 ?seriesAltLabelLg2
                       	    ?typeId ?type  ?typeLabelLg1 ?typeLabelLg2 ?familyId ?family ?familyLabelLg1 ?familyLabelLg2
                       	     ?periodicityId ?periodicity ?periodicityLabelLg1 ?periodicityLabelLg2 ?creator