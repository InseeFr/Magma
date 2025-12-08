SELECT distinct ?uri ?intituleFr ?intituleEn 
?definitionFr ?definitionEn ?noteEditorialeFr ?noteEditorialeEn ?scopeNoteFr ?scopeNoteEn
 ?dateMiseAJour ?dateCreation ?dateFinDeValidite ?hasLink ?id
FROM <http://rdf.insee.fr/graphes/concepts/definitions>
WHERE {
			BIND("${id}" AS ?id) .
			?uri skos:inScheme ?uriScheme .
			FILTER(REGEX(STR(?uriScheme),'/concepts/definitions/scheme')) 
			FILTER(REGEX(STR(?uri),'/concepts/definition/${id}')) .

			?uri skos:prefLabel ?intituleFr . 
			FILTER(lang(?intituleFr) = 'fr') 
			?uri skos:prefLabel ?intituleEn . 
			FILTER(lang(?intituleEn) = 'en')

			BIND(EXISTS{?uri (skos:broader|skos:narrower|skos:related|dcterms:replaces|dcterms:isReplacedBy|skos:closeMatch|dcterms:references) ?repl } AS ?hasLink)
			
			OPTIONAL{ ?uri dcterms:modified ?dateMiseAJour }
			OPTIONAL{ ?uri dcterms:created ?dateCreation  }
			OPTIONAL{ ?uri dcterms:valid ?dateFinDeValidite }
		
			
			OPTIONAL {
				?uri skos:definition ?defFr . 
				?defFr dcterms:language 'fr'^^xsd:language . 
				?defFr evoc:noteLiteral ?definitionFr . 
				?defFr pav:version ?vdfr . 
				filter not exists {
					?uri skos:definition ?def2Fr . 
					?def2Fr dcterms:language 'fr'^^xsd:language . 
					?def2Fr pav:version ?v2dfr .
					filter (?v2dfr > ?vdfr)
				}
			}  
			
			OPTIONAL {
				?uri skos:definition ?defEn . 
				?defEn dcterms:language 'en'^^xsd:language . 
				?defEn evoc:noteLiteral ?definitionEn . 
				?defEn pav:version ?vden . 
				filter not exists {
					?uri skos:definition ?def2En . 
					?def2En dcterms:language 'en'^^xsd:language . 
					?def2En pav:version ?v2den .
					filter (?v2den > ?vden)
				}
			}   

			OPTIONAL {
				?uri skos:scopeNote ?scopeFr . 
				?scopeFr dcterms:language 'fr'^^xsd:language . 
				?scopeFr evoc:noteLiteral ?scopeNoteFr . 
				?scopeFr pav:version ?vsfr .
				filter not exists {
					?uri skos:scopeNote ?scope2Fr .
					?scope2Fr dcterms:language 'fr'^^xsd:language . 
					?scope2Fr pav:version ?v2sfr .
					filter (?v2sfr > ?vsfr)
				}				
			}   

			OPTIONAL {
				?uri skos:scopeNote ?scopeEn . 
				?scopeEn dcterms:language 'en'^^xsd:language . 
				?scopeEn evoc:noteLiteral ?scopeNoteEn . 
				?scopeEn pav:version ?vsen . 
				filter not exists {
					?uri skos:scopeNote ?scope2En .
					?scope2En dcterms:language 'en'^^xsd:language . 
					?scope2En pav:version ?v2sen .
					filter (?v2sen > ?vsen)
				}
			} 

			OPTIONAL {
				?uri skos:editorialNote ?editorialFr . 
				?editorialFr dcterms:language 'fr'^^xsd:language . 
				?editorialFr evoc:noteLiteral ?noteEditorialeFr .
				?editorialFr pav:version ?vefr .
				filter not exists {
					?uri skos:editorialNote ?editorial2Fr . 
					?editorial2Fr dcterms:language 'fr'^^xsd:language . 
					?editorial2Fr pav:version ?v2efr .
					filter (?v2efr > ?vefr)
				}				
			} 

			OPTIONAL {
				?uri skos:editorialNote ?editorialEn . 
				?editorialEn dcterms:language 'en'^^xsd:language . 
				?editorialEn evoc:noteLiteral ?noteEditorialeEn .
				?editorialEn pav:version ?veen . 
				filter not exists {
					?uri skos:editorialNote ?editorial2En . 
					?editorial2En dcterms:language 'en'^^xsd:language . 
					?editorial2En pav:version ?v2een .
					filter (?v2een > ?veen)
				}
			} 

}
ORDER BY DESC(?dateMiseAJour) 
LIMIT 1