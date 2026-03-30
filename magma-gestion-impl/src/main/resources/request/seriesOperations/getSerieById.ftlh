SELECT ?seriesId  ?series
?seriesLabelLg1 ?seriesLabelLg2 ?seriesAltLabelLg1 ?seriesAltLabelLg2
?seriesAbstractLg1 ?seriesAbstractLg2 ?seriesHistoryNoteLg1 ?seriesHistoryNoteLg2
?type ?typeID ?typeLabelLg1 ?typeLabelLg2
?periodicity ?periodicityId ?periodicityLabelLg1 ?periodicityLabelLg2
?families
?seeAlsoSeries ?previousSeries ?nextSeries
?operations ?indicators
?sims ?simsId
?created ?modified
?creators ?publishers ?contributors ?dataCollectors
?validationState
WHERE {
	BIND('${idSeries}' AS ?seriesId)
	# NB: pour l'instant, les variables de langue ne sont pas reportées dans les sous-requêtes
	BIND('${LG1}' AS ?lg1)
	BIND('${LG2}' AS ?lg2)

	# Une série est une StatisticalOperationSeries
	?series a insee:StatisticalOperationSeries .
	# Pas de notation, donc filtre pourri pour sélectionner la bonne série
	FILTER(STRAFTER(STR(?series),'/operations/serie/') = ?seriesId)

	#Infos sur la série elle-même (tout en OPTIONAL pour éviter les problèmes)
	#Labels
	OPTIONAL {?series skos:prefLabel ?seriesLabelLg1 . FILTER (lang(?seriesLabelLg1) = ?lg1)}
	OPTIONAL {?series skos:prefLabel ?seriesLabelLg2 . FILTER (lang(?seriesLabelLg2) = ?lg2)}
	#Alt labels
	OPTIONAL {?series skos:altLabel ?seriesAltLabelLg1 . FILTER (lang(?seriesAltLabelLg1) = ?lg1)}
	OPTIONAL {?series skos:altLabel ?seriesAltLabelLg2 . FILTER (lang(?seriesAltLabelLg2) = ?lg2)}
	#Abstract
	OPTIONAL {?series dcterms:abstract ?seriesAbstractLg1 . FILTER (lang(?seriesAbstractLg1) = ?lg1)}
	OPTIONAL {?series dcterms:abstract ?seriesAbstractLg2 . FILTER (lang(?seriesAbstractLg2) = ?lg2)}
	#HistoryNote
	OPTIONAL {?series skos:historyNote ?seriesHistoryNoteLg1 . FILTER (lang(?seriesHistoryNoteLg1) = ?lg1)}
	OPTIONAL {?series skos:historyNote ?seriesHistoryNoteLg2 . FILTER (lang(?seriesHistoryNoteLg2) = ?lg2)}
	#Type
	OPTIONAL {
		?series dcterms:type ?type .
		OPTIONAL {?type skos:notation ?typeID}
		OPTIONAL {?type skos:prefLabel ?typeLabelLg1 . FILTER (LANG(?typeLabelLg1) = ?lg1)}
		OPTIONAL {?type skos:prefLabel ?typeLabelLg2 . FILTER (LANG(?typeLabelLg2) = ?lg2)}
	}
	#Periodicity
	OPTIONAL {
		?series dcterms:accrualPeriodicity ?periodicity .
		OPTIONAL {?periodicity skos:notation ?periodicityId}
		OPTIONAL{?periodicity skos:prefLabel ?periodicityLabelLg1 . FILTER (lang(?periodicityLabelLg1) = ?lg1)}
		OPTIONAL{?periodicity skos:prefLabel ?periodicityLabelLg2 . FILTER (lang(?periodicityLabelLg2) = ?lg2)}
	}

	# Autres objets liés à la série
	#Famille
	OPTIONAL {
		SELECT DISTINCT ?series (CONCAT(STR(?familyId), '$', STR(?family), '$', COALESCE(?familyLabelLg1, ''), '$', COALESCE(?familyLabelLg2, '')) AS ?families)
		WHERE {
			?family a insee:StatisticalOperationFamily ; (dcterms:hasPart|^dcterms:isPartOf) ?series .
			BIND(STRAFTER(STR(?family),'/operations/famille/') AS ?familyId ) .
			OPTIONAL {?family skos:prefLabel ?familyLabelLg1 . FILTER (lang(?familyLabelLg1) = '${LG1}')}
			OPTIONAL {?family skos:prefLabel ?familyLabelLg2 . FILTER (lang(?familyLabelLg2) = '${LG2}')}
		}
	}
	#Séries voir aussi
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(STR(?seeAlsoSerieId), '$', STR(?seeAlsoSerie), '$', COALESCE(?seeAlsoSerieLabelLg1, ''), '$', COALESCE(?seeAlsoSerieLabelLg2, '')) ; SEPARATOR='|') AS ?seeAlsoSeries)
		WHERE {
			?seeAlsoSerie a insee:StatisticalOperationSeries ; (^rdfs:seeAlso|rdfs:seeAlso) ?series .
			BIND(STRAFTER(STR(?seeAlsoSerie),'/operations/serie/') AS ?seeAlsoSerieId ) .
			OPTIONAL {?seeAlsoSerie skos:prefLabel ?seeAlsoSerieLabelLg1 . FILTER (lang(?seeAlsoSerieLabelLg1) = '${LG1}')}
			OPTIONAL {?seeAlsoSerie skos:prefLabel ?seeAlsoSerieLabelLg2 . FILTER (lang(?seeAlsoSerieLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	#Séries précédents
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(STR(?previousSerieId), '$', STR(?previousSerie), '$', COALESCE(?previousSerieLabelLg1, ''), '$', COALESCE(?previousSerieLabelLg2, '')) ; SEPARATOR='|') AS ?previousSeries)
		WHERE {
			?previousSerie a insee:StatisticalOperationSeries ; (^dcterms:replaces|dcterms:isReplacedBy) ?series .
			BIND(STRAFTER(STR(?previousSerie),'/operations/serie/') AS ?previousSerieId ) .
			OPTIONAL {?previousSerie skos:prefLabel ?previousSerieLabelLg1 . FILTER (lang(?previousSerieLabelLg1) = '${LG1}')}
			OPTIONAL {?previousSerie skos:prefLabel ?previousSerieLabelLg2 . FILTER (lang(?previousSerieLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	#Séries suivantes
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(STR(?nextSerieId), '$', STR(?nextSerie), '$', COALESCE(?nextSerieLabelLg1, ''), '$', COALESCE(?nextSerieLabelLg2, '')) ; SEPARATOR='|') AS ?nextSeries)
		WHERE {
			?nextSerie a insee:StatisticalOperationSeries ; (dcterms:replaces|^dcterms:isReplacedBy) ?series .
			BIND(STRAFTER(STR(?nextSerie),'/operations/serie/') AS ?nextSerieId ) .
			OPTIONAL {?nextSerie skos:prefLabel ?nextSerieLabelLg1 . FILTER (lang(?nextSerieLabelLg1) = '${LG1}')}
			OPTIONAL {?nextSerie skos:prefLabel ?nextSerieLabelLg2 . FILTER (lang(?nextSerieLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	#Opérations
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(STR(?operationId), '$', STR(?operation), '$', COALESCE(?operationLabelLg1, ''), '$', COALESCE(?operationLabelLg2, '')) ; SEPARATOR='|') AS ?operations)
		WHERE {
			?operation a insee:StatisticalOperation ; (^dcterms:hasPart|dcterms:isPartOf) ?series .
			BIND(STRAFTER(STR(?operation),'/operations/operation/') AS ?operationId ) .
			OPTIONAL {?operation skos:prefLabel ?operationLabelLg1 . FILTER (lang(?operationLabelLg1) = '${LG1}')}
			OPTIONAL {?operation skos:prefLabel ?operationLabelLg2 . FILTER (lang(?operationLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	#Indicateurs
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(STR(?indicatorId), '$', STR(?indicator), '$', COALESCE(?indicatorLabelLg1, ''), '$', COALESCE(?indicatorLabelLg2, '')) ; SEPARATOR='|') AS ?indicators)
		WHERE {
			?indicator a insee:StatisticalIndicator ; prov:wasGeneratedBy ?series .
			BIND(STRAFTER(STR(?indicator),'/produits/indicateur/') AS ?indicatorId ) .
			OPTIONAL {?indicator skos:prefLabel ?indicatorLabelLg1 . FILTER (lang(?indicatorLabelLg1) = '${LG1}')}
			OPTIONAL {?indicator skos:prefLabel ?indicatorLabelLg2 . FILTER (lang(?indicatorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	#Sims
	OPTIONAL {
		?sims a sdmx-mm:MetadataReport; sdmx-mm:target ?series .
		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId)
	}


	# Informations de gestion
	# Dates
	OPTIONAL {?series dcterms:created ?created}
	OPTIONAL {?series dcterms:modified ?modified}

	# Agents
	# Creator
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?creatorId, ''), '$', STR(?creator), '$', COALESCE(?creatorLabelLg1, ''), '$', COALESCE(?creatorLabelLg2, '')) ; SEPARATOR='|') AS ?creators)
		WHERE {
			?creator a org:Organization ; ^dc:creator ?series .
			OPTIONAL{?creator dcterms:identifier ?creatorId}
			OPTIONAL {?creator skos:prefLabel ?creatorLabelLg1 . FILTER (lang(?creatorLabelLg1) = '${LG1}')}
			OPTIONAL {?creator skos:prefLabel ?creatorLabelLg2 . FILTER (lang(?creatorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	# Publisher
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?publisherId, ''), '$', STR(?publisher), '$', COALESCE(?publisherLabelLg1, ''), '$', COALESCE(?publisherLabelLg2, '')) ; SEPARATOR='|') AS ?publishers)
		WHERE {
			?publisher a org:Organization ; ^dcterms:publisher ?series .
			OPTIONAL{?publisher dcterms:identifier ?publisherId}
			OPTIONAL {?publisher skos:prefLabel ?publisherLabelLg1 . FILTER (lang(?publisherLabelLg1) = '${LG1}')}
			OPTIONAL {?publisher skos:prefLabel ?publisherLabelLg2 . FILTER (lang(?publisherLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	# Contributor
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?contributorId, ''), '$', STR(?contributor), '$', COALESCE(?contributorLabelLg1, ''), '$', COALESCE(?contributorLabelLg2, '')) ; SEPARATOR='|') AS ?contributors)
		WHERE {
			?contributor a org:Organization ; ^dcterms:contributor ?series .
			OPTIONAL{?contributor dcterms:identifier ?contributorId}
			OPTIONAL {?contributor skos:prefLabel ?contributorLabelLg1 . FILTER (lang(?contributorLabelLg1) = '${LG1}')}
			OPTIONAL {?contributor skos:prefLabel ?contributorLabelLg2 . FILTER (lang(?contributorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}
	# Data collector
	OPTIONAL {
		SELECT ?series (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?dataCollectorId, ''), '$', STR(?dataCollector), '$', COALESCE(?dataCollectorLabelLg1, ''), '$', COALESCE(?dataCollectorLabelLg2, '')) ; SEPARATOR='|') AS ?dataCollectors)
		WHERE {
			?dataCollector a org:Organization ; ^insee:dataCollector ?series .
			OPTIONAL{?dataCollector dcterms:identifier ?dataCollectorId}
			OPTIONAL {?dataCollector skos:prefLabel ?dataCollectorLabelLg1 . FILTER (lang(?dataCollectorLabelLg1) = '${LG1}')}
			OPTIONAL {?dataCollector skos:prefLabel ?dataCollectorLabelLg2 . FILTER (lang(?dataCollectorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?series
	}

	# Statut
	OPTIONAL {?series insee:validationState ?validationState}
}