select distinct ?Id ?LabelLg1 ?LabelLg2
from <http://rdf.insee.fr/graphes/organisations>
from <http://rdf.insee.fr/graphes/organisations/insee>
where {
?uri a <http://www.w3.org/ns/org#Organization> .
?uri dcterms:identifier ?Id .
OPTIONAL{ ?uri skos:prefLabel ?LabelLg1 .
FILTER(lang(?LabelLg1) = 'fr')
}
OPTIONAL{ ?uri skos:prefLabel ?LabelLg2 .
FILTER(lang(?LabelLg2) = 'en')
}
} 
