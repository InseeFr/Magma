package fr.insee.rmes.magma.diffusion.queries.parameters;


public record ConceptsRequestParametizer(String id, String label, String uriConcept) implements ParametersForQueryDiffusion<ConceptsRequestParametizer> {

    public static ConceptsRequestParametizer ofId(String id) {
        return new ConceptsRequestParametizer(id, "none", "none", "none");
    }

    public static ConceptsRequestParametizer ofUri(String uriConcept){
        return new ConceptsRequestParametizer("none", "none", "none", uriConcept);

    }

    public ConceptsRequestParametizer(String libelle, String collection){
        this("none", libelle, collection, "none");
    }

}