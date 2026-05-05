SELECT DISTINCT
  ?operationId ?operation
  ?operationLabelLg1 ?operationLabelLg2 ?operationAltLabelLg1 ?operationAltLabelLg2
  ?temporal
  ?seriesId ?series ?seriesLabelLg1 ?seriesLabelLg2
  ?simsId ?sims
  ?created ?modified
  ?validationState
WHERE {
	BIND('${operationId}' AS ?operationId)
	BIND('${LG1}' AS ?lg1)
	BIND('${LG2}' AS ?lg2)

  # Graphe de l'opération
  GRAPH <${OPERATIONS_SERIES_GRAPH}> {
    ?operation a insee:StatisticalOperation ;
    FILTER(STRAFTER(STR(?operation),'/operations/operation/') = ?operationId) .

    # Labels
    OPTIONAL {?operation skos:prefLabel ?operationLabelLg1 . FILTER (lang(?operationLabelLg1) = ?lg1)}
    OPTIONAL {?operation skos:prefLabel ?operationLabelLg2 . FILTER (lang(?operationLabelLg2) = ?lg2)}
    # Alt labels
    OPTIONAL {?operation skos:altLabel ?operationAltLabelLg1 . FILTER (lang(?operationAltLabelLg1) = ?lg1)}
    OPTIONAL {?operation skos:altLabel ?operationAltLabelLg2 . FILTER (lang(?operationAltLabelLg2) = ?lg2)}
    # Millésime
    OPTIONAL {?operation dcterms:temporal ?temporal}
    # Série mère
    OPTIONAL {
      ?series a insee:StatisticalOperationSeries ; (dcterms:hasPart|^dcterms:isPartOf) ?operation .
      BIND(STRAFTER(STR(?series),'/operations/serie/') AS ?seriesId) .
      OPTIONAL {?series skos:prefLabel ?seriesLabelLg1 . FILTER (lang(?seriesLabelLg1) = ?lg1)}
      OPTIONAL {?series skos:prefLabel ?seriesLabelLg2 . FILTER (lang(?seriesLabelLg2) = ?lg2)}
    }
    # Informations de gestion
    OPTIONAL {?operation dcterms:created ?created}
    OPTIONAL {?operation dcterms:modified ?modified}
    OPTIONAL {?operation insee:validationState ?validationState}
  }

  # Graphe du sims
  GRAPH ?simsGraph {
    OPTIONAL {
      ?sims a sdmx-mm:MetadataReport ; sdmx-mm:target ?operation .
      BIND(STRAFTER(STR(?sims),'/qualite/rapport/') AS ?simsId)
    }
  }

}