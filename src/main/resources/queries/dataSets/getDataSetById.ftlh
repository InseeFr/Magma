SELECT DISTINCT ?id ?uri ?titleLg1 ?titleLg2 ?creator ?contributor ?labeldisseminationStatusLg1
?subtitleLg1 ?subtitleLg2 ?abstractLg1 ?abstractLg2 ?endPeriod ?startPeriod
?descriptionLg1 ?descriptionLg2 ?scopeNoteLg1 ?scopeNoteLg2 ?landingPageLg1 ?landingPageLg2
?processStepLg1 ?processStepLg2
?version ?dateCreation ?dateModification ?dateEmission  ?statutValidation ?numObservations ?numSeries
?identifier ?idPublisher ?labelPublisherLg1 ?labelPublisherLg2 ?wasGeneratedById ?labelwasGeneratedByLg1  ?labelwasGeneratedByLg2
?labeltypeLg1 ?labeltypeLg2 ?idarchiveUnit ?labelarchiveUnitLg1 ?labelarchiveUnitLg2 ?labelaccessRightsLg1 ?labelaccessRightsLg2
?labelconfidentialityStatusLg1 ?labelconfidentialityStatusLg2
?labeltemporalResolutionLg1 ?labeltemporalResolutionLg2 ?spatialResolutionId ?labelspatialLg1 ?labelspatialLg2
?labelspatialResolutionLg1 ?labelspatialResolutionLg2 ?spatialId
?labelstatisticalUnitLg1 ?labelstatisticalUnitLg2 ?dsd ?structureId
?labelaccrualPeriodicityLg1 ?labelaccrualPeriodicityLg2
FROM <${DATASETS_GRAPH}>
FROM <${ADMS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>
FROM <${OPERATIONS_GRAPH}>
FROM <${CODES_GRAPH}>
FROM <${STRUCTURES_GRAPH}>
FROM <${ONTOLOGIES_GRAPH}>


WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;
		dcterms:created+ ?dateCreation ;
		dcterms:identifier ?id ;
		dcterms:modified ?dateModification ;
		insee:validationState ?isValidated ;
		insee:disseminationStatus ?disseminationStatus ;
		dc:creator ?creator ;
		dc:contributor ?contributor ;
		dcterms:title ?titleLg1 ;
		dcterms:title ?titleLg2 ;
		BIND( if(?isValidated,"Publiée","Provisoire, jamais publiée") as ?statutValidation )
		FILTER (lang(?titleLg1) = '${LG1}')
		FILTER (lang(?titleLg2) = '${LG2}')

		?disseminationStatus skos:prefLabel ?labeldisseminationStatusLg1 .
		FILTER (lang(?labeldisseminationStatusLg1) = "fr") .

		OPTIONAL{?uri pav:version ?version} .
		OPTIONAL{?uri insee:numObservations ?numObservations} .
		OPTIONAL{?uri <http://data.europa.eu/m8g/numSeries> ?numSeries}
		OPTIONAL{?uri insee:structure ?structure  } .


		OPTIONAL {?uri insee:subtitle ?subtitleLg1 .
		FILTER (lang(?subtitleLg1) = '${LG1}')}
		OPTIONAL {?uri insee:subtitle ?subtitleLg2 .
		FILTER (lang(?subtitleLg2) = '${LG2}')}

		OPTIONAL {?uri dct:abstract ?abstractLg1 .
		FILTER (lang(?abstractLg1) = '${LG1}')}
		OPTIONAL {?uri dct:abstract ?abstractLg2 .
		FILTER (lang(?abstractLg2) = '${LG2}')}

		OPTIONAL {?uri dct:description ?descriptionLg1 .
		FILTER (lang(?descriptionLg1) = '${LG1}')}
		OPTIONAL {?uri dct:description ?descriptionLg2 .
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

		OPTIONAL {?uri dct:temporal/dcat:startDate ?startPeriod . }
		OPTIONAL {?uri dct:temporal/dcat:endDate ?endPeriod . }


		OPTIONAL{?uri dct:temporalResolution ?temporalResolution   .
		?temporalResolution skos:prefLabel ?labeltemporalResolutionLg1 .
		FILTER (lang(?labeltemporalResolutionLg1) = '${LG1}')}
		OPTIONAL {?temporalResolution skos:prefLabel ?labeltemporalResolutionLg2 .
		FILTER (lang(?labeltemporalResolutionLg2) = '${LG2}') .}

		OPTIONAL{?uri dct:spatial ?spatial   .
		?spatial skos:notation ?spatialId .
		?spatial skos:prefLabel ?labelspatialLg1 .
		FILTER (lang(?labelspatialLg1) = '${LG1}') .
		?spatial skos:prefLabel ?labelspatialLg2 .
		FILTER (lang(?labelspatialLg2) = '${LG2}') .}

		OPTIONAL{?uri insee:spatialResolution ?spatialResolution  .
		?spatialResolution skos:notation ?spatialResolutionId .
		?spatialResolution skos:prefLabel ?labelspatialResolutionLg1 .
		FILTER (lang(?labelspatialResolutionLg1) = '${LG1}') .
		?spatialResolution skos:prefLabel ?labelspatialResolutionLg2 .
		FILTER (lang(?labelspatialResolutionLg2) = '${LG2}') .}

		OPTIONAL{?uri insee:statisticalUnit ?statisticalUnit .
		?statisticalUnit skos:prefLabel ?labelstatisticalUnitLg1 .
		FILTER (lang(?labelstatisticalUnitLg1) = '${LG1}') .
		?statisticalUnit skos:prefLabel ?labelstatisticalUnitLg2 .
		FILTER (lang(?labelstatisticalUnitLg2) = '${LG2}') .}

		OPTIONAL {?structure dct:identifier ?structureId .
		?structure skos:notation ?dsd .}

		OPTIONAL{?uri adms:identifier ?identifierADMS  .
		?identifierADMS skos:notation ?identifier . }

		OPTIONAL{?uri dct:publisher ?publisher  .
		?publisher dct:identifier ?idPublisher .
		?publisher skos:prefLabel ?labelPublisherLg1 .
		FILTER(lang(?labelPublisherLg1)= '${LG1}') .
		?publisher skos:prefLabel ?labelPublisherLg2 .
		FILTER(lang(?labelPublisherLg2)= '${LG2}') .}

		OPTIONAL{?uri prov:wasGeneratedBy ?wasGeneratedBy .
		BIND(STRAFTER(STR(?wasGeneratedBy), '/operations/operation/') AS ?wasGeneratedById)
		?wasGeneratedBy skos:prefLabel ?labelwasGeneratedByLg1 .
		FILTER(lang(?labelwasGeneratedByLg1)= '${LG1}') .
		?wasGeneratedBy skos:prefLabel ?labelwasGeneratedByLg2 .
		FILTER(lang(?labelwasGeneratedByLg2)= '${LG2}') .}

		OPTIONAL{?uri dct:type ?type .
		?type skos:prefLabel ?labeltypeLg1 .
		FILTER(lang(?labeltypeLg1)= '${LG1}') .
		?type skos:prefLabel ?labeltypeLg2 .
		FILTER(lang(?labeltypeLg2)= '${LG2}') .}

		OPTIONAL{?uri insee:archiveUnit ?archiveUnit .
		?archiveUnit skos:notation ?idarchiveUnit .
		?archiveUnit skos:prefLabel ?labelarchiveUnitLg1 .
		FILTER(lang(?labelarchiveUnitLg1)= '${LG1}')
		?archiveUnit skos:prefLabel ?labelarchiveUnitLg2 .
		FILTER(lang(?labelarchiveUnitLg2)= '${LG2}') .}

		OPTIONAL{?uri dct:accessRights ?accessRights .
		?accessRights skos:prefLabel ?labelaccessRightsLg1 .
		FILTER(lang(?labelaccessRightsLg1)= '${LG1}')
		?accessRights skos:prefLabel ?labelaccessRightsLg2 .
		FILTER(lang(?labelaccessRightsLg2)= '${LG2}') .}

		OPTIONAL{?uri insee:confidentialityStatus ?confidentialityStatus .
		?confidentialityStatus skos:prefLabel ?labelconfidentialityStatusLg1 .
		FILTER(lang(?labelconfidentialityStatusLg1)= '${LG1}')
		?accessRights skos:prefLabel ?labelconfidentialityStatusLg2 .
		FILTER(lang(?labelconfidentialityStatusLg2)= '${LG2}') .}

		OPTIONAL{?uri dct:accrualPeriodicity ?accrualPeriodicity .
		?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg1 .
		FILTER(lang(?labelaccrualPeriodicityLg1)= '${LG1}')
		?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg2 .
		FILTER(lang(?labelaccrualPeriodicityLg2)= '${LG2}') .}


}

