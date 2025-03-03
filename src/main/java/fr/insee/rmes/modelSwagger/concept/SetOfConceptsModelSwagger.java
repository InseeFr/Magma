package fr.insee.rmes.modelSwagger.concept;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({
        "id",
        "uri",
})

@Generated("jsonschema2pojo")
@Setter
@Getter

public class SetOfConceptsModelSwagger implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("uri")
    private String uri;

}