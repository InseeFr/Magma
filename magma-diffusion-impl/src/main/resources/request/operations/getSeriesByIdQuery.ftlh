 SELECT ?familyId ?familyLabelLg1 ?familyLabelLg2 ?family 
           				 ?seriesId ?seriesLabelLg1 ?seriesLabelLg2 ?seriesAbstractLg1 ?seriesAbstractLg2 
           				 ?seriesHistoryNoteLg1 ?seriesHistoryNoteLg2 ?seriesAltLabelLg1  ?seriesAltLabelLg2 ?series ?simsId 
           				 ?periodicity ?type 
           				 ?periodicityLabelLg1 ?typeLabelLg1 
           				 ?periodicityLabelLg2 ?typeLabelLg2 
           				 ?periodicityId ?typeId 
           				?hasSeeAlso ?hasIsReplacedBy ?hasReplaces ?hasOperation ?hasIndic 
           				?hasPublisher ?hasContributor  ?hasCreator ?hasDataCollector
            
           	{  
           		?series a insee:StatisticalOperationSeries . 
           		FILTER(STRAFTER(STR(?series),'/operations/serie/') = '${idSeries}')  
           		BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId)  
           		?series skos:prefLabel ?seriesLabelLg1 .  
           		FILTER (lang(?seriesLabelLg1) = 'fr')  
           		?series skos:prefLabel ?seriesLabelLg2 .  
           		FILTER (lang(?seriesLabelLg2) = 'en')  
            
           		?series dcterms:isPartOf ?family .  
           		?family a insee:StatisticalOperationFamily .  
           		?family skos:prefLabel ?familyLabelLg1 .  
           		FILTER (lang(?familyLabelLg1 ) = 'fr')  
           		?family skos:prefLabel ?familyLabelLg2 .  
           		FILTER (lang(?familyLabelLg2 ) = 'en')  
           		BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) .  
            
           		OPTIONAL { ?series dcterms:abstract ?seriesAbstractLg1 .  
           					FILTER (lang(?seriesAbstractLg1) = 'fr') } 
           		OPTIONAL { ?series dcterms:abstract ?seriesAbstractLg2 .  
           					FILTER (lang(?seriesAbstractLg2) = 'en') } 
           		OPTIONAL { ?series skos:historyNote ?seriesHistoryNoteLg1 .  
           					FILTER (lang(?seriesHistoryNoteLg1) = 'fr') }  
           		OPTIONAL { ?series skos:historyNote ?seriesHistoryNoteLg2 .  
           					FILTER (lang(?seriesHistoryNoteLg2) = 'en') }  
           		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg1 . 
           					FILTER (lang(?seriesAltLabelLg1) = 'fr') } 
           		OPTIONAL { ?series skos:altLabel ?seriesAltLabelLg2 . 
           					FILTER (lang(?seriesAltLabelLg2) = 'en') } 
            
           		OPTIONAL { 
           				?series dcterms:accrualPeriodicity ?periodicity . 
           				?periodicity skos:prefLabel ?periodicityLabelLg1 .  
           				FILTER (lang(?periodicityLabelLg1) = 'fr')  
           				?periodicity skos:prefLabel ?periodicityLabelLg2 .  
           				FILTER (lang(?periodicityLabelLg2) = 'en')  
           				BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .  
           		} 
            
           		OPTIONAL { 
           			?series dcterms:type ?type . 
           			?type skos:prefLabel ?typeLabelLg1 .  
           			FILTER (lang(?typeLabelLg1) = 'fr')  
           			?type skos:prefLabel ?typeLabelLg2 .  
           			FILTER (lang(?typeLabelLg2) = 'en')  
           			BIND(STRAFTER(STR(?type),'/codes/categorieSource/') AS ?typeId) .  
           		} 
            
           		OPTIONAL { ?sims sdmx-mm:target ?series .  
           			?sims a sdmx-mm:MetadataReport .  
           			BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) .  
           		} 
            
           		BIND(EXISTS{?series rdfs:seeAlso ?seeAlso} AS ?hasSeeAlso) 
           		BIND(EXISTS{?series dcterms:isReplacedBy ?isReplacedBy} AS ?hasIsReplacedBy) 
           		BIND(EXISTS{?series dcterms:replaces ?replaces} AS ?hasReplaces) 
           		BIND(EXISTS{?series dcterms:hasPart|^dcterms:isPartOf ?operation} AS ?hasOperation) 
           		BIND(EXISTS{?indic prov:wasGeneratedBy ?series} AS ?hasIndic) 
           		BIND(EXISTS{?series dcterms:publisher ?publisher} AS ?hasPublisher) 
           		BIND(EXISTS{?series dcterms:contributor ?contrib} AS ?hasContributor) 
           		BIND(EXISTS{?series insee:dataCollector ?datacoll} AS ?hasDataCollector) 

           		BIND(EXISTS{?series dc:creator ?creator} AS ?hasCreator) 

            
           	} 