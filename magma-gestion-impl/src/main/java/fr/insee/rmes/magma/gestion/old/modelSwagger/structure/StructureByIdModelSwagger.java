
package fr.insee.rmes.magma.gestion.old.modelSwagger.structure;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dateMiseAJour",
    "dateCreation",
    "notation",
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
public class StructureByIdModelSwagger implements Serializable
{

    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("dateCreation")
    private String dateCreation;
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
    public StructureByIdModelSwagger() {
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
     * @param dateCreation
     * @param dimensions
     */
    public StructureByIdModelSwagger(String dateMiseAJour, String notation, String dateCreation, String statutValidation, List<Mesure> mesures, String id, List<Label__1> label, String uri, String version, List<Attribut> attributs, List<Dimension> dimensions) {
        super();
        this.dateMiseAJour = dateMiseAJour;
        this.notation = notation;
        this.dateCreation = dateCreation;
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

    public StructureByIdModelSwagger withDateMiseAJour(String dateMiseAJour) {
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

    public StructureByIdModelSwagger withNotation(String notation) {
        this.notation = notation;
        return this;
    }

    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCrAtion) {
        this.dateCreation = dateCrAtion;
    }

    public StructureByIdModelSwagger withDateCreation(String dateCrAtion) {
        this.dateCreation = dateCrAtion;
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

    public StructureByIdModelSwagger withStatutValidation(String statutValidation) {
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

    public StructureByIdModelSwagger withMesures(List<Mesure> mesures) {
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

    public StructureByIdModelSwagger withId(String id) {
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

    public StructureByIdModelSwagger withLabel(List<Label__1> label) {
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

    public StructureByIdModelSwagger withUri(String uri) {
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

    public StructureByIdModelSwagger withVersion(String version) {
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

    public StructureByIdModelSwagger withAttributs(List<Attribut> attributs) {
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

    public StructureByIdModelSwagger withDimensions(List<Dimension> dimensions) {
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

    public StructureByIdModelSwagger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
