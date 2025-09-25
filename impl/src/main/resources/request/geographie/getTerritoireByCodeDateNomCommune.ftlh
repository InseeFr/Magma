<#ftl output_format="plainText">
SELECT DISTINCT ?uri (STRAFTER(STR(?typeAvecIri), "#") AS ?type) ?code ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu ?intitule ?categorieJuridique ?intituleComplet
FROM <http://rdf.insee.fr/graphes/geo/cog>
FROM <http://rdf.insee.fr/graphes/codes/cj>
WHERE {

	?uri a igeo:${typeOrigine} ;
	a ?typeAvecIri ;
	igeo:codeArticle ?typeArticle ;


	igeo:nom ?intitule ;
	igeo:nomSansArticle ?intituleSansArticle ;


<#if com != "true">
	(igeo:subdivisionDirecteDe/a) ?parent;
</#if>


<#if typeOrigine == 	"Intercommunalite">


	OPTIONAL {
		?uri a igeo:${typeOrigine} ;
		igeo:nomIntercommunalite ?intituleComplet .
	}
</#if>
	OPTIONAL {
		?uri a igeo:${typeOrigine} ;
		insee:categorieJuridique ?cj.
		?cj skos:prefLabel ?categorieJuridique.
	}

<#if code != "none">
	?uri igeo:codeINSEE '${code}' .
	BIND('${code}' as ?code)
<#else>
	?uri igeo:codeINSEE ?code .
</#if>
	OPTIONAL {
		?evenementCreation igeo:creation ?uri ;
		igeo:date ?dateCreation .
}

	OPTIONAL {
		?evenementSuppression igeo:suppression ?uri ;
		igeo:date ?dateSuppression.
	}

<#if chefLieu != "none">
	OPTIONAL {
		?uri igeo:${chefLieu} ?chefLieuRDF .
		?chefLieuRDF igeo:codeINSEE ?chefLieu.
	OPTIONAL {
		?evenementCreationChefLieu igeo:creation ?chefLieuRDF ;
		igeo:date ?dateCreationChefLieu .
	}
	OPTIONAL {
		?evenementSuppressionChefLieu igeo:suppression ?chefLieuRDF ;
		igeo:date ?dateSuppressionChefLieu.
	}

	<#if date != "*">
		FILTER(!BOUND(?dateCreationChefLieu) || ?dateCreationChefLieu <= '${date}'^^xsd:date)
		FILTER(!BOUND(?dateSuppressionChefLieu) || ?dateSuppressionChefLieu > '${date}'^^xsd:date)
	</#if>

	}
</#if>
<#if filtreNom != "*">
	BIND("${filtreNom}" AS ?query).
	BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intitule), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_\']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNom) .
	BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intituleSansArticle), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNomSansArticle) .
	BIND (CONCAT("^", REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?query), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "([^/]+)[/]", "$1-sur-"),"([^\\\\]+)[\\\\]", "$1-sous-"), "[-_']", " "),"[^a-z0-9() ]", ""), "[ ]{2,}", " "), "^st(e)? ", "saint$1 "), "") AS ?formattedQuery) .
	FILTER (REGEX(?formattedNom, ?formattedQuery) || REGEX(?formattedNomSansArticle, ?formattedQuery))
</#if>
<#if com != "true">
	FILTER(REGEX(STR(?parent), "(Departement|Arrondissement)$"))
</#if>
<#if date != "*">
	FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
	FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)
</#if>


}
ORDER BY ?code
		