package fr.insee.rmes.magma.diffusion.queries.parameters;


public record ConceptsRequestParametizer(String id, String label, String uriConcept) implements ParametersForQuery<ConceptsRequestParametizer>{

    public ConceptsRequestParametizer(String id,
                                  String label){
        this(id, label, "none");
    }

    public ConceptsRequestParametizer(String uriConcept){
        this("none", "none", uriConcept);
    }

}