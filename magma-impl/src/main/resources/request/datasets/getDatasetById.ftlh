SELECT DISTINCT
    (STR(?dataset) AS ?uri)
    ?id
    ?statutValidation
    ?titleLg1 ?titleLg2
    ?subtitleLg1 ?subtitleLg2
    ?abstractLg1 ?abstractLg2
    ?descriptionLg1 ?descriptionLg2
    ?scopeNoteLg1 ?scopeNoteLg2
    ?landingPageLg1 ?landingPageLg2
    ?catalogRecordCreator ?catalogRecordContributor
    ?catalogRecordModified ?catalogRecordCreated
    ?modified ?issued ?version ?spatialTemporal
    ?startPeriod ?endPeriod
    ?derivedDescriptionLg1 ?derivedDescriptionLg2
    ?idPublisher ?labelPublisherLg1 ?labelPublisherLg2
    ?labeltypeLg1 ?labeltypeLg2
    ?labelaccessRightsLg1 ?labelaccessRightsLg2
    ?labelconfidentialityStatusLg1 ?labelconfidentialityStatusLg2
    ?labelaccrualPeriodicityLg1 ?labelaccrualPeriodicityLg2
    ?spatialId ?labelspatialLg1 ?labelspatialLg2
    ?codeProcessStep ?disseminationStatus ?identifier
    ?structureUri ?structureId ?dsd ?isDataStructureDefinition
    ?numObservations ?numSeries
    (GROUP_CONCAT(DISTINCT CONCAT(?idCreator, "$", COALESCE(STR(?creatorLabelLg1), ""), "$", COALESCE(STR(?creatorLabelLg2), "")); separator="|") AS ?creators)
    (GROUP_CONCAT(DISTINCT STR(?wasGeneratedBy); separator=",") AS ?operationStat)
    (GROUP_CONCAT(DISTINCT STR(?theme); separator=",") AS ?names)
    (GROUP_CONCAT(DISTINCT ?wasDerivedFromId; separator=",") AS ?wasDerivedFromS)
    (GROUP_CONCAT(DISTINCT STR(?relation); separator=",") AS ?relations)
    (GROUP_CONCAT(DISTINCT ?kwLg1; separator=",") AS ?keywordLg1)
    (GROUP_CONCAT(DISTINCT ?kwLg2; separator=",") AS ?keywordLg2)
    (GROUP_CONCAT(DISTINCT STR(?archiveUnit); separator=",") AS ?archiveUnits)
    (GROUP_CONCAT(DISTINCT STR(?temporalResolution); separator=",") AS ?temporalResolutions)
    (GROUP_CONCAT(DISTINCT STR(?spatialResolution); separator=",") AS ?spatialResolutions)
