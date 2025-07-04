SELECT DISTINCT ?uri ?code (STRAFTER(STR(?typeAvecIri), "#") AS ?type) ?typeArticle ?intitule ?intituleSansArticle ?dateCreation ?dateSuppression ?chefLieu ?typeDIris
FROM
<http://rdf.insee.fr/graphes/geo/cog>
WHERE {
?origine a igeo:${typeOrigine} ;
igeo:codeINSEE '${code}' .

<#if ascendant == "true">
    ?origine igeo:subdivisionDirecteDe+ ?uri .
<#else>
    ?uri igeo:subdivisionDirecteDe+ ?origine .
</#if>

?uri igeo:codeINSEE ?code ;
a ?typeAvecIri ;
igeo:codeArticle ?typeArticle ;
igeo:nom ?intitule ;
igeo:nomSansArticle ?intituleSansArticle .
Optional{
?uri    igeo:typeDIRIS ?uriTypeDIris ;
BIND(SUBSTR(STR(?uriTypeDIris ), STRLEN(STR(?uriTypeDIris )), 1) AS ?typeDIris)
}

<#if typeEnumDescendantsAireDAttractionDesVilles != "none">
    ?uri a igeo:${typeEnumDescendantsAireDAttractionDesVilles} .
</#if>

<#if typeEnumDescendantsArrondissement != "none">
    ?uri a igeo:${typeEnumDescendantsArrondissement} .
</#if>

<#if typeEnumAscendantsArrondissement != "none">
    ?uri a igeo:${typeEnumAscendantsArrondissement} .
</#if>

<#if typeEnumAscendantsArrondissementMunicipal != "none">
    ?uri a igeo:${typeEnumAscendantsArrondissementMunicipal} .
</#if>

<#if typeEnumDescendantsBassinDeVie != "none">
    ?uri a igeo:${typeEnumDescendantsBassinDeVie} .
</#if>

<#if typeEnumAscendantsCanton != "none">
    ?uri a igeo:${typeEnumAscendantsCanton} .
</#if>

<#if typeEnumAscendantsCantonOuVille != "none">
    ?uri a igeo:${typeEnumAscendantsCantonOuVille} .
</#if>

<#if typeEnumDescendantsCantonOuVille != "none">
    ?uri a igeo:${typeEnumDescendantsCantonOuVille} .
</#if>

<#if typeEnumAscendantsCirconscriptionTerritoriale != "none">
    ?uri a igeo:${typeEnumAscendantsCirconscriptionTerritoriale} .
</#if>

<#if typeEnumDescendantsCollectiviteDOutreMer != "none">
    ?uri a igeo:${typeEnumDescendantsCollectiviteDOutreMer} .
</#if>


<#if typeEnumAscendantsCommune != "none">
    ?uri a igeo:${typeEnumAscendantsCommune} .
</#if>

<#if typeEnumDescendantsCommune != "none">
    ?uri a igeo:${typeEnumDescendantsCommune} .
</#if>

<#if typeEnumAscendantsCommuneAssociee != "none">
    ?uri a igeo:${typeEnumAscendantsCommuneAssociee} .
</#if>

<#if typeEnumAscendantsCommuneDeleguee != "none">
    ?uri a igeo:${typeEnumAscendantsCommuneDeleguee} .
</#if>

<#if typeEnumAscendantsDepartement != "none">
    ?uri a igeo:${typeEnumAscendantsDepartement} .
</#if>

<#if typeEnumDescendantsDepartement != "none">
    ?uri a igeo:${typeEnumDescendantsDepartement} .
</#if>

<#if typeEnumAscendantsDistrict != "none">
    ?uri a igeo:${typeEnumAscendantsDistrict} .
</#if>

<#if typeEnumDescendantsIntercommunalite != "none">
    ?uri a igeo:${typeEnumDescendantsIntercommunalite} .
</#if>

<#if typeEnumDescendantsRegion != "none">
    ?uri a igeo:${typeEnumDescendantsRegion} .
</#if>

<#if typeEnumDescendantsUniteUrbaine != "none">
    ?uri a igeo:${typeEnumDescendantsUniteUrbaine} .
</#if>

<#if typeEnumDescendantsZoneDEmploi != "none">
    ?uri a igeo:${typeEnumDescendantsZoneDEmploi} .
</#if>

OPTIONAL {
?uri (igeo:sousPrefecture|igeo:prefecture|igeo:prefectureDeRegion|igeo:bureauCentralisateur) ?chefLieuRDF .
?chefLieuRDF igeo:codeINSEE ?chefLieu .

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

OPTIONAL {
?evenementCreationOrigine igeo:creation ?origine ;
igeo:date ?dateCreationOrigine.
}

OPTIONAL {
?evenementSuppressionOrigine igeo:suppression ?origine ;
igeo:date ?dateSuppressionOrigine.
}


FILTER(!BOUND(?dateCreationOrigine) || ?dateCreationOrigine <= '${date}'^^xsd:date)


FILTER(!BOUND(?dateSuppressionOrigine) || ?dateSuppressionOrigine > '${date}'^^xsd:date)

OPTIONAL {
?evenementCreation igeo:creation ?uri ;
igeo:date ?dateCreation .
}
OPTIONAL {
?evenementSuppression igeo:suppression ?uri ;
igeo:date ?dateSuppression.
}


<#if filtreNom !='*'>
    BIND("${filtreNom}" AS ?query).
    # Formattage du nom avec article pour comparaison non polluée par majuscules et accents
    BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intitule), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_\']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNom) .
    # Formattage du nom sans article pour comparaison non polluée par majuscules et accents
    BIND (REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?intituleSansArticle), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "[-_']", " "), "[^a-z0-9() ]", ""), "[ ]{2,}", " ") AS ?formattedNomSansArticle) .
    # Formattage de la chaîne utilisateur pour comparaison non polluée par majuscules et accents (avec recodage saint/sainte et sur/sous). Ajout d’un ^ au début pour chercher les nom qui commencent par la chaîne utilisateur
    BIND (CONCAT("^", REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(LCASE(?query), "[àáâãäåaaa]", "a"), "ç", "c"), "[èééêë]", "e"), "[ìíîïì]", "i"), "[óôõö]", "o"), "[ùúûü]", "u"), "ÿ", "y"), "[œ]", "oe"), "([^/]+)[/]", "$1-sur-"),"([^\\\\]+)[\\\\]", "$1-sous-"), "[-_']", " "),"[^a-z0-9() ]", ""), "[ ]{2,}", " "), "^st(e)? ", "saint$1 "), "") AS ?formattedQuery) .
    # Filtrage par comparaison de la chaîne utilisateur formatée avec le nom formaté avec et sans article
    FILTER (REGEX(?formattedNom, ?formattedQuery) || REGEX(?formattedNomSansArticle, ?formattedQuery))
    ## Fin du filtre sur le libellé ##
</#if>
FILTER(!BOUND(?dateCreation) || ?dateCreation <= '${date}'^^xsd:date)
FILTER(!BOUND(?dateSuppression) || ?dateSuppression > '${date}'^^xsd:date)

}
ORDER BY ?type ?code