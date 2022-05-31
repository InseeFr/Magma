select distinct ?uri ?id ?labelOperationLg1 ?labelOperationLg2 
FROM  <http://rdf.insee.fr/graphes/operations>
where {
                values ?uri {<${URI}>}
   				bind(strafter(str(?uri),"/operation/") as ?id)
   				<${URI}> skos:prefLabel ?labelOperationLg1 .
                FILTER (lang(?labelOperationLg1) = 'fr')
				Optional {
			   <${URI}> skos:prefLabel ?labelOperationLg2 .
    			FILTER (lang(?labelOperationLg2) = 'en') }
    
}