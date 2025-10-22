
package fr.insee.rmes.magma.gestion.old.modelSwagger.component;

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
    "concept",
    "statutValidation",
    "label",
    "type",
    "uri",
    "representation",
    "version",
    "notation",
    "id",
    "listeCode"
})
@Generated("jsonschema2pojo")
public class ComponentByIdModelSwagger implements Serializable
{

    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("concept")
    @Valid
    private Concept concept;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("label")
    @Valid
    private List<Label> label = null;
    @JsonProperty("type")
    private String type;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("representation")
    private String representation;
    @JsonProperty("version")
    private String version;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("id")
    private String id;
    @JsonProperty("listeCode")
    @Valid
    private ListeCode listeCode;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5695294506357720319L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ComponentByIdModelSwagger() {
    }

    /**
     * 
     * @param dateMiseAJour
     * @param dateCreation
     * @param notation
     * @param concept
     * @param statutValidation
     * @param label
     * @param id
     * @param type
     * @param listeCode
     * @param uri
     * @param representation
     * @param version
     */
    public ComponentByIdModelSwagger(String dateMiseAJour, String dateCreation, Concept concept, String statutValidation, List<Label> label, String type, String uri, String representation, String version, String notation, String id, ListeCode listeCode) {
        super();
        this.dateMiseAJour = dateMiseAJour;
        this.dateCreation = dateCreation;
        this.concept = concept;
        this.statutValidation = statutValidation;
        this.label = label;
        this.type = type;
        this.uri = uri;
        this.representation = representation;
        this.version = version;
        this.notation = notation;
        this.id = id;
        this.listeCode = listeCode;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public ComponentByIdModelSwagger withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ComponentByIdModelSwagger withDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @JsonProperty("concept")
    public Concept getConcept() {
        return concept;
    }

    @JsonProperty("concept")
    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public ComponentByIdModelSwagger withConcept(Concept concept) {
        this.concept = concept;
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

    public ComponentByIdModelSwagger withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
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

    public ComponentByIdModelSwagger withLabel(List<Label> label) {
        this.label = label;
        return this;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public ComponentByIdModelSwagger withType(String type) {
        this.type = type;
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

    public ComponentByIdModelSwagger withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("representation")
    public String getRepresentation() {
        return representation;
    }

    @JsonProperty("representation")
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public ComponentByIdModelSwagger withRepresentation(String representation) {
        this.representation = representation;
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

    public ComponentByIdModelSwagger withVersion(String version) {
        this.version = version;
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

    public ComponentByIdModelSwagger withNotation(String notation) {
        this.notation = notation;
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

    public ComponentByIdModelSwagger withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("listeCode")
    public ListeCode getListeCode() {
        return listeCode;
    }

    @JsonProperty("listeCode")
    public void setListeCode(ListeCode listeCode) {
        this.listeCode = listeCode;
    }

    public ComponentByIdModelSwagger withListeCode(ListeCode listeCode) {
        this.listeCode = listeCode;
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

    public ComponentByIdModelSwagger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
