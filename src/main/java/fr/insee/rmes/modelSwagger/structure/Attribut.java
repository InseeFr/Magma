
package fr.insee.rmes.modelSwagger.structure;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ordre",
    "notation",
    "listCode",
    "attachement",
    "obligatoire",
    "label",
    "uri",
    "representation"
})
@Generated("jsonschema2pojo")
public class Attribut implements Serializable
{

    @JsonProperty("ordre")
    private String ordre;
    @JsonProperty("notation")
    private String notation;
    @JsonProperty("listCode")
    @Valid
    private ListCode listCode;
    @JsonProperty("attachement")
    private String attachement;
    @JsonProperty("obligatoire")
    private String obligatoire;
    @JsonProperty("label")
    @Valid
    private List<Label__2> label = null;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("representation")
    private String representation;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -332716016603935664L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Attribut() {
    }

    /**
     * 
     * @param ordre
     * @param notation
     * @param listCode
     * @param attachement
     * @param obligatoire
     * @param label
     * @param uri
     * @param representation
     */
    public Attribut(String ordre, String notation, ListCode listCode, String attachement, String obligatoire, List<Label__2> label, String uri, String representation) {
        super();
        this.ordre = ordre;
        this.notation = notation;
        this.listCode = listCode;
        this.attachement = attachement;
        this.obligatoire = obligatoire;
        this.label = label;
        this.uri = uri;
        this.representation = representation;
    }

    @JsonProperty("ordre")
    public String getOrdre() {
        return ordre;
    }

    @JsonProperty("ordre")
    public void setOrdre(String ordre) {
        this.ordre = ordre;
    }

    public Attribut withOrdre(String ordre) {
        this.ordre = ordre;
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

    public Attribut withNotation(String notation) {
        this.notation = notation;
        return this;
    }

    @JsonProperty("listCode")
    public ListCode getListCode() {
        return listCode;
    }

    @JsonProperty("listCode")
    public void setListCode(ListCode listCode) {
        this.listCode = listCode;
    }

    public Attribut withListCode(ListCode listCode) {
        this.listCode = listCode;
        return this;
    }

    @JsonProperty("attachement")
    public String getAttachement() {
        return attachement;
    }

    @JsonProperty("attachement")
    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

    public Attribut withAttachement(String attachement) {
        this.attachement = attachement;
        return this;
    }

    @JsonProperty("obligatoire")
    public String getObligatoire() {
        return obligatoire;
    }

    @JsonProperty("obligatoire")
    public void setObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
    }

    public Attribut withObligatoire(String obligatoire) {
        this.obligatoire = obligatoire;
        return this;
    }

    @JsonProperty("label")
    public List<Label__2> getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(List<Label__2> label) {
        this.label = label;
    }

    public Attribut withLabel(List<Label__2> label) {
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

    public Attribut withUri(String uri) {
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

    public Attribut withRepresentation(String representation) {
        this.representation = representation;
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

    public Attribut withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
