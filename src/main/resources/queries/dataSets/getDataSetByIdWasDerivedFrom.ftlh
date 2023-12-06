SELECT DISTINCT   ?wasDerivedFrom

FROM <${DATASETS_GRAPH}>

WHERE {

        BIND(STRAFTER(STR(<${URI}>), '/datasets/') AS ?wasDerivedFrom)

}