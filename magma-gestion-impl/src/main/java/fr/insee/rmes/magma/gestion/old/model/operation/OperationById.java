
package fr.insee.rmes.magma.gestion.old.model.operation;

import com.fasterxml.jackson.annotation.*;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "series",
    "operationId",
    "operationLabelLg1",
    "operationLabelLg2",
    "seriesLabelLg1",
    "uri",
    "seriesId",
    "seriesLabelLg2",
    "proprietaire",
    "millesime"
})
@Generated("jsonschema2pojo")
public class OperationById implements Serializable
{







    @JsonProperty("series")
    private String series;
    @JsonProperty("operationId")
    private String id;
    @JsonProperty("operationLabelLg1")
    private String operationLabelLg1;
    @JsonProperty("operationLabelLg2")
    private String operationLabelLg2;
    @JsonProperty("seriesLabelLg1")
    private String seriesLabelLg1;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("seriesId")
    private String seriesId;
    @JsonProperty("seriesLabelLg2")
    private String seriesLabelLg2;

    @JsonProperty("proprietaire")
    private String proprietaire;
    @JsonProperty("millesime")
    private String millesime;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4460719270540817291L;



    /**
     * No args constructor for use in serialization
     * 
     */
    public OperationById() {
    }

    /**
     * 
     * @param series
     * @param id
     * @param operationLabelLg1
     * @param operationLabelLg2
     * @param seriesLabelLg1
     * @param uri
     * @param seriesId
     * @param seriesLabelLg2
     * @param proprietaire
     */
    public OperationById(String series, String id, String operationLabelLg1, String operationLabelLg2, String seriesLabelLg1, String uri, String seriesId, String seriesLabelLg2, String proprietaire   ) {
        super();
        this.series = series;
        this.id = id;
        this.operationLabelLg1 = operationLabelLg1;
        this.operationLabelLg2 = operationLabelLg2;
        this.seriesLabelLg1 = seriesLabelLg1;
        this.uri = uri;
        this.seriesId = seriesId;
        this.seriesLabelLg2 = seriesLabelLg2;
        this.proprietaire = proprietaire;
    }


    @JsonProperty("series")
    public String getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(String series) {
        this.series = series;
    }

    public OperationById withSeries(String series) {
        this.series = series;
        return this;
    }

    @JsonProperty("operationId")
    public String getId() {
        return id;
    }

    @JsonProperty("operationId")
    public void setId(String id) {
        this.id = id;
    }

    public OperationById withOperationId(String operationId) {
        this.id = operationId;
        return this;
    }

    @JsonProperty("operationLabelLg1")
    public String getOperationLabelLg1() {
        return operationLabelLg1;
    }

    @JsonProperty("operationLabelLg1")
    public void setOperationLabelLg1(String operationLabelLg1) {
        this.operationLabelLg1 = operationLabelLg1;
    }

    public OperationById withOperationLabelLg1(String operationLabelLg1) {
        this.operationLabelLg1 = operationLabelLg1;
        return this;
    }

    @JsonProperty("operationLabelLg2")
    public String getOperationLabelLg2() {
        return operationLabelLg2;
    }

    @JsonProperty("operationLabelLg2")
    public void setOperationLabelLg2(String operationLabelLg2) {
        this.operationLabelLg2 = operationLabelLg2;
    }

    public OperationById withOperationLabelLg2(String operationLabelLg2) {
        this.operationLabelLg2 = operationLabelLg2;
        return this;
    }

    @JsonProperty("seriesLabelLg1")
    public String getSeriesLabelLg1() {
        return seriesLabelLg1;
    }

    @JsonProperty("seriesLabelLg1")
    public void setSeriesLabelLg1(String seriesLabelLg1) {
        this.seriesLabelLg1 = seriesLabelLg1;
    }

    public OperationById withSeriesLabelLg1(String seriesLabelLg1) {
        this.seriesLabelLg1 = seriesLabelLg1;
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

    public OperationById withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("seriesId")
    public String getSeriesId() {
        return seriesId;
    }

    @JsonProperty("seriesId")
    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public OperationById withSeriesId(String seriesId) {
        this.seriesId = seriesId;
        return this;
    }

    @JsonProperty("seriesLabelLg2")
    public String getSeriesLabelLg2() {
        return seriesLabelLg2;
    }

    @JsonProperty("seriesLabelLg2")
    public void setSeriesLabelLg2(String seriesLabelLg2) {
        this.seriesLabelLg2 = seriesLabelLg2;
    }

    @JsonProperty("proprietaire")
    public String getProprietaire() {
        return proprietaire;
    }

    public String getMillesime() {
        return millesime;
    }

    @JsonProperty("proprietaire")
    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public OperationById withSeriesLabelLg2(String seriesLabelLg2) {
        this.seriesLabelLg2 = seriesLabelLg2;
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

    public OperationById withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
