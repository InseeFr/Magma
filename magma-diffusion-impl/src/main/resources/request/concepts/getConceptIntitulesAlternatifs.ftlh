SELECT   (STR(?intitulesAlternatifs) AS ?contenu)
         (LANG(?intitulesAlternatifs) AS ?langue)
FROM <http://rdf.insee.fr/graphes/concepts/definitions>

WHERE {


        <${uriConcept}> skos:altLabel ?intitulesAlternatifs .



}