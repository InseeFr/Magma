SELECT ?agence ?id
FROM <${CONCEPTS_GRAPH}>
WHERE {
?concept skos:inScheme ?conceptScheme .

FILTER(regex(str(?conceptScheme),'/concepts/definitions/scheme'))

?concept skos:notation "${ID}" .
?concept skos:closeMatch ?conceptSDMX .
FILTER(strStarts(str(?conceptSDMX),'urn:sdmx:org.sdmx.infomodel.conceptscheme.Concept'))
BIND(strAfter(str(?conceptSDMX),'=') AS ?segmentAParser) .
BIND(strBefore(?segmentAParser,':') AS ?agence) .
BIND(strBefore(strAfter(?segmentAParser,':'),'(') AS ?scheme) .
BIND(strBefore(strAfter(?segmentAParser,'('),')') AS ?version) .
BIND(strAfter(?segmentAParser,').') AS ?id) .
}