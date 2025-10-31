SELECT ?uri ?id
WHERE {
   ?uriObject rdf:type skos:Collection .
   ?uriObject dcterms:identifier "${ID}" .
   OPTIONAL{
      ?uriObject skos:member ?uri .
      ?uri skos:notation ?id
    }
} ORDER BY DESC(?uri)