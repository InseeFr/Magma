select ?identifier ?uri ?descriptionLg2 ?descriptionLg1 ?titleLg1 ?titleLg2 ?byteSize ?created ?modified ?downloadURL ?format
FROM <http://rdf.insee.fr/graphes/catalogue>

where {
?uriDistribution <http://www.w3.org/dcat#distribution> ?uri
FILTER(STRAFTER(STR(?uriDistribution),'http://bauhaus/datasets/') = "${ID}" )
?uri dct:identifier ?identifier .
OPTIONAL{ ?uri dct:description ?descriptionLg1 .
FILTER(lang(?descriptionLg1) = '${LG1}')
}
OPTIONAL{ ?uri dct:description ?descriptionLg2 .
FILTER(lang(?descriptionLg2) = '${LG2}')
}
OPTIONAL{ ?uri dct:title ?titleLg1 .
FILTER(lang(?titleLg1) = '${LG1}')
}
OPTIONAL{ ?uri dct:title ?titleLg2 .
FILTER(lang(?titleLg2) = '${LG2}')
}
OPTIONAL{ ?uri <http://www.w3.org/dcat#byteSize> ?byteSize}
OPTIONAL{ ?uri dct:created ?created }
OPTIONAL{ ?uri dct:modified ?modified }
OPTIONAL{ ?uri <http://www.w3.org/dcat#downloadURL> ?downloadURL}
OPTIONAL{ ?uri dct:format ?format}

}