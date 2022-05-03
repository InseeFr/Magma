
package fr.insee.rmes.dto.structure;

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
    "dateMiseAJour",
    "notation",
    "dateCr\u00e9ation",
    "statutValidation",
    "mesures",
    "id",
    "label",
    "uri",
    "version",
    "attributs",
    "dimensions"
})
@Generated("jsonschema2pojo")
public class StructureByIdDTO implements Serializable
{

    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("dateCr\u00e9ation")
    private String dateCrAtion;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("mesures")
    @Valid
    private List<Mesure> mesures = null;
    @JsonProperty("id")
    private String id;
    @JsonProperty("label")
    @Valid
    private List<Label__1> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("version")
    private String version;
    @JsonProperty("attributs")
    @Valid
    private List<Attribut> attributs = null;
    @JsonProperty("dimensions")
    @Valid
    private List<Dimension> dimensions = null;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 59690530118419605L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public StructureByIdDTO() {
    }

    /**
     * 
     * @param dateMiseAJour
     * @param notation
     * @param statutValidation
     * @param mesures
     * @param id
     * @param label
     * @param uri
     * @param version
     * @param attributs
     * @param dateCrAtion
     * @param dimensions
     */
    public StructureByIdDTO(String dateMiseAJour, String notation, String dateCrAtion, String statutValidation, List<Mesure> mesures, String id, List<Label__1> label, String uri, String version, List<Attribut> attributs, List<Dimension> dimensions) {
        super();
        this.dateMiseAJour = dateMiseAJour;
        this.notation = notation;
        this.dateCrAtion = dateCrAtion;
        this.statutValidation = statutValidation;
        this.mesures = mesures;
        this.id = id;
        this.label = label;
        this.uri = uri;
        this.version = version;
        this.attributs = attributs;
        this.dimensions = dimensions;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public StructureByIdDTO withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("notation")
    public String getNotation() {
        return notation;
    }

    @JsonProperty("notation")
    public void setNotation(String notation) {
        this.notation = notation;
    }

    public StructureByIdDTO withNotation(String notation) {
        this.notation = notation;
        return this;
    }

    @JsonProperty("dateCr\u00e9ation")
    public String getDateCrAtion() {
        return dateCrAtion;
    }

    @JsonProperty("dateCr\u00e9ation")
    public void setDateCrAtion(String dateCrAtion) {
        this.dateCrAtion = dateCrAtion;
    }

    public StructureByIdDTO withDateCrAtion(String dateCrAtion) {
        this.dateCrAtion = dateCrAtion;
        return this;
    }

    @JsonProperty("statutValidation")
    public String getStatutValidation() {
        return statutValidation;
    }

    @JsonProperty("statutValidation")
    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }

    public StructureByIdDTO withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
        return this;
    }

    @JsonProperty("mesures")
    public List<Mesure> getMesures() {
        return mesures;
    }

    @JsonProperty("mesures")
    public void setMesures(List<Mesure> mesures) {
        this.mesures = mesures;
    }

    public StructureByIdDTO withMesures(List<Mesure> mesures) {
        this.mesures = mesures;
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

    public StructureByIdDTO withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("label")
    public List<Label__1> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label__1> label) {
        this.label = label;
    }

    public StructureByIdDTO withLabel(List<Label__1> label) {
        this.label = label;
        return this;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    public StructureByIdDTO withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    public StructureByIdDTO withVersion(String version) {
        this.version = version;
        return this;
    }

    @JsonProperty("attributs")
    public List<Attribut> getAttributs() {
        return attributs;
    }

    @JsonProperty("attributs")
    public void setAttributs(List<Attribut> attributs) {
        this.attributs = attributs;
    }

    public StructureByIdDTO withAttributs(List<Attribut> attributs) {
        this.attributs = attributs;
        return this;
    }

    @JsonProperty("dimensions")
    public List<Dimension> getDimensions() {
        return dimensions;
    }

    @JsonProperty("dimensions")
    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }

    public StructureByIdDTO withDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
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

    public StructureByIdDTO withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
