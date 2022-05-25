select distinct ?uri ?labelThemeLg1 ?labelThemeLg2 
FROM  <http://rdf.insee.fr/graphes/concepts>
where {

   		   			?uri skos:notation ?identifier .
    					FILTER(str(?uri) = '${ID}')
   				Optional{
   				?uri skos:prefLabel ?labelTheme ;
    			skos:prefLabel ?labelThemeLg1 ;
           	    skos:prefLabel ?labelThemeLg2 .
    			FILTER (lang(?labelThemeLg1) = 'fr')
                FILTER (lang(?labelThemeLg2) = 'en') }
    
}
