select distinct ?id ?uri ?titreLg1 ?titreLg2 ?dateMiseAJour ?statutValidation ?dateCreation
FROM <${DATASETS_GRAPH}>
where { 
     ?uri dcterms:created+ ?origine ;
      dcterms:identifier ?id ;
      dcterms:title ?title .
     ?uri dcterms:title ?titreLg1 .
           	FILTER (lang(?titreLg1) = 'fr')
     ?uri dcterms:title ?titreLg2 .
           	FILTER (lang(?titreLg2) = 'en')
     OPTIONAL {?uri dcterms:modified ?dateMiseAJour . }
     OPTIONAL {?uri dcterms:created ?dateCreation . }
	 OPTIONAL {?uri insee:validationState ?statutValidation . }

} 
