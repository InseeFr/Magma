
package fr.insee.rmes.model.datasets;

import java.io.Serializable;
import java.util.HashMap;
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
        "dateCreation",
        "names",
        "dateMiseAJour",
        "operationStat",
        "titreLg1",
        "id",
        "titreLg2",
        "uri"
})
@Generated("jsonschema2pojo")
public class DataSet implements Serializable
{

    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("names")
    private String names;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("antecedent")
    private String operationStat;
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
    private final static long serialVersionUID = 107365304509061184L;

    /**
     * No args constructor for use in serialization
     *
     */
    public DataSet() {
    }

    /**
     *
     * @param dateCreation
     * @param names
     * @param dateMiseAJour
     * @param serie
     * @param titreLg1
     * @param id
     * @param titreLg2
     * @param uri
     */
    public DataSet(String dateCreation, String names, String dateMiseAJour, String serie, String titreLg1, String id, String titreLg2, String uri) {
        super();
        this.dateCreation = dateCreation;
        this.names = names;
        this.dateMiseAJour = dateMiseAJour;
        this.operationStat = serie;
        this.titreLg1 = titreLg1;
        this.id = id;
        this.titreLg2 = titreLg2;
        this.uri = uri;
    }

    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public DataSet withDateCreation(String dateCreation) {
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

    public DataSet withNames(String names) {
        this.names = names;
        return this;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateModification")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public DataSet withDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("operationStat")
    public String getSerie() {
        return operationStat;
    }

    @JsonProperty("operationStat")
    public void setSerie(String serie) {
        this.operationStat = serie;
    }

    public DataSet withSerie(String serie) {
        this.operationStat = serie;
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

    public DataSet withTitreLg1(String titreLg1) {
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

    public DataSet withId(String id) {
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

    public DataSet withTitreLg2(String titreLg2) {
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

    public DataSet withUri(String uri) {
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

    public DataSet withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}