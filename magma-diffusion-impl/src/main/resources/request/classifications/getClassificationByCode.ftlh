SELECT DISTINCT ( ?poste AS ?uri ) ( ?label AS ?intitule )

WHERE {

	?nomenclature a skos:ConceptScheme ; xkos:levels/rdf:rest*/rdf:first ?niveau .

	?niveau a xkos:ClassificationLevel ; skos:member ?poste .

	?poste a skos:Concept ; skos:notation "${code}" ; skos:notation ?notation ; skos:prefLabel ?label .

	FILTER( REPLACE( STR(?nomenclature), '^http://id.insee.fr/codes/([^/]+).*$','$1' ) = "${nomenclature}" )

	FILTER( REPLACE( STR(?niveau), '^.+/([^/]+)$','$1' ) = "${niveau}" )

	FILTER( LANG(?label) = 'fr' )

	FILTER( STRENDS( STR(?poste), STR(?notation) ) )

}
