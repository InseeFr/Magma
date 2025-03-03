SELECT ?uri_concept ?id_concept
WHERE {
   ?uri rdf:type skos:Collection .
   ?uri dcterms:identifier "${ID}" .
   OPTIONAL{
      ?uri skos:member ?uri_concept .
      ?uri_concept skos:notation ?id_concept
    }
} ORDER BY DESC(?uri_concept)