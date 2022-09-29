
package fr.insee.rmes.modelSwagger.operation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "altLabel",
    "label",
    "uri",
    "serie",
    "id"
})
@Generated("jsonschema2pojo")
public class OperationBySerieIdModelSwagger  implements Serializable{

    @JsonProperty("altLabel")
    @Valid
    private List<AltLabel> altLabel = null;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("serie")
    @Valid
    private Serie serie;
    @JsonProperty("id")
    private String id;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5641722896504448023L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OperationBySerieIdModelSwagger() {
    }

    /**
     * 
     * @param serie
     * @param altLabel
     * @param label
     * @param id
     * @param uri
     */
    public OperationBySerieIdModelSwagger(List<AltLabel> altLabel, List<Label> label, String uri, Serie serie, String id) {
        super();
        this.altLabel = altLabel;
        this.label = label;
        this.uri = uri;
        this.serie = serie;
        this.id = id;
    }

    List<OperationBySerieIdModelSwagger> operationBySerieIdModelSwaggerList;

}
