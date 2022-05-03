
package fr.insee.rmes.dto.operation;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "altLabel",
    "label",
    "type",
    "series",
    "id",
    "frequence",
    "nbOperation",
    "famille"
})
@Generated("jsonschema2pojo")
public class SerieByIdDTO extends SeriesListDTO implements Serializable
{

    @JsonProperty("altLabel")
    @Valid
    private List<AltLabel> altLabel = null;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("type")
    @Valid
    private Type type;
    @JsonProperty("series")
    private String series;
    @JsonProperty("id")
    private String id;
    @JsonProperty("frequence")
    @Valid
    private Frequence frequence;
    @JsonProperty("nbOperation")
    private String nbOperation;
    @JsonProperty("famille")
    @Valid
    private Famille famille;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 457428553976945880L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SerieByIdDTO() {
    }

    /**
     * 
     * @param series
     * @param altLabel
     * @param label
     * @param id
     * @param frequence
     * @param type
     * @param nbOperation
     * @param famille
     */
    public SerieByIdDTO(List<AltLabel> altLabel, List<Label> label, Type type, String series, String id, Frequence frequence, String nbOperation, Famille famille) {
        super();
        this.altLabel = altLabel;
        this.label = label;
        this.type = type;
        this.series = series;
        this.id = id;
        this.frequence = frequence;
        this.nbOperation = nbOperation;
        this.famille = famille;
    }

    @JsonProperty("altLabel")
    public List<AltLabel> getAltLabel() {
        return altLabel;
    }

    @JsonProperty("altLabel")
    public void setAltLabel(List<AltLabel> altLabel) {
        this.altLabel = altLabel;
    }

    public SerieByIdDTO withAltLabel(List<AltLabel> altLabel) {
        this.altLabel = altLabel;
        return this;
    }

    @JsonProperty("label")
    public List<Label> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label> label) {
        this.label = label;
    }

    public SerieByIdDTO withLabel(List<Label> label) {
        this.label = label;
        return this;
    }

    @JsonProperty("type")
    public Type getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Type type) {
        this.type = type;
    }

    public SerieByIdDTO withType(Type type) {
        this.type = type;
        return this;
    }

    @JsonProperty("series")
    public String getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(String series) {
        this.series = series;
    }

    public SerieByIdDTO withSeries(String series) {
        this.series = series;
        return this;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public SerieByIdDTO withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("frequence")
    public Frequence getFrequence() {
        return frequence;
    }

    @JsonProperty("frequence")
    public void setFrequence(Frequence frequence) {
        this.frequence = frequence;
    }

    public SerieByIdDTO withFrequence(Frequence frequence) {
        this.frequence = frequence;
        return this;
    }

    @JsonProperty("nbOperation")
    public String getNbOperation() {
        return nbOperation;
    }

    @JsonProperty("nbOperation")
    public void setNbOperation(String nbOperation) {
        this.nbOperation = nbOperation;
    }

    public SerieByIdDTO withNbOperation(String nbOperation) {
        this.nbOperation = nbOperation;
        return this;
    }

    @JsonProperty("famille")
    public Famille getFamille() {
        return famille;
    }

    @JsonProperty("famille")
    public void setFamille(Famille famille) {
        this.famille = famille;
    }

    public SerieByIdDTO withFamille(Famille famille) {
        this.famille = famille;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public SerieByIdDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
