package fr.insee.rmes.metadata.queries.parameters;

public record ConceptRequestParametizer(String id,
                                        Class<?> typeOrigine) implements ParametersForQuery<ConceptRequestParametizer> {

//    for /concepts/definition/{id}
//    public ConceptRequestParametizer(String id, Class<?> typeOrigine){
//        this(id, typeOrigine);
//    }


}
