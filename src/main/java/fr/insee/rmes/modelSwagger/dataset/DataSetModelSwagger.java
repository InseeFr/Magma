package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonPropertyOrder({
        "id",
        "uri",
        "identifier",
        "created",
        "modified",
        "disseminationStatus",
        "validationState",
        "creator",
        "contributor",
        "title"

})
@Generated("jsonschema2pojo")
public class DataSetModelSwagger implements Serializable  {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    //@Valid
    private List<Title> title ;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty("created")
    private String created;
    @JsonProperty ("validationState")
    private String validationState;
    @JsonProperty("contributor")
    private String contributor;
    @JsonProperty("creator")
    private String creator;
    @JsonProperty("disseminationStatus")
    private String disseminationStatus;

    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("subtitle")
    private List<Title> subtitle;
    @JsonProperty("abstract")
    private List<Title> abstractDataset;
    @JsonProperty("description")
    private List<Title> description;
    @JsonProperty("scopeNote")
    private List<Title> scopeNote;
    @JsonProperty("landingPage")
    private List<LandingPage> landingPage;
    @JsonProperty("processStep")
    private List<Title> processStep;
    @JsonProperty("publisher")
    private IdLabel publisher;
    @JsonProperty("wasGeneratedBy")
    private List<IdLabel> wasGeneratedBy;
    @JsonProperty("type")
    private List<Title> type;
    @JsonProperty("archiveUnit")
    private List<IdLabel> archiveUnit;
    @JsonProperty("accessRights")
    private List<Title> accessRights;
    @JsonProperty("confidentialityStatus")
    private List<Title> confidentialityStatus;
    @JsonProperty("accrualPeriodicity")
    private Label accrualPeriodicity;
    @JsonProperty("temporal")
    private Temporal temporal;
    @JsonProperty("temporalResolution")
    private List<Label> temporalResolution;
    @JsonProperty("spatial")
    private IdLabel spatial;
    @JsonProperty("spatialResolution")
    private List<IdLabel> spatialResolution;
    @JsonProperty("statisticalUnit")
    private List<Title> statisticalUnit;
    @JsonProperty("structure")
    private Structure structure;
    @JsonProperty("version")
    private String version;
    @JsonProperty("issued")
    private String issued;
    @JsonProperty("numObservations")
    private Integer numObservations;
    @JsonProperty("numSeries")
    private Integer numSeries;




