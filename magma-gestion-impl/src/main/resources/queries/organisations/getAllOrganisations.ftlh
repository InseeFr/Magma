select distinct ?Id ?prefLabelLg1 ?prefLabelLg2
from <http://rdf.insee.fr/graphes/organisations>
from <http://rdf.insee.fr/graphes/organisations/insee>
where {
?uri a <http://www.w3.org/ns/org#Organization> .
?uri dcterms:identifier ?Id .
OPTIONAL{ ?uri skos:prefLabel ?prefLabelLg1 .
FILTER(lang(?prefLabelLg1) = '${LG1}')
}
OPTIONAL{ ?uri skos:prefLabel ?prefLabelLg2 .
FILTER(lang(?prefLabelLg2) = '${LG2}')
}
} 
