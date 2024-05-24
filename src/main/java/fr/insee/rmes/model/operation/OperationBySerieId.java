
package fr.insee.rmes.model.operation;

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
    "series",
    "typeLabelLg1",
    "typeLabelLg2",
    "operationId",
    "operationLabelLg1",
    "operationLabelLg2",
    "operationAltLabelLg2",
    "seriesLabelLg1",
    "operationAltLabelLg1",
    "uri",
    "seriesId",
    "seriesLabelLg2"
})
@Generated("jsonschema2pojo")
public class OperationBySerieId implements Serializable
{

    @JsonProperty("series")
    private String series;
    @JsonProperty("typeLabelLg1")
    private String typeLabelLg1;
    @JsonProperty("typeLabelLg2")
    private String typeLabelLg2;
    @JsonProperty("operationId")
    private String operationId;
    @JsonProperty("operationLabelLg1")
    private String operationLabelLg1;
    @JsonProperty("operationLabelLg2")
    private String operationLabelLg2;
    @JsonProperty("operationAltLabelLg2")
    private String operationAltLabelLg2;
    @JsonProperty("seriesLabelLg1")
    private String seriesLabelLg1;
    @JsonProperty("operationAltLabelLg1")
    private String operationAltLabelLg1;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("seriesId")
    private String seriesId;
    @JsonProperty("seriesLabelLg2")
    private String seriesLabelLg2;
    @JsonProperty("periodicityId")
    private String periodicityId;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -379683856557303223L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OperationBySerieId() {
    }

    /**
     * 
     * @param series
     * @param typeLabelLg1
     * @param typeLabelLg2
     * @param operationId
     * @param operationLabelLg1
     * @param operationLabelLg2
     * @param operationAltLabelLg2
     * @param seriesLabelLg1
     * @param operationAltLabelLg1
     * @param uri
     * @param seriesId
     * @param seriesLabelLg2
     */
    public OperationBySerieId(String series, String typeLabelLg1, String typeLabelLg2, String operationId, String operationLabelLg1, String operationLabelLg2, String operationAltLabelLg2, String seriesLabelLg1, String operationAltLabelLg1, String uri, String seriesId, String seriesLabelLg2,String periodicityId) {
        super();
        this.series = series;
        this.typeLabelLg1 = typeLabelLg1;
        this.typeLabelLg2 = typeLabelLg2;
        this.operationId = operationId;
        this.operationLabelLg1 = operationLabelLg1;
        this.operationLabelLg2 = operationLabelLg2;
        this.operationAltLabelLg2 = operationAltLabelLg2;
        this.seriesLabelLg1 = seriesLabelLg1;
        this.operationAltLabelLg1 = operationAltLabelLg1;
        this.uri = uri;
        this.seriesId = seriesId;
        this.seriesLabelLg2 = seriesLabelLg2;
        this.periodicityId= periodicityId;
    }

    @JsonProperty("series")
    public String getSeries() {
        return series;
    }

    @JsonProperty("series")
    public void setSeries(String series) {
        this.series = series;
    }

    public OperationBySerieId withSeries(String series) {
        this.series = series;
        return this;
    }

    @JsonProperty("typeLabelLg1")
    public String getTypeLabelLg1() {
        return typeLabelLg1;
    }

    @JsonProperty("typeLabelLg1")
    public void setTypeLabelLg1(String typeLabelLg1) {
        this.typeLabelLg1 = typeLabelLg1;
    }

    public OperationBySerieId withTypeLabelLg1(String typeLabelLg1) {
        this.typeLabelLg1 = typeLabelLg1;
        return this;
    }

    @JsonProperty("typeLabelLg2")
    public String getTypeLabelLg2() {
        return typeLabelLg2;
    }

    @JsonProperty("typeLabelLg2")
    public void setTypeLabelLg2(String typeLabelLg2) {
        this.typeLabelLg2 = typeLabelLg2;
    }

    public OperationBySerieId withTypeLabelLg2(String typeLabelLg2) {
        this.typeLabelLg2 = typeLabelLg2;
        return this;
    }

    @JsonProperty("operationId")
    public String getOperationId() {
        return operationId;
    }

    @JsonProperty("operationId")
    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public OperationBySerieId withOperationId(String operationId) {
        this.operationId = operationId;
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

    public OperationBySerieId withOperationLabelLg1(String operationLabelLg1) {
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

    public OperationBySerieId withOperationLabelLg2(String operationLabelLg2) {
        this.operationLabelLg2 = operationLabelLg2;
        return this;
    }

    @JsonProperty("operationAltLabelLg2")
    public String getOperationAltLabelLg2() {
        return operationAltLabelLg2;
    }

    @JsonProperty("operationAltLabelLg2")
    public void setOperationAltLabelLg2(String operationAltLabelLg2) {
        this.operationAltLabelLg2 = operationAltLabelLg2;
    }

    public OperationBySerieId withOperationAltLabelLg2(String operationAltLabelLg2) {
        this.operationAltLabelLg2 = operationAltLabelLg2;
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

    public OperationBySerieId withSeriesLabelLg1(String seriesLabelLg1) {
        this.seriesLabelLg1 = seriesLabelLg1;
        return this;
    }

    @JsonProperty("operationAltLabelLg1")
    public String getOperationAltLabelLg1() {
        return operationAltLabelLg1;
    }

    @JsonProperty("operationAltLabelLg1")
    public void setOperationAltLabelLg1(String operationAltLabelLg1) {
        this.operationAltLabelLg1 = operationAltLabelLg1;
    }

    public OperationBySerieId withOperationAltLabelLg1(String operationAltLabelLg1) {
        this.operationAltLabelLg1 = operationAltLabelLg1;
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

    public OperationBySerieId withUri(String uri) {
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

    public OperationBySerieId withSeriesId(String seriesId) {
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

    public OperationBySerieId withSeriesLabelLg2(String seriesLabelLg2) {
        this.seriesLabelLg2 = seriesLabelLg2;
        return this;
    }
    @JsonProperty("periodicityId")
    public String getPeriodicityId() {
        return periodicityId;
    }
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public OperationBySerieId withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
