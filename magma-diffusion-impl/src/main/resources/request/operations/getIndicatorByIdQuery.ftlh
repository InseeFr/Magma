 SELECT ?id ?indic ?labelLg1 ?labelLg2 ?altLabelLg1 ?altLabelLg2 ?abstractLg1 ?abstractLg2 ?simsId 
             ?historyNoteLg1 ?historyNoteLg2 
             ?periodicity ?periodicityLabelLg1 ?periodicityLabelLg2 ?periodicityId 
             ?hasReplaces ?hasIsReplacedBy ?hasSeeAlso ?hasWasGeneratedBy 
             ?hasPublisher ?hasContributor ?hasCreator

             WHERE { 
             FILTER(STRENDS(STR(?indic),'/produits/indicateur/${idIndic}')) . 
             BIND(STRAFTER(STR(?indic),'/produits/indicateur/') AS ?id)  

             ?indic skos:prefLabel ?labelLg1 
             FILTER (lang(?labelLg1) = 'fr') 
             OPTIONAL{?indic skos:prefLabel ?labelLg2 
             	FILTER (lang(?labelLg2) = 'en') } 
             OPTIONAL{?indic skos:altLabel ?altLabelLg1 
             	FILTER (lang(?altLabelLg1) = 'fr') } 
             OPTIONAL{?indic skos:altLabel ?altLabelLg2 
             	FILTER (lang(?altLabelLg2) = 'en') } 
             OPTIONAL{?indic dcterms:abstract ?abstractLg1 
             	FILTER (lang(?abstractLg1) = 'fr') } 
             OPTIONAL{?indic dcterms:abstract ?abstractLg2 
             	FILTER (lang(?abstractLg2) = 'en') } 
             OPTIONAL{?indic skos:historyNote ?historyNoteLg1 
             	FILTER (lang(?historyNoteLg1) = 'fr') } 
             OPTIONAL{?indic skos:historyNote ?historyNoteLg2 
             	FILTER (lang(?historyNoteLg2) = 'en') } 
             OPTIONAL {?indic dcterms:accrualPeriodicity ?accrualPeriodicity . 
             	?accrualPeriodicity skos:notation ?accrualPeriodicityCode . 
             	?accrualPeriodicity skos:inScheme ?accrualPeriodicityCodeList . 
             	?accrualPeriodicityCodeList skos:notation ?accrualPeriodicityList . 
             } 
            OPTIONAL { 
            	?indic dcterms:accrualPeriodicity ?periodicity . 
            	?periodicity skos:prefLabel ?periodicityLabelLg1 .  
            	FILTER (lang(?periodicityLabelLg1) = 'fr')  
            	?periodicity skos:prefLabel ?periodicityLabelLg2 .  
            	FILTER (lang(?periodicityLabelLg2) = 'en')  
            	BIND(STRAFTER(STR(?periodicity),'/codes/frequence/') AS ?periodicityId) .  
            } 


            OPTIONAL { ?sims sdmx-mm:target ?indic . 			 
            	?sims a sdmx-mm:MetadataReport . 				 
            	BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId) . 
           	} 
        
            		BIND(EXISTS{?indic dcterms:contributor ?a} AS ?hasContributor) . 
            		BIND(EXISTS{?indic dcterms:publisher ?publisher} AS ?hasPublisher) .
	           		BIND(EXISTS{?indic dc:creator ?creator} AS ?hasCreator) .         		
            		BIND(EXISTS{?indic dcterms:replaces ?b} AS ?hasReplaces) .  
            		BIND(EXISTS{?indic dcterms:isReplacedBy ?c} AS ?hasIsReplacedBy) .  
            		BIND(EXISTS{?indic rdfs:seeAlso ?d} AS ?hasSeeAlso) .  
            		BIND(EXISTS{?indic prov:wasGeneratedBy ?e} AS ?hasWasGeneratedBy) .  
            	


             } 
			 