    @JsonProperty("theme")
    @JsonInclude(NON_NULL)
    private List<ThemeModelSwagger> themeModelSwaggerS;
    @JsonProperty("serie")
    @JsonInclude(NON_NULL)
    private List<SerieModelSwagger> serieModelSwaggerS;
    @JsonProperty("operation")
    @JsonInclude(NON_NULL)
    private List<OperationModelSwagger> operationModelSwaggerS;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DataSetModelSwagger() {
    }

    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified, String created, String validationState, String contributor, String creator, String disseminationStatus) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.created = created;
        this.validationState = validationState;
        this.contributor = contributor;
        this.creator = creator;
        this.disseminationStatus = disseminationStatus;
    }

    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified, String created, String validationState, List<ThemeModelSwagger> themeModelSwaggerS, List<SerieModelSwagger> serieModelSwaggerS, List<OperationModelSwagger> operationModelSwaggerS) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.created = created;
        this.validationState=validationState;
        this.themeModelSwaggerS = themeModelSwaggerS;
        this.serieModelSwaggerS = serieModelSwaggerS;
        this.operationModelSwaggerS = operationModelSwaggerS;
    }
    public DataSetModelSwagger(String id, String uri, String modified) {
        this.id = id;
        this.uri = uri;
        this.modified = modified;
    }



    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified, String validationState) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.validationState= validationState;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public List<Title> getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(List<Title> titles) {
        this.title = titles;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("modified")
    public String getModified() {
        return modified;
    }

    @JsonProperty("modified")
    public void setCreated(String DateModification) {
        this.modified = DateModification;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setModified(String created) {
        this.created = created;
    }

    @JsonProperty("validationState")
    public String getValidationState() {
        return validationState;
    }

    @JsonProperty("validationState")
    public void setValidationState(String validationState) {
        this.validationState = validationState;
    }
    @JsonProperty("contributor")
    public String getContributor() {
        return contributor;
    }
    @JsonProperty("contributor")
    public void setContributor(String contributor) {
        this.contributor = contributor;
    }
    @JsonProperty("creator")
    public String getCreator() {
        return creator;
    }
    @JsonProperty("creator")
    public void setCreator(String creator) {
        this.creator = creator;
    }
    @JsonProperty("disseminationStatus")
    public String getDisseminationStatus() {
        return disseminationStatus;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    public void setSubtitle(List<Title> subtitle) {
        this.subtitle = subtitle;
    }

    public void setAbstractDataset(List<Title> abstractDataset) {
        this.abstractDataset = abstractDataset;
    }

    public void setDescription(List<Title> description) {
        this.description = description;
    }

    public void setScopeNote(List<Title> scopeNote) {
        this.scopeNote = scopeNote;
    }

    public void setLandingPage(List<LandingPage> landingPage) {
        this.landingPage = landingPage;
    }

    public void setProcessStep(List<Title> processStep) {
        this.processStep = processStep;
    }



    public void setWasGeneratedBy(List<IdLabel> wasGeneratedBy) {
        this.wasGeneratedBy = wasGeneratedBy;
    }

    public void setType(List<Title> type) {
        this.type = type;
    }



    public void setAccessRights(List<Title> accessRights) {
        this.accessRights = accessRights;
    }

    public void setConfidentialityStatus(List<Title> confidentialityStatus) {
        this.confidentialityStatus = confidentialityStatus;
    }

    public void setAccrualPeriodicity(Label accrualPeriodicity) {
        this.accrualPeriodicity = accrualPeriodicity;
    }

    public void setTemporal(Temporal temporal) {
        this.temporal = temporal;
    }

    public void setTemporalResolution(List<Label> temporalResolution) {
        this.temporalResolution = temporalResolution;
    }





    public void setStatisticalUnit(List<Title> statisticalUnit) {
        this.statisticalUnit = statisticalUnit;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setIssued(String issued) {
        this.issued = issued;
    }

    public void setNumObservations(Integer numObservations) {
        this.numObservations = numObservations;
    }

    public void setNumSeries(Integer numSeries) {
        this.numSeries = numSeries;
    }

    public void setPublisher(IdLabel publisher) {
        this.publisher = publisher;
    }

    public void setArchiveUnit(List<IdLabel> archiveUnit) {
        this.archiveUnit = archiveUnit;
    }

    public void setSpatial(IdLabel spatial) {
        this.spatial = spatial;
    }

    public void setSpatialResolution(List<IdLabel> spatialResolution) {
        this.spatialResolution = spatialResolution;
    }

    @JsonProperty("disseminationStatus")
    public void setDisseminationStatus(String disseminationStatus) {
        this.disseminationStatus = disseminationStatus;
    }

    @JsonProperty("theme")
    public List<ThemeModelSwagger> getThemeModelSwagger() {
        return themeModelSwaggerS;
    }

    @JsonProperty("theme")
    public void setThemeModelSwagger(List<ThemeModelSwagger> themeListModelSwagger) {
        this.themeModelSwaggerS = themeModelSwaggerS;
    }

    @JsonProperty("serie")
    public List<SerieModelSwagger> getSerieModelSwagger() {
        return serieModelSwaggerS;
    }
    @JsonProperty("serie")
    public void setSerieModelSwagger(List<SerieModelSwagger> serieModelSwagger) {
        this.serieModelSwaggerS = serieModelSwaggerS;
    }

    @JsonProperty("operation")
    public List<OperationModelSwagger> getOperationModelSwagger() {
        return operationModelSwaggerS;
    }
    @JsonProperty("operation")
    public void setOperationModelSwagger(List<OperationModelSwagger> operationModelSwagger) {
        this.operationModelSwaggerS = operationModelSwaggerS;
    }
}
