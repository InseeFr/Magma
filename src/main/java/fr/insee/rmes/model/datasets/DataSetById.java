package fr.insee.rmes.model.datasets;

import java.io.Serializable;
import java.util.HashMap;
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
        "operationStat",
        "dateCreation",
        "names",
        "dateModification",
        "titreLg1",
        "id",
        "titreLg2",
        "uri"
})
@Generated("jsonschema2pojo")
public class DataSetById implements Serializable
{

    @JsonProperty("operationStat")
    private String operationStat;
    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("names")
    private String names;
    @JsonProperty("dateModification")
    private String dateModification;
    @JsonProperty("titreLg1")
    private String titreLg1;
    @JsonProperty("id")
    private String id;
    @JsonProperty("titreLg2")
    private String titreLg2;
    @JsonProperty("uri")
    private String uri;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8064158425713380130L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataSetById() {
    }

    /**
     *
     * @param operationStat
     * @param dateCreation
     * @param names
     * @param dateModification
     * @param titreLg1
     * @param id
     * @param titreLg2
     * @param uri
     */
    public DataSetById(String operationStat, String dateCreation, String names, String dateModification, String titreLg1, String id, String titreLg2, String uri) {
        super();
        this.operationStat = operationStat;
        this.dateCreation = dateCreation;
        this.names = names;
        this.dateModification = dateModification;
        this.titreLg1 = titreLg1;
        this.id = id;
        this.titreLg2 = titreLg2;
        this.uri = uri;
    }

    @JsonProperty("operationStat")
    public String getOperationStat() {
        return operationStat;
    }

    @JsonProperty("operationStat")
    public void setOperationStat(String operationStat) {
        this.operationStat = operationStat;
    }

    public DataSetById withOperationStat(String operationStat) {
        this.operationStat = operationStat;
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

    public DataSetById withDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @JsonProperty("names")
    public String getNames() {
        return names;
    }

    @JsonProperty("names")
    public void setNames(String names) {
        this.names = names;
    }

    public DataSetById withNames(String names) {
        this.names = names;
        return this;
    }

    @JsonProperty("dateModification")
    public String getDateModification() {
        return dateModification;
    }

    @JsonProperty("dateModification")
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public DataSetById withDateModification(String dateModification) {
        this.dateModification = dateModification;
        return this;
    }

    @JsonProperty("titreLg1")
    public String getTitreLg1() {
        return titreLg1;
    }

    @JsonProperty("titreLg1")
    public void setTitreLg1(String titreLg1) {
        this.titreLg1 = titreLg1;
    }

    public DataSetById withTitreLg1(String titreLg1) {
        this.titreLg1 = titreLg1;
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

    public DataSetById withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("titreLg2")
    public String getTitreLg2() {
        return titreLg2;
    }

    @JsonProperty("titreLg2")
    public void setTitreLg2(String titreLg2) {
        this.titreLg2 = titreLg2;
    }

    public DataSetById withTitreLg2(String titreLg2) {
        this.titreLg2 = titreLg2;
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

    public DataSetById withUri(String uri) {
        this.uri = uri;
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

    public DataSetById withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
