SELECT ?indicatorId  ?indicator
?indicatorLabelLg1 ?indicatorLabelLg2 ?indicatorAltLabelLg1 ?indicatorAltLabelLg2
?indicatorAbstractLg1 ?indicatorAbstractLg2 ?indicatorHistoryNoteLg1 ?indicatorHistoryNoteLg2
?periodicity ?periodicityId ?periodicityLabelLg1 ?periodicityLabelLg2
?wasGeneratedBySeries
?seeAlsoSeries ?seeAlsoIndicators
?sims ?simsId
?created ?modified
?creators ?publishers ?contributors
?validationState
WHERE {
	BIND('${indicatorId}' AS ?indicatorId)
	# NB: pour l'instant, les variables de langue ne sont pas reportées dans les sous-requêtes
	BIND('${LG1}' AS ?lg1)
	BIND('${LG2}' AS ?lg2)

	# Un indicateur est un StatisticalIndicator
	?indicator a insee:StatisticalIndicator .
	FILTER(STRAFTER(STR(?indicator),'/produits/indicateur/') = ?indicatorId ) .

	#Infos sur l'indicateur lui-même (tout en OPTIONAL pour éviter les problèmes)
	#Labels
	OPTIONAL {?indicator skos:prefLabel ?indicatorLabelLg1 . FILTER (lang(?indicatorLabelLg1) = ?lg1)}
	OPTIONAL {?indicator skos:prefLabel ?indicatorLabelLg2 . FILTER (lang(?indicatorLabelLg2) = ?lg2)}
	#Alt labels
	OPTIONAL {?indicator skos:altLabel ?indicatorAltLabelLg1 . FILTER (lang(?indicatorAltLabelLg1) = ?lg1)}
	OPTIONAL {?indicator skos:altLabel ?indicatorAltLabelLg2 . FILTER (lang(?indicatorAltLabelLg2) = ?lg2)}
	#Abstract
	OPTIONAL {?indicator dcterms:abstract ?indicatorAbstractLg1 . FILTER (lang(?indicatorAbstractLg1) = ?lg1)}
	OPTIONAL {?indicator dcterms:abstract ?indicatorAbstractLg2 . FILTER (lang(?indicatorAbstractLg2) = ?lg2)}
	#HistoryNote
	OPTIONAL {?indicator skos:historyNote ?indicatorHistoryNoteLg1 . FILTER (lang(?indicatorHistoryNoteLg1) = ?lg1)}
	OPTIONAL {?indicator skos:historyNote ?indicatorHistoryNoteLg2 . FILTER (lang(?indicatorHistoryNoteLg2) = ?lg2)}
	#Periodicity
	OPTIONAL {
		?indicator dcterms:accrualPeriodicity ?periodicity .
		OPTIONAL {?periodicity skos:notation ?periodicityId}
		OPTIONAL{?periodicity skos:prefLabel ?periodicityLabelLg1 . FILTER (lang(?periodicityLabelLg1) = ?lg1)}
		OPTIONAL{?periodicity skos:prefLabel ?periodicityLabelLg2 . FILTER (lang(?periodicityLabelLg2) = ?lg2)}
	}

	#Séries mères (il peut y en avoir plusieurs)
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(STR(?wasGeneratedBySerieId), '$', STR(?wasGeneratedBySerie), '$', COALESCE(?wasGeneratedBySerieLabelLg1, ''), '$', COALESCE(?wasGeneratedBySerieLabelLg2, '')) ; SEPARATOR='|') AS ?wasGeneratedBySeries)
		WHERE {
			?wasGeneratedBySerie a insee:StatisticalOperationSeries ; (^prov:wasGeneratedBy) ?indicator .
			BIND(STRAFTER(STR(?wasGeneratedBySerie),'/operations/serie/') AS ?wasGeneratedBySerieId ) .
			OPTIONAL {?wasGeneratedBySerie skos:prefLabel ?wasGeneratedBySerieLabelLg1 . FILTER (lang(?wasGeneratedBySerieLabelLg1) = '${LG1}')}
			OPTIONAL {?wasGeneratedBySerie skos:prefLabel ?wasGeneratedBySerieLabelLg2 . FILTER (lang(?wasGeneratedBySerieLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}

	# Autres objets liés à l'indicateur
	#Séries voir aussi
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(STR(?seeAlsoSerieId), '$', STR(?seeAlsoSerie), '$', COALESCE(?seeAlsoSerieLabelLg1, ''), '$', COALESCE(?seeAlsoSerieLabelLg2, '')) ; SEPARATOR='|') AS ?seeAlsoSeries)
		WHERE {
			?seeAlsoSerie a insee:StatisticalOperationSeries ; (^rdfs:seeAlso|rdfs:seeAlso) ?indicator .
			BIND(STRAFTER(STR(?seeAlsoSerie),'/operations/serie/') AS ?seeAlsoSerieId ) .
			OPTIONAL {?seeAlsoSerie skos:prefLabel ?seeAlsoSerieLabelLg1 . FILTER (lang(?seeAlsoSerieLabelLg1) = '${LG1}')}
			OPTIONAL {?seeAlsoSerie skos:prefLabel ?seeAlsoSerieLabelLg2 . FILTER (lang(?seeAlsoSerieLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}
	#Indicateurs voir aussi
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(STR(?seeAlsoIndicatorId), '$', STR(?seeAlsoIndicator), '$', COALESCE(?seeAlsoIndicatorLabelLg1, ''), '$', COALESCE(?seeAlsoIndicatorLabelLg2, '')) ; SEPARATOR='|') AS ?seeAlsoIndicators)
		WHERE {
			?seeAlsoIndicator a insee:StatisticalIndicator ; (^rdfs:seeAlso|rdfs:seeAlso) ?indicator .
			BIND(STRAFTER(STR(?seeAlsoIndicator),'/produits/indicateur/') AS ?seeAlsoIndicatorId ) .
			OPTIONAL {?seeAlsoIndicator skos:prefLabel ?seeAlsoIndicatorLabelLg1 . FILTER (lang(?seeAlsoIndicatorLabelLg1) = '${LG1}')}
			OPTIONAL {?seeAlsoIndicator skos:prefLabel ?seeAlsoIndicatorLabelLg2 . FILTER (lang(?seeAlsoIndicatorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}
	#Sims
	OPTIONAL {
		?sims a sdmx-mm:MetadataReport; sdmx-mm:target ?indicator .
		BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId)
	}

	# Informations de gestion
	# Dates
	OPTIONAL {?indicator dcterms:created ?created}
	OPTIONAL {?indicator dcterms:modified ?modified}

	# Agents
	# Creator
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?creatorId, ''), '$', STR(?creator), '$', COALESCE(?creatorLabelLg1, ''), '$', COALESCE(?creatorLabelLg2, '')) ; SEPARATOR='|') AS ?creators)
		WHERE {
			?creator a org:Organization ; ^dc:creator ?indicator .
			OPTIONAL{?creator dcterms:identifier ?creatorId}
			OPTIONAL {?creator skos:prefLabel ?creatorLabelLg1 . FILTER (lang(?creatorLabelLg1) = '${LG1}')}
			OPTIONAL {?creator skos:prefLabel ?creatorLabelLg2 . FILTER (lang(?creatorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}
	# Publisher
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?publisherId, ''), '$', STR(?publisher), '$', COALESCE(?publisherLabelLg1, ''), '$', COALESCE(?publisherLabelLg2, '')) ; SEPARATOR='|') AS ?publishers)
		WHERE {
			?publisher a org:Organization ; ^dcterms:publisher ?indicator .
			OPTIONAL{?publisher dcterms:identifier ?publisherId}
			OPTIONAL {?publisher skos:prefLabel ?publisherLabelLg1 . FILTER (lang(?publisherLabelLg1) = '${LG1}')}
			OPTIONAL {?publisher skos:prefLabel ?publisherLabelLg2 . FILTER (lang(?publisherLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}
	# Contributor
	OPTIONAL {
		SELECT ?indicator (GROUP_CONCAT(DISTINCT CONCAT(COALESCE(?contributorId, ''), '$', STR(?contributor), '$', COALESCE(?contributorLabelLg1, ''), '$', COALESCE(?contributorLabelLg2, '')) ; SEPARATOR='|') AS ?contributors)
		WHERE {
			?contributor a org:Organization ; ^dcterms:contributor ?indicator .
			OPTIONAL{?contributor dcterms:identifier ?contributorId}
			OPTIONAL {?contributor skos:prefLabel ?contributorLabelLg1 . FILTER (lang(?contributorLabelLg1) = '${LG1}')}
			OPTIONAL {?contributor skos:prefLabel ?contributorLabelLg2 . FILTER (lang(?contributorLabelLg2) = '${LG2}')}
		}
		GROUP BY ?indicator
	}

	# Statut
	OPTIONAL {?indicator insee:validationState ?validationState}
}