SELECT distinct ?uri ?id ?idParent ?titreLg1 ?titreLg2 ?type ?valeurSimple ?labelLg1 ?labelLg2 ?codeUri ?organisationUri ?hasDocLg1 ?hasDocLg2 ?labelObjLg1 ?labelObjLg2  ?maxOccurs ?geoUri   
            	FROM <http://rdf.insee.fr/graphes/qualite/rapport/${idSims}> 
            	FROM <http://rdf.insee.fr/graphes/codes> 
            	FROM <http://rdf.insee.fr/graphes/organisations> 
            	FROM <http://rdf.insee.fr/graphes/organisations/insee> 
            	FROM <http://rdf.insee.fr/graphes/concepts/qualite> 
            	FROM <http://rdf.insee.fr/graphes/qualite/simsv2fr> 
            	FROM NAMED <http://rdf.insee.fr/graphes/geo/cog>
            	FROM NAMED <http://rdf.insee.fr/graphes/qualite/territoires>
WHERE {
?report rdf:type sdmx-mm:MetadataReport .
?reporturi sdmx-mm:metadataReport ?report .
OPTIONAL {
	?mas sdmx-mm:metadataAttributeProperty ?uri .
    ?mas sdmx-mm:parent ?uriParent .
    BIND(REPLACE( STR(?uriParent) , '(.*/)(\\w.*$)', '$2' ) AS ?idParent) .
}
?uri sdmx-mm:concept ?concept .
?concept skos:prefLabel ?titreLg1 ;
 		 skos:prefLabel ?titreLg2 ;
FILTER(lang(?titreLg1) = 'fr')
FILTER(lang(?titreLg2) = 'en')
BIND(REPLACE( STR(?uri) , '(.*/)(\\w.*$)', '$2' ) AS ?id) .
{
    {
        ?reporturi ?uri ?valeurSimple .
        FILTER ( datatype(?valeurSimple) = xsd:date )
        BIND('DATE' AS ?type) .
    }

<#-- RangeType.RICHTEXT  -->
UNION {
{   ?reporturi ?uri ?textLg1 .
    ?textLg1 rdf:type <http://purl.org/dc/dcmitype/Text> .
    ?textLg1 dcterms:language <${LG1_CL}>
    OPTIONAL{
        ?textLg1 rdf:value ?labelLg1 .
    }
    BIND(EXISTS{?textLg1 insee:additionalMaterial ?docLg1} AS ?hasDocLg1)
					}
    OPTIONAL {
	    ?reporturi ?uri ?textLg2 .
		?textLg2 rdf:type <http://purl.org/dc/dcmitype/Text>  .
		?textLg2 dcterms:language <${LG2_CL}>
		OPTIONAL{
            ?textLg2 rdf:value ?labelLg2 .
		}
		BIND(EXISTS{?textLg2 insee:additionalMaterial ?docLg2} AS ?hasDocLg2)
	}
    BIND('RICH_TEXT' AS ?type) .
    }

UNION {
    ?reporturi ?uri ?labelLg1 .
    FILTER(lang(?labelLg1) = 'fr')
    OPTIONAL{?reporturi ?uri ?labelLg2 .
        FILTER(lang(?labelLg2) = 'en') }
    BIND('TEXT' AS ?type) .
}
UNION {
    ?reporturi ?uri ?codeUri .
    ?codeUri skos:notation ?valeurSimple .
    ?codeUri skos:inScheme ?listUri .
    ?listUri skos:notation ?codeList .
    ?codeUri skos:prefLabel ?labelObjLg1 .
    FILTER (lang(?labelObjLg1) = 'fr') .
    ?codeUri skos:prefLabel ?labelObjLg2 .
    FILTER (lang(?labelObjLg2) = 'en') .
    BIND('CODE_LIST' AS ?type) .
    OPTIONAL {
	    ?mas sdmx-mm:metadataAttributeProperty ?uri .
		?mas sdmx-mm:maxOccurs ?maxOccurs
	}
}
UNION {
    ?reporturi ?uri ?organisationUri .
    ?organisationUri dcterms:identifier ?valeurSimple .
    OPTIONAL { ?organisationUri skos:prefLabel ?labelObjLg1 .
        FILTER (lang(?labelObjLg1) = 'fr')}
    OPTIONAL {?organisationUri skos:prefLabel ?labelObjLg2 .
        FILTER (lang(?labelObjLg2) = 'en') }
    BIND('ORGANIZATION' AS ?type) .
}
UNION {
    ?reporturi ?uri ?geoUri .
    GRAPH  ?g {
        ?geoUri skos:prefLabel|igeo:nom|rdfs:label ?labelObjLg1 .
        <#-- COG has no lang type  -->
        FILTER (lang(?labelObjLg1) = 'fr' || (langmatches(lang(?labelObjLg1), '') ))
    }
    BIND(REPLACE( STR(?geoUri) , '(.*/)(\\w.*$)', '$2' ) AS ?valeurSimple)
    BIND('GEOGRAPHY' AS ?type) .
}
}
}