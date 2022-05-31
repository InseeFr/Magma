select distinct ?uri ?labelThemeLg1 ?labelThemeLg2 
FROM  <http://rdf.insee.fr/graphes/concepts>
where {
                values ?uri {<${URI}>}
   				<${URI}> skos:prefLabel ?labelThemeLg1 .
    			FILTER (lang(?labelThemeLg1) = 'fr')
    			Optional{
    			<${URI}> skos:prefLabel ?labelThemeLg2 .
                FILTER (lang(?labelThemeLg2) = 'en') }
    
}
