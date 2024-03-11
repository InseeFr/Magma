SELECT DISTINCT
?labeltypeLg1 ?labeltypeLg2
?labelaccessRightsLg1 ?labelaccessRightsLg2
?labelconfidentialityStatusLg1 ?labelconfidentialityStatusLg2
?codeProcessStep
?spatialId ?labelspatialLg1 ?labelspatialLg2
?labelstatisticalUnitLg1 ?labelstatisticalUnitLg2
?labelaccrualPeriodicityLg1 ?labelaccrualPeriodicityLg2
(GROUP_CONCAT(DISTINCT ?temporalResolution;separator=" , ") AS ?temporalResolutions) (GROUP_CONCAT(DISTINCT ?spatialResolution;separator=" , ") AS ?spatialResolutions)
FROM <${DATASETS_GRAPH}>
FROM <${CODES_GRAPH}>

WHERE {

		?uri a dcat:Dataset ;
		dcterms:identifier "${ID}" ;

		OPTIONAL{?uri dcterms:temporalResolution ?temporalResolution .}

		OPTIONAL{?uri dcterms:spatial ?spatial   .
		?spatial skos:notation ?spatialId .
		?spatial skos:prefLabel ?labelspatialLg1 .
		FILTER (lang(?labelspatialLg1) = '${LG1}') .
		?spatial skos:prefLabel ?labelspatialLg2 .
		FILTER (lang(?labelspatialLg2) = '${LG2}') .}

		OPTIONAL{?uri insee:spatialResolution ?spatialResolution  .}

		OPTIONAL{?uri  insee:processStep ?codeProcessStep  .}

		OPTIONAL{?uri insee:statisticalUnit ?statisticalUnit .
		?statisticalUnit skos:prefLabel ?labelstatisticalUnitLg1 .
		FILTER (lang(?labelstatisticalUnitLg1) = '${LG1}') .
		?statisticalUnit skos:prefLabel ?labelstatisticalUnitLg2 .
		FILTER (lang(?labelstatisticalUnitLg2) = '${LG2}') .}

		OPTIONAL{?uri dcterms:type ?type .
		?type skos:prefLabel ?labeltypeLg1 .
		FILTER(lang(?labeltypeLg1)= '${LG1}') .
		?type skos:prefLabel ?labeltypeLg2 .
		FILTER(lang(?labeltypeLg2)= '${LG2}') .}

		OPTIONAL{?uri dcterms:accessRights ?accessRights .
		?accessRights skos:prefLabel ?labelaccessRightsLg1 .
		FILTER(lang(?labelaccessRightsLg1)= '${LG1}')
		?accessRights skos:prefLabel ?labelaccessRightsLg2 .
		FILTER(lang(?labelaccessRightsLg2)= '${LG2}') .}

		OPTIONAL{?uri insee:confidentialityStatus ?confidentialityStatus .
		?confidentialityStatus skos:prefLabel ?labelconfidentialityStatusLg1 .
		FILTER(lang(?labelconfidentialityStatusLg1)= '${LG1}')
		?accessRights skos:prefLabel ?labelconfidentialityStatusLg2 .
		FILTER(lang(?labelconfidentialityStatusLg2)= '${LG2}') .}

		OPTIONAL{?uri dcterms:accrualPeriodicity ?accrualPeriodicity .
		?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg1 .
		FILTER(lang(?labelaccrualPeriodicityLg1)= '${LG1}')
		?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg2 .
		FILTER(lang(?labelaccrualPeriodicityLg2)= '${LG2}') .}


}
GROUP BY ?labeltypeLg1 ?labeltypeLg2
?labelaccessRightsLg1 ?labelaccessRightsLg2
?labelconfidentialityStatusLg1 ?labelconfidentialityStatusLg2
?codeProcessStep
?spatialId ?labelspatialLg1 ?labelspatialLg2
?labelstatisticalUnitLg1 ?labelstatisticalUnitLg2
?labelaccrualPeriodicityLg1 ?labelaccrualPeriodicityLg2
