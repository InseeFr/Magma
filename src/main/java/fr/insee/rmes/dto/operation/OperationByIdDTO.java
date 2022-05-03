
package fr.insee.rmes.dto.operation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "serie",
    "id",
    "label",
    "uri"
})
@Generated("jsonschema2pojo")
public class OperationByIdDTO implements Serializable
{

    @JsonProperty("serie")
    @Valid
    private Serie serie;
    @JsonProperty("id")
    private String operationId;
    @JsonProperty("label")
    @Valid
    private List<Label> labelOperation = null;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -1632969180648660692L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OperationByIdDTO() {
    }

    /**
     * 
     * @param serie
     * @param labelOperation
     * @param operationId
     * @param uri
     */
    public OperationByIdDTO(Serie serie, String operationId, List<Label> labelOperation, String uri) {
        super();
        this.serie = serie;
        this.operationId = operationId;
        this.labelOperation = labelOperation;
        this.uri = uri;
    }

}
