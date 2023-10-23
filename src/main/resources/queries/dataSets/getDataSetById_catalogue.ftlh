SELECT DISTINCT ?id ?uri  ?creator ?contributor
?titleLg1 ?titleLg2 ?statutValidation
?dateCreation ?dateModification ?dateEmission
?subtitleLg1 ?subtitleLg2 ?abstractLg1 ?abstractLg2
?descriptionLg1 ?descriptionLg2 ?scopeNoteLg1 ?scopeNoteLg2 ?landingPageLg1 ?landingPageLg2
?processStepLg1 ?processStepLg2
?version  ?numObservations ?numSeries
?endPeriod ?startPeriod
(GROUP_CONCAT(DISTINCT ?themes;separator=" , ") AS ?names) (GROUP_CONCAT(DISTINCT ?wasGeneratedBy;separator=" , ") AS ?operationStat)
FROM <${DATASETS_GRAPH}>


WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;
		dcterms:identifier ?id ;
		dc:creator ?creator ;
		dc:contributor ?contributor ;
		insee:validationState ?isValidated ;
		dcterms:created ?dateCreation ;
		dcterms:modified ?dateModification ;
		dcterms:title ?titleLg1 ;
		dcterms:title ?titleLg2 ;
		BIND( if(?isValidated,"Publiée","Provisoire, jamais publiée") as ?statutValidation )
		FILTER (lang(?titleLg1) = '${LG1}')
		FILTER (lang(?titleLg2) = '${LG2}')

		OPTIONAL {?uri prov:wasGeneratedBy ?wasGeneratedBy } .
		OPTIONAL {?uri dcat:theme ?themes } .
		OPTIONAL{?uri pav:version ?version} .
		OPTIONAL{?uri insee:numObservations ?numObservations} .
		OPTIONAL{?uri stat-dcat-ap:numSeries ?numSeries}
		OPTIONAL{?uri insee:structure ?structure  } .


		OPTIONAL {?uri insee:subtitle ?subtitleLg1 .
		FILTER (lang(?subtitleLg1) = '${LG1}')}
		OPTIONAL {?uri insee:subtitle ?subtitleLg2 .
		FILTER (lang(?subtitleLg2) = '${LG2}')}

		OPTIONAL {?uri dcterms:abstract ?abstractLg1 .
		FILTER (lang(?abstractLg1) = '${LG1}')}
		OPTIONAL {?uri dcterms:abstract ?abstractLg2 .
		FILTER (lang(?abstractLg2) = '${LG2}')}

		OPTIONAL {?uri dcterms:description ?descriptionLg1 .
		FILTER (lang(?descriptionLg1) = '${LG1}')}
		OPTIONAL {?uri dcterms:description ?descriptionLg2 .
		FILTER (lang(?descriptionLg2) = '${LG2}')}

		OPTIONAL {?uri skos:scopeNote ?scopeNoteLg1 .
		FILTER (lang(?scopeNoteLg1) = '${LG1}')}
		OPTIONAL {?uri skos:scopeNote ?scopeNoteLg2 .
		FILTER (lang(?scopeNoteLg2) = '${LG2}')}

		OPTIONAL {?uri dcat:landingPage ?landingPageLg1 .
		FILTER (lang(?landingPageLg1) = '${LG1}')}
		OPTIONAL {?uri dcat:landingPage ?landingPageLg2 .
		FILTER (lang(?landingPageLg2) = '${LG2}')}

		OPTIONAL {?uri insee:processStep ?processStepLg1 .
		FILTER (lang(?processStepLg1) = '${LG1}')}
		OPTIONAL {?uri insee:processStep ?processStepLg2 .
		FILTER (lang(?processStepLg2) = '${LG2}')}

		OPTIONAL {?uri dcterms:issued ?dateEmission . }

		OPTIONAL {?uri dcterms:temporal/dcat:startDate ?startPeriod . }
		OPTIONAL {?uri dcterms:temporal/dcat:endDate ?endPeriod . }


} GROUP BY ?id ?uri  ?creator ?contributor
?titleLg1 ?titleLg2 ?statutValidation
?dateCreation ?dateModification ?dateEmission
?subtitleLg1 ?subtitleLg2 ?abstractLg1 ?abstractLg2
?descriptionLg1 ?descriptionLg2 ?scopeNoteLg1 ?scopeNoteLg2 ?landingPageLg1 ?landingPageLg2
?processStepLg1 ?processStepLg2
?version  ?numObservations ?numSeries
?endPeriod ?startPeriod