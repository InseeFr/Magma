SELECT ?series ?seriesId ?seriesLabelLg1 ?seriesLabelLg2
WHERE {
	BIND('${LG1}' AS ?lg1)
	BIND('${LG2}' AS ?lg2)

	?series a insee:StatisticalOperationSeries .
	#Id
	BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId ) .
	#Labels
	OPTIONAL {?series skos:prefLabel ?seriesLabelLg1 . FILTER (lang(?seriesLabelLg1) = ?lg1)}
	OPTIONAL {?series skos:prefLabel ?seriesLabelLg2 . FILTER (lang(?seriesLabelLg2) = ?lg2)}

	#Filtre sur la date de la série ou de tout objet lié :
	#Pour gagner en performance, ne calculer la date que si nécessaire
	<#if date != "none">
	#hasPart et isPartOf pour les opérations, wasGeneratedBy pour les indicateurs, target pour les rapports qualité
	OPTIONAL {
		SELECT ?series (MAX(?allDates) AS ?lastUpdate)
		WHERE {
			?series (dcterms:hasPart|^dcterms:isPartOf|^prov:wasGeneratedBy)?/(^sdmx-mm:target)?/dcterms:modified ?allDates.
		}
		GROUP BY ?series
	}
	FILTER(?lastUpdate >= '${date}'^^xsd:date)
	</#if>
}
ORDER BY ?seriesId