SELECT ?notation
WHERE {
  GRAPH <${CODES_GRAPH}> {
    <${codeProcessStep}> a skos:Concept ;
                                              skos:notation ?notation .
}
}