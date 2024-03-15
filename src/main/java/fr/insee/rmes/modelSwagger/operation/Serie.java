
package fr.insee.rmes.modelSwagger.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "label",
    "uri"
})
@Generated("jsonschema2pojo")
public class Serie implements Serializable
{

    @JsonProperty("id")
    private String seriesid;
    @JsonProperty("label")
    @Valid
    private List<Label> labelSerie = null;
    @JsonProperty("uri")
    private String series;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -4606257655963558026L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Serie() {
    }

    /**
     * 
     * @param labelSerie
     * @param series
     * @param seriesid
     */
    public Serie(String seriesid, List<Label> labelSerie, String series) {
        super();
        this.seriesid = seriesid;
        this.labelSerie = labelSerie;
        this.series = series;
    }

    public List<Label> getLabelSerie() {
        return labelSerie;
    }
}
