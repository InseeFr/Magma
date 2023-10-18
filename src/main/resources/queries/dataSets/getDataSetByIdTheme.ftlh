select distinct ?uri ?labelThemeLg1 ?labelThemeLg2 ?themeTaxonomy
FROM <${DATASETS_GRAPH}>
FROM <${CONCEPTS_GRAPH}>

where {
                values ?uri {<${URI}>}
   				<${URI}> skos:prefLabel ?labelThemeLg1 .
    			FILTER (lang(?labelThemeLg1) = '${LG1}')
				OPTIONAL{
    			<${URI}> skos:prefLabel ?labelThemeLg2 .
                FILTER (lang(?labelThemeLg2) = '${LG2}') }
				OPTIONAL{
				<${URI}> skos:inScheme ?themeDetail .
				?themeDetail skos:notation ?themeTaxonomy}

    
}
