package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.model.datasets.*;
import lombok.Getter;
import lombok.Setter;

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
        "issued",
        "modified",
        "disseminationStatus",
        "validationState",
        "version",
        "creator",
        "contributor",
        "publisher",
        "title",
        "subtitle",
        "abstract",
        "description",
        "scopeNote",
        "wasGeneratedBy",
        "type",
        "archiveUnit",
        "accessRights",
        "confidentialityStatus",
        "theme",
        "landingPage",
        "processStep",
        "accrualPeriodicity",
        "temporal",
        "temporalResolution",
        "spatial",
        "spatialResolution",
        "statisticalUnit",
        "structure",
        "numObservations",
        "numSeries"
})

@Generated("jsonschema2pojo")
@Setter
@Getter

public class DataSetModelSwagger implements Serializable  {

    @JsonProperty("id")
    private Id id;
    @JsonProperty("title")
    //@Valid
    private List<LangContent> title ;
    @JsonProperty("uri")
    private Uri uri;
    @JsonProperty("modified")
    private Modified modified;
    @JsonProperty("created")
    private Created created;
    @JsonProperty ("validationState")
    private String validationState;
    @JsonProperty("contributor")
    private Contributor contributor;
    @JsonProperty("creator")
    private Creator creator;

    @JsonProperty("disseminationStatus")
    private DisseminationStatus disseminationStatus;

    @JsonProperty("identifier")
    private String identifier;
    @JsonProperty("subtitle")
    private List<LangContent> subtitle;
    @JsonProperty("abstract")
    private List<LangContent> abstractDataset;
    @JsonProperty("description")
    private List<LangContent> description;
    @JsonProperty("scopeNote")
    private List<LangContent> scopeNote;
    @JsonProperty("landingPage")
    private List<LandingPage> landingPage;
    @JsonProperty("processStep")
    private List<LangContent> processStep;
    @JsonProperty("publisher")
    private IdLabel publisher;
    @JsonProperty("wasGeneratedBy")
    private List<IdLabel> wasGeneratedBy;
    @JsonProperty("type")
    private List<LangContent> type;
    @JsonProperty("archiveUnit")
    private List<IdLabel> archiveUnit;
    @JsonProperty("accessRights")
    private List<LangContent> accessRights;
    @JsonProperty("confidentialityStatus")
    private List<LangContent> confidentialityStatus;
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
    private List<LangContent> statisticalUnit;
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

    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DataSetModelSwagger() {
    }

    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified, Created created, String validationState, Contributor contributor, Creator creator, DisseminationStatus disseminationStatus) {
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


    public DataSetModelSwagger(Id id, Uri uri, Modified modified) {
        this.id = id;
        this.uri = uri;
        this.modified = modified;
    }


    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified, String validationState) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.validationState= validationState;
    }
    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified, Created created, String validationState, List<ThemeModelSwagger> themeModelSwaggerS) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.created = created;
        this.validationState = validationState;
        this.themeModelSwaggerS = themeModelSwaggerS;
    }

}