FROM <${DATASETS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}>
FROM <${ORGANISATIONS_GRAPH}/insee>
FROM <${CODELIST_GRAPH}>
FROM <${ADMS_GRAPH}>
FROM <${STRUCTURES_GRAPH}>
FROM <${ONTOLOGIES_GRAPH}>
WHERE {
    ?dataset a dcat:Dataset ;
             dcterms:identifier "${id}" ;
             dcterms:identifier ?id ;
             insee:validationState ?isValidated ;
             dcterms:title ?titleLg1 ;
             dcterms:title ?titleLg2 .

    BIND(IF(?isValidated = "Unpublished", "Provisoire, jamais publiée", "Publiée") AS ?statutValidation)
    FILTER (lang(?titleLg1) = '${LG1}')
    FILTER (lang(?titleLg2) = '${LG2}')

    OPTIONAL { ?dataset dcterms:modified ?modified }
    OPTIONAL { ?dataset dcterms:issued ?issued }
    OPTIONAL { ?dataset pav:version ?version }
    OPTIONAL { ?dataset insee:numObservations ?numObservations }
    OPTIONAL { ?dataset stat-dcat-ap:numSeries ?numSeries }
    OPTIONAL { ?dataset insee:spatialTemporal ?spatialTemporal }
    OPTIONAL {
        ?dataset dcterms:creator ?creator .
        ?creator dcterms:identifier ?idCreator .
        ?creator skos:prefLabel ?creatorLabelLg1 .
        FILTER (lang(?creatorLabelLg1) = '${LG1}')
        OPTIONAL { ?creator skos:prefLabel ?creatorLabelLg2 . FILTER (lang(?creatorLabelLg2) = '${LG2}') }
    }
    OPTIONAL { ?dataset dcterms:relation ?relation }
    OPTIONAL { ?dataset prov:wasGeneratedBy ?wasGeneratedBy }
    OPTIONAL { ?dataset dcat:theme ?theme }
    OPTIONAL { ?dataset dcat:temporalResolution ?temporalResolution }
    OPTIONAL { ?dataset insee:spatialResolution ?spatialResolution }
    OPTIONAL { ?dataset adms:identifier ?ident . ?ident skos:notation ?identifier }
    OPTIONAL { ?dataset insee:archiveUnit ?archiveUnit }

    OPTIONAL {
        ?dataset ^foaf:primaryTopic ?catalogRecord .
        OPTIONAL { ?catalogRecord dc:creator ?catalogRecordCreator }
        OPTIONAL { ?catalogRecord dc:contributor ?catalogRecordContributor }
        OPTIONAL { ?catalogRecord dcterms:modified ?catalogRecordModified }
        OPTIONAL { ?catalogRecord dcterms:created ?catalogRecordCreated }
    }

    OPTIONAL {
        ?dataset prov:wasDerivedFrom ?wasDerivedFrom .
        ?wasDerivedFrom dcterms:identifier ?wasDerivedFromId .
    }
    OPTIONAL {
        ?dataset prov:qualifiedDerivation ?qualifiedDerivation .
        OPTIONAL { ?qualifiedDerivation dcterms:description ?derivedDescriptionLg1 . FILTER (lang(?derivedDescriptionLg1) = '${LG1}') }
        OPTIONAL { ?qualifiedDerivation dcterms:description ?derivedDescriptionLg2 . FILTER (lang(?derivedDescriptionLg2) = '${LG2}') }
    }

    OPTIONAL { ?dataset insee:subtitle ?subtitleLg1 . FILTER (lang(?subtitleLg1) = '${LG1}') }
    OPTIONAL { ?dataset insee:subtitle ?subtitleLg2 . FILTER (lang(?subtitleLg2) = '${LG2}') }
    OPTIONAL { ?dataset dcterms:abstract ?abstractLg1 . FILTER (lang(?abstractLg1) = '${LG1}') }
    OPTIONAL { ?dataset dcterms:abstract ?abstractLg2 . FILTER (lang(?abstractLg2) = '${LG2}') }
    OPTIONAL { ?dataset dcterms:description ?descriptionLg1 . FILTER (lang(?descriptionLg1) = '${LG1}') }
    OPTIONAL { ?dataset dcterms:description ?descriptionLg2 . FILTER (lang(?descriptionLg2) = '${LG2}') }
    OPTIONAL { ?dataset skos:scopeNote ?scopeNoteLg1 . FILTER (lang(?scopeNoteLg1) = '${LG1}') }
    OPTIONAL { ?dataset skos:scopeNote ?scopeNoteLg2 . FILTER (lang(?scopeNoteLg2) = '${LG2}') }
    OPTIONAL { ?dataset dcat:landingPage ?landingPageLg1 . FILTER (lang(?landingPageLg1) = '${LG1}') }
    OPTIONAL { ?dataset dcat:landingPage ?landingPageLg2 . FILTER (lang(?landingPageLg2) = '${LG2}') }
    OPTIONAL { ?dataset dcat:keyword ?kwLg1 . FILTER (lang(?kwLg1) = '${LG1}') }
    OPTIONAL { ?dataset dcat:keyword ?kwLg2 . FILTER (lang(?kwLg2) = '${LG2}') }
    OPTIONAL { ?dataset dcterms:temporal/dcat:startDate ?startPeriod }
    OPTIONAL { ?dataset dcterms:temporal/dcat:endDate ?endPeriod }

    OPTIONAL {
        ?dataset dcterms:publisher ?publisher .
        OPTIONAL { ?publisher dcterms:identifier ?idPublisher }
        OPTIONAL { ?publisher skos:prefLabel ?labelPublisherLg1 . FILTER (lang(?labelPublisherLg1) = '${LG1}') }
        OPTIONAL { ?publisher skos:prefLabel ?labelPublisherLg2 . FILTER (lang(?labelPublisherLg2) = '${LG2}') }
    }

    OPTIONAL {
        ?dataset dcterms:type ?type .
        OPTIONAL { ?type skos:prefLabel ?labeltypeLg1 . FILTER (lang(?labeltypeLg1) = '${LG1}') }
        OPTIONAL { ?type skos:prefLabel ?labeltypeLg2 . FILTER (lang(?labeltypeLg2) = '${LG2}') }
    }

    OPTIONAL {
        ?dataset dcterms:accessRights ?accessRights .
        OPTIONAL { ?accessRights skos:prefLabel ?labelaccessRightsLg1 . FILTER (lang(?labelaccessRightsLg1) = '${LG1}') }
        OPTIONAL { ?accessRights skos:prefLabel ?labelaccessRightsLg2 . FILTER (lang(?labelaccessRightsLg2) = '${LG2}') }
    }

    OPTIONAL {
        ?dataset insee:confidentialityStatus ?confStatus .
        OPTIONAL { ?confStatus skos:prefLabel ?labelconfidentialityStatusLg1 . FILTER (lang(?labelconfidentialityStatusLg1) = '${LG1}') }
        OPTIONAL { ?confStatus skos:prefLabel ?labelconfidentialityStatusLg2 . FILTER (lang(?labelconfidentialityStatusLg2) = '${LG2}') }
    }

    OPTIONAL {
        ?dataset dcterms:accrualPeriodicity ?accrualPeriodicity .
        OPTIONAL { ?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg1 . FILTER (lang(?labelaccrualPeriodicityLg1) = '${LG1}') }
        OPTIONAL { ?accrualPeriodicity skos:prefLabel ?labelaccrualPeriodicityLg2 . FILTER (lang(?labelaccrualPeriodicityLg2) = '${LG2}') }
    }

    OPTIONAL {
        ?dataset dcterms:spatial ?spatial .
        OPTIONAL { ?spatial skos:notation ?spatialId }
        OPTIONAL { ?spatial skos:prefLabel ?labelspatialLg1 . FILTER (lang(?labelspatialLg1) = '${LG1}') }
        OPTIONAL { ?spatial skos:prefLabel ?labelspatialLg2 . FILTER (lang(?labelspatialLg2) = '${LG2}') }
    }

    OPTIONAL { ?dataset insee:processStep ?codeProcessStep }

    OPTIONAL {
        ?dataset insee:disseminationStatus ?disseminationStatusUri .
        ?disseminationStatusUri skos:prefLabel ?disseminationStatus .
        FILTER (lang(?disseminationStatus) = '${LG1}')
    }

    OPTIONAL {
        ?dataset insee:structure ?structureUri .
        OPTIONAL { ?structureUri dcterms:identifier ?structureId }
        OPTIONAL { ?structureUri skos:notation ?dsd }
        OPTIONAL {
            ?structureUri rdf:type ?structureType .
            BIND(IF(?structureType = qb:DataStructureDefinition, "true", "false") AS ?isDataStructureDefinition)
        }
    }
}
GROUP BY
    ?dataset ?id ?statutValidation
    ?titleLg1 ?titleLg2 ?subtitleLg1 ?subtitleLg2
    ?abstractLg1 ?abstractLg2 ?descriptionLg1 ?descriptionLg2
    ?scopeNoteLg1 ?scopeNoteLg2 ?landingPageLg1 ?landingPageLg2
    ?catalogRecordCreator ?catalogRecordContributor ?catalogRecordModified ?catalogRecordCreated
    ?modified ?issued ?version ?numObservations ?numSeries ?spatialTemporal
    ?startPeriod ?endPeriod ?derivedDescriptionLg1 ?derivedDescriptionLg2
    ?idPublisher ?labelPublisherLg1 ?labelPublisherLg2
    ?labeltypeLg1 ?labeltypeLg2
    ?labelaccessRightsLg1 ?labelaccessRightsLg2
    ?labelconfidentialityStatusLg1 ?labelconfidentialityStatusLg2
    ?labelaccrualPeriodicityLg1 ?labelaccrualPeriodicityLg2
    ?spatialId ?labelspatialLg1 ?labelspatialLg2
    ?codeProcessStep ?disseminationStatus ?identifier
    ?structureUri ?structureId ?dsd ?isDataStructureDefinition