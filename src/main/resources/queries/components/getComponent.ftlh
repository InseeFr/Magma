SELECT ?id (?uriComposant as ?uri) ?type ?idMetier
    ?dateMiseAJour ?statutValidation ?prefLabelLg1 ?prefLabelLg2 ?altLabelLg1 ?altLabelLg2
    ?uriConcept ?idConcept ?representation
    ?uriListeCode ?idListeCode
    ?uriParentListCode ?idParentListCode
    ?version ?uriComponentParentId ?uriComponentParentNotation ?dateCreation
    ?minLength ?maxLength ?minInclusive ?maxInclusive ?pattern
FROM <${STRUCTURES_COMPONENTS_GRAPH}>
FROM <${CODELIST_GRAPH}>
WHERE {
    ?uriComposant dcterms:identifier "${ID}" .
    BIND("${ID}" AS ?id) .

    {
        ?uriComposant rdf:type qb:DimensionProperty .
        BIND("Dimension" as ?type) .
    }
    UNION  {
        ?uriComposant rdf:type qb:MeasureProperty .
        BIND("Mesure" as ?type) .
    }
    UNION  {
       ?uriComposant rdf:type qb:AttributeProperty .
        BIND("Attribut" as ?type) .
    }
    ?uriComposant skos:notation ?idMetier .
    ?uriComposant insee:validationState ?statutValidation .

    ?uriComposant rdfs:label ?prefLabelLg1 .
    FILTER (lang(?prefLabelLg1) = '${LG1}') .

    ?uriComposant rdfs:label ?prefLabelLg2 .
    FILTER (lang(?prefLabelLg2) = '${LG2}') .

    ?uriComposant skos:altLabel ?altLabelLg1 .
    FILTER (lang(?altLabelLg1) = '${LG1}') .

    ?uriComposant skos:altLabel ?altLabelLg2 .
    FILTER (lang(?altLabelLg2) = '${LG2}') .

    OPTIONAL {
        ?uriComposant dcterms:modified ?dateMiseAJour .
    }
    OPTIONAL {
        ?uriComposant skos:broader ?uriComponentParent .
        ?uriComponentParent dcterms:identifier ?uriComponentParentId .
        ?uriComponentParent skos:notation ?uriComponentParentNotation .
    }

    OPTIONAL {
        ?uriComposant dcterms:created ?dateCreation .
    }
    OPTIONAL {
        ?uriComposant qb:concept ?uriConcept .
        BIND(STRAFTER(STR(?uriConcept), "http://bauhaus/concepts/definition/") AS ?idConcept)
    }
    OPTIONAL {
        ?uriComposant rdfs:range ?representation .
    }
    OPTIONAL {
        ?uriComposant qb:codeList ?uriListeCode .
        ?uriListeCode skos:notation ?idListeCode .
    }

    OPTIONAL {
        ?uriComposant pav:version ?version .
    }

    OPTIONAL {
        ?uriComposant xsd:minLength ?minLength .
    }
    OPTIONAL {
        ?uriComposant xsd:maxLength ?maxLength .
    }
    OPTIONAL {
        ?uriComposant xsd:minInclusive ?minInclusive .
    }
    OPTIONAL {
        ?uriComposant xsd:maxInclusive ?maxInclusive .
    }
    OPTIONAL {
        ?uriComposant xsd:pattern ?pattern .
    }
    OPTIONAL {
    ?uriComposant skos:altLabel ?nom .
    }
}