
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
    "type",
    "familyId",
    "periodicityId",
    "periodicityLabelLg2",
    "periodicityLabelLg1",
    "series",
    "typeLabelLg1",
    "typeLabelLg2",
    "periodicity",
    "typeId",
    "id",
    "seriesLabelLg1",
    "seriesAltLabelLg1",
    "nbOperation",
    "family",
    "familyLabelLg1",
    "seriesAltLabelLg2",
    "familyLabelLg2",
    "seriesLabelLg2"
})
@Generated("jsonschema2pojo")
public class SerieById implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("familyId")
    private String familyId;
    @JsonProperty("periodicityId")
    private String periodicityId;
    @JsonProperty("periodicityLabelLg2")
    private String periodicityLabelLg2;
    @JsonProperty("periodicityLabelLg1")
    private String periodicityLabelLg1;
    @JsonProperty("series")
    private String series;
    @JsonProperty("typeLabelLg1")
    private String typeLabelLg1;
    @JsonProperty("typeLabelLg2")
    private String typeLabelLg2;
    @JsonProperty("periodicity")
    private String periodicity;
    @JsonProperty("typeId")
    private String typeId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("seriesLabelLg1")
    private String seriesLabelLg1;
    @JsonProperty("seriesAltLabelLg1")
    private String seriesAltLabelLg1;
    @JsonProperty("nbOperation")
    private String nbOperation;
    @JsonProperty("family")
    private String family;
    @JsonProperty("familyLabelLg1")
    private String familyLabelLg1;
    @JsonProperty("seriesAltLabelLg2")
    private String seriesAltLabelLg2;
    @JsonProperty("familyLabelLg2")
    private String familyLabelLg2;
    @JsonProperty("seriesLabelLg2")
    private String seriesLabelLg2;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4113476655597887558L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public SerieById() {
    }

    /**
     * 
     * @param type
     * @param familyId
     * @param periodicityId
     * @param periodicityLabelLg2
     * @param periodicityLabelLg1
     * @param series
     * @param typeLabelLg1
     * @param typeLabelLg2
     * @param periodicity
     * @param typeId
     * @param id
     * @param seriesLabelLg1
     * @param seriesAltLabelLg1
     * @param nbOperation
     * @param family
     * @param familyLabelLg1
     * @param seriesAltLabelLg2
     * @param familyLabelLg2
     * @param seriesLabelLg2
     */
    public SerieById(String type, String familyId, String periodicityId, String periodicityLabelLg2, String periodicityLabelLg1, String series, String typeLabelLg1, String typeLabelLg2, String periodicity, String typeId, String id, String seriesLabelLg1, String seriesAltLabelLg1, String nbOperation, String family, String familyLabelLg1, String seriesAltLabelLg2, String familyLabelLg2, String seriesLabelLg2) {
        super();
        this.type = type;
        this.familyId = familyId;
        this.periodicityId = periodicityId;
        this.periodicityLabelLg2 = periodicityLabelLg2;
        this.periodicityLabelLg1 = periodicityLabelLg1;
        this.series = series;
        this.typeLabelLg1 = typeLabelLg1;
        this.typeLabelLg2 = typeLabelLg2;
        this.periodicity = periodicity;
        this.typeId = typeId;
        this.id = id;
        this.seriesLabelLg1 = seriesLabelLg1;
        this.seriesAltLabelLg1 = seriesAltLabelLg1;
        this.nbOperation = nbOperation;
        this.family = family;
        this.familyLabelLg1 = familyLabelLg1;
        this.seriesAltLabelLg2 = seriesAltLabelLg2;
        this.familyLabelLg2 = familyLabelLg2;
        this.seriesLabelLg2 = seriesLabelLg2;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public SerieById withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("familyId")
    public String getFamilyId() {
        return familyId;
    }

    @JsonProperty("familyId")
    public void setFamilyId(String familyId) {
        this.familyId = familyId;
    }

    public SerieById withFamilyId(String familyId) {
        this.familyId = familyId;
        return this;
    }

    @JsonProperty("periodicityId")
    public String getPeriodicityId() {
        return periodicityId;
    }

    @JsonProperty("periodicityId")
    public void setPeriodicityId(String periodicityId) {
        this.periodicityId = periodicityId;
    }

    public SerieById withPeriodicityId(String periodicityId) {
        this.periodicityId = periodicityId;
        return this;
    }

    @JsonProperty("periodicityLabelLg2")
    public String getPeriodicityLabelLg2() {
        return periodicityLabelLg2;
    }

    @JsonProperty("periodicityLabelLg2")
    public void setPeriodicityLabelLg2(String periodicityLabelLg2) {
        this.periodicityLabelLg2 = periodicityLabelLg2;
    }

    public SerieById withPeriodicityLabelLg2(String periodicityLabelLg2) {
        this.periodicityLabelLg2 = periodicityLabelLg2;
        return this;
    }

    @JsonProperty("periodicityLabelLg1")
    public String getPeriodicityLabelLg1() {
        return periodicityLabelLg1;
    }

    @JsonProperty("periodicityLabelLg1")
    public void setPeriodicityLabelLg1(String periodicityLabelLg1) {
        this.periodicityLabelLg1 = periodicityLabelLg1;
    }

    public SerieById withPeriodicityLabelLg1(String periodicityLabelLg1) {
        this.periodicityLabelLg1 = periodicityLabelLg1;
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

    public SerieById withSeries(String series) {
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

    public SerieById withTypeLabelLg1(String typeLabelLg1) {
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

    public SerieById withTypeLabelLg2(String typeLabelLg2) {
        this.typeLabelLg2 = typeLabelLg2;
        return this;
    }

    @JsonProperty("periodicity")
    public String getPeriodicity() {
        return periodicity;
    }

    @JsonProperty("periodicity")
    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public SerieById withPeriodicity(String periodicity) {
        this.periodicity = periodicity;
        return this;
    }

    @JsonProperty("typeId")
    public String getTypeId() {
        return typeId;
    }

    @JsonProperty("typeId")
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public SerieById withTypeId(String typeId) {
        this.typeId = typeId;
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

    public SerieById withId(String id) {
        this.id = id;
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

    public SerieById withSeriesLabelLg1(String seriesLabelLg1) {
        this.seriesLabelLg1 = seriesLabelLg1;
        return this;
    }

    @JsonProperty("seriesAltLabelLg1")
    public String getSeriesAltLabelLg1() {
        return seriesAltLabelLg1;
    }

    @JsonProperty("seriesAltLabelLg1")
    public void setSeriesAltLabelLg1(String seriesAltLabelLg1) {
        this.seriesAltLabelLg1 = seriesAltLabelLg1;
    }

    public SerieById withSeriesAltLabelLg1(String seriesAltLabelLg1) {
        this.seriesAltLabelLg1 = seriesAltLabelLg1;
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

    public SerieById withNbOperation(String nbOperation) {
        this.nbOperation = nbOperation;
        return this;
    }

    @JsonProperty("family")
    public String getFamily() {
        return family;
    }

    @JsonProperty("family")
    public void setFamily(String family) {
        this.family = family;
    }

    public SerieById withFamily(String family) {
        this.family = family;
        return this;
    }

    @JsonProperty("familyLabelLg1")
    public String getFamilyLabelLg1() {
        return familyLabelLg1;
    }

    @JsonProperty("familyLabelLg1")
    public void setFamilyLabelLg1(String familyLabelLg1) {
        this.familyLabelLg1 = familyLabelLg1;
    }

    public SerieById withFamilyLabelLg1(String familyLabelLg1) {
        this.familyLabelLg1 = familyLabelLg1;
        return this;
    }

    @JsonProperty("seriesAltLabelLg2")
    public String getSeriesAltLabelLg2() {
        return seriesAltLabelLg2;
    }

    @JsonProperty("seriesAltLabelLg2")
    public void setSeriesAltLabelLg2(String seriesAltLabelLg2) {
        this.seriesAltLabelLg2 = seriesAltLabelLg2;
    }

    public SerieById withSeriesAltLabelLg2(String seriesAltLabelLg2) {
        this.seriesAltLabelLg2 = seriesAltLabelLg2;
        return this;
    }

    @JsonProperty("familyLabelLg2")
    public String getFamilyLabelLg2() {
        return familyLabelLg2;
    }

    @JsonProperty("familyLabelLg2")
    public void setFamilyLabelLg2(String familyLabelLg2) {
        this.familyLabelLg2 = familyLabelLg2;
    }

    public SerieById withFamilyLabelLg2(String familyLabelLg2) {
        this.familyLabelLg2 = familyLabelLg2;
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

    public SerieById withSeriesLabelLg2(String seriesLabelLg2) {
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

    public SerieById withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
