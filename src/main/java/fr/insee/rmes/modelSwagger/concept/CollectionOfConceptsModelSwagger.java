package fr.insee.rmes.modelSwagger.concept;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.model.concept.ConceptDefCourte;
import fr.insee.rmes.modelSwagger.dataset.LangContent;
import lombok.Getter;
import lombok.Setter;
import jakarta.annotation.Generated;
import java.io.Serializable;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({
        "id",
        "uri",
        "dateMiseAJour",
        "intitule",
        "description",
})

@Generated("jsonschema2pojo")
@Setter
@Getter
public class CollectionOfConceptsModelSwagger implements Serializable {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("id")
    private String id;

    @JsonProperty("intitule")
    private List<LangContent> label;

    @JsonProperty("dateMiseAJour")
    private String dateOfUpdate;

    @JsonProperty("description")
    private List<ConceptDefCourte> description;

    public CollectionOfConceptsModelSwagger(String uri,String id, String dateOfUpdate){
        this.uri=uri;
        this.id=id;
        this.dateOfUpdate=dateOfUpdate;

    }

    public CollectionOfConceptsModelSwagger(){}
}