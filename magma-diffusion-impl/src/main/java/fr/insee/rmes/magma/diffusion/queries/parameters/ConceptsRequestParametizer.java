package fr.insee.rmes.magma.diffusion.queries.parameters;


public record ConceptsRequestParametizer(String id, String label, String collection, String uriConcept) implements ParametersForQuery<ConceptsRequestParametizer>{

    public static ConceptsRequestParametizer ofId(String id) {
        return new ConceptsRequestParametizer(id, "none", "none", "none");
    }

    public static ConceptsRequestParametizer ofUri(String uriConcept){
        return new ConceptsRequestParametizer("none", "none", "none", uriConcept);

    }

    public ConceptsRequestParametizer(String label, String collection){
        this("none", label, collection, "none");
    }

}