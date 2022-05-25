select distinct ?uri ?id ?labelSerieLg1 ?labelSerieLg2 
FROM  <http://rdf.insee.fr/graphes/operations>
where {
    			values ?uri {<${URI}>}
                bind(strafter(str(?uri),"/serie/") as ?id)
				<${URI}> skos:prefLabel ?labelSerieLg1 .
    			FILTER (lang(?labelSerieLg1) = 'fr')
    			Optional {
    			<${URI}> skos:prefLabel ?labelSerieLg2 .
				FILTER (lang(?labelSerieLg2) = 'en') }
    
}