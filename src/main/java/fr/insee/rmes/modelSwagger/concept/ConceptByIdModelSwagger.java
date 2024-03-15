
package fr.insee.rmes.modelSwagger.concept;

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
import fr.insee.rmes.model.concept.ConceptDefCourte;
import fr.insee.rmes.model.concept.ConceptSDMX;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dateCreation",
        "dateMiseAJour",
        "statutValidation",
        "id",
        "label",
        "dateFinValidite",
        "uri",
        "version",
        "name"
})
@Generated("jsonschema2pojo")
public class ConceptByIdModelSwagger implements Serializable
{

    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("label")
    private List<LabelConcept> labelConcept;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("id")
    private String id;
    @JsonProperty("dateFinValidite")
    private String dateFinValidite;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("version")
    private String version;
    @JsonProperty("conceptsSDMX")
    private ConceptSDMX[] conceptsSDMX;

    @JsonProperty("definitionCourte")
    private List<ConceptDefCourte> definitionCourte;

    @JsonProperty("name")
    private String name;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4392335549697327701L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ConceptByIdModelSwagger() {
    }

    public ConceptByIdModelSwagger(String dateCreation, String dateMiseAJour, String statutValidation, String id, List<LabelConcept> labelConcept, String dateFinValidite, String uri, String version, List<ConceptDefCourte> definitionCourte) {
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.definitionCourte=definitionCourte;
        this.statutValidation = statutValidation;
        this.id = id;
        this.labelConcept = labelConcept;
        this.dateFinValidite = dateFinValidite;
        this.uri = uri;
        this.version = version;
        this.definitionCourte = definitionCourte;
    }
    public ConceptByIdModelSwagger(String dateCreation, String dateMiseAJour, String statutValidation, String id, String dateFinValidite, String uri, String version) {
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.statutValidation = statutValidation;
        this.id = id;
        this.dateFinValidite = dateFinValidite;
        this.uri = uri;
        this.version = version;
    }

    public ConceptByIdModelSwagger(String dateCreation, String dateMiseAJour, String statutValidation, String id, List<LabelConcept> labelConcept, String dateFinValidite, String uri, String version, ConceptSDMX[] conceptsSDMXArray, List<ConceptDefCourte> definitionCourte) {
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.statutValidation = statutValidation;
        this.id = id;
        this.labelConcept = labelConcept;
        this.dateFinValidite = dateFinValidite;
        this.uri = uri;
        this.version = version;
        this.conceptsSDMX = conceptsSDMXArray;
        this.definitionCourte = definitionCourte;
    }

    public ConceptByIdModelSwagger(String dateCreation, String dateMiseAJour, String statutValidation, String id, String dateFinValidite, String uri, String version, ConceptSDMX[] conceptsSDMXArray) {
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.statutValidation = statutValidation;
        this.id = id;
        this.dateFinValidite = dateFinValidite;
        this.uri = uri;
        this.version = version;
        this.conceptsSDMX = conceptsSDMXArray;
    }

    /**
     *
     * @param dateCreation
     * @param dateMiseAJour
     * @param prefLabelLg1
     * @param prefLabelLg2
     * @param statutValidation
     * @param id
     * @param dateFinValidite
     * @param uri
     * @param version
     * @param definitionCourte
     * @param name
     */


    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ConceptByIdModelSwagger withDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public ConceptByIdModelSwagger withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("label")
    public List<LabelConcept> getLabelConcept() {
        return labelConcept;
    }

    @JsonProperty("label")
    public void setLabelConcept(List<LabelConcept> labelConcept) {
        this.labelConcept = labelConcept;
    }

    @JsonProperty("statutValidation")
    public String getStatutValidation() {
        return statutValidation;
    }

    @JsonProperty("statutValidation")
    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }

    public ConceptByIdModelSwagger withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
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

    public ConceptByIdModelSwagger withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("dateFinValidite")
    public String getDateFinValidite() {
        return dateFinValidite;
    }

    @JsonProperty("dateFinValidite")
    public void setDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public ConceptByIdModelSwagger withDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
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
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public ConceptByIdModelSwagger withUri(String uri) {
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

    @JsonProperty("conceptsSDMX")
    public ConceptSDMX[] getConceptsSDMX() {
        return conceptsSDMX;
    }
    @JsonProperty("conceptsSDMX")
    public void setConceptsSDMX(ConceptSDMX[] conceptsSDMX) {
        this.conceptsSDMX = conceptsSDMX;
    }
    @JsonProperty("definitionCourte")
    public List<ConceptDefCourte> getDefinitionCourte() {
        return definitionCourte;
    }
    @JsonProperty("definitionCourte")
    public void setDefinitionCourte(List<ConceptDefCourte> definitionCourte) {
        this.definitionCourte = definitionCourte;
    }

    public ConceptByIdModelSwagger withVersion(String version) {
        this.version = version;
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

    public ConceptByIdModelSwagger withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
