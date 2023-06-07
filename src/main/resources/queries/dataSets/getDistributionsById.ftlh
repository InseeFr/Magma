select ?identifier ?uri ?descriptionLg2 ?descriptionLg1 ?titleLg1 ?titleLg2 ?byteSize ?created ?modified ?downloadURL ?format
FROM <http://rdf.insee.fr/graphes/catalogue>

where {
        ?uriDistribution dcat:distribution ?uri
        FILTER(STRAFTER(STR(?uriDistribution),'http://bauhaus/datasets/') = "${ID}" )
        ?uri dcterms:identifier ?identifier .
        OPTIONAL{ ?uri dcterms:description ?descriptionLg1 .
            FILTER(lang(?descriptionLg1) = '${LG1}')
        }
        OPTIONAL{ ?uri dcterms:description ?descriptionLg2 .
            FILTER(lang(?descriptionLg2) = '${LG2}')
        }
        OPTIONAL{ ?uri dcterms:title ?titleLg1 .
            FILTER(lang(?titleLg1) = '${LG1}')
        }
        OPTIONAL{ ?uri dcterms:title ?titleLg2 .
            FILTER(lang(?titleLg2) = '${LG2}')
        }
        OPTIONAL{ ?uri dcat:byteSize ?byteSize}
        OPTIONAL{ ?uri dcterms:created ?created }
        OPTIONAL{ ?uri dcterms:modified ?modified }
        OPTIONAL{ ?uri dcat:downloadURL ?downloadURL}
        OPTIONAL{ ?uri dcterms:format ?format}

}