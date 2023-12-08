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
        "catalogRecordCreated",
        "catalogRecordModified",
        "catalogRecordCreator",
        "catalogRecordContributor",
        "identifier",
        "issued",
        "modified",
        "disseminationStatus",
        "validationState",
        "version",
        "creator",
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
        "spatialTemporal",
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
    private String id;
    @JsonProperty("catalogRecordCreated")
    private CatalogRecordCreated catalogRecordCreated;
    @JsonProperty("catalogRecordModified")
    private CatalogRecordModified catalogRecordModified;
    @JsonProperty("catalogRecordCreator")
    private CatalogRecordCreator catalogRecordCreator;
    @JsonProperty("catalogRecordContributor")
    private CatalogRecordContributor catalogRecordContributor;
    @JsonProperty("title")
    //@Valid
    private List<LangContent> title ;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("modified")
    private String modified;
    @JsonProperty ("validationState")
    private String validationState;
    @JsonProperty("creator")
    private Creator creator;

    @JsonProperty("disseminationStatus")
    private String disseminationStatus;

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

    @JsonProperty("spatialTemporal")
    private SpatialTemporal spatialTemporal;
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

    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified,  String validationState, Creator creator, DisseminationStatus disseminationStatus,CatalogRecordCreated catalogRecordCreated, CatalogRecordModified catalogRecordModified,CatalogRecordCreator catalogRecordCreator,CatalogRecordContributor catalogRecordContributor) {
        this.id = id.toString();
        this.title = title;
        this.uri = uri.toString();
        this.modified = modified.toString();
        this.validationState = validationState;
        this.creator = creator;
        this.disseminationStatus = disseminationStatus.toString();
        this.catalogRecordContributor = catalogRecordContributor;
        this.catalogRecordCreator = catalogRecordCreator;
        this.catalogRecordCreated = catalogRecordCreated;
        this.catalogRecordModified = catalogRecordModified;
    }


    public DataSetModelSwagger(Id id, Uri uri, Modified modified) {
        this.id = id.toString();
        this.uri = uri.toString();
        this.modified = modified.toString();
    }


    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified, String validationState) {
        this.id = id.toString();
        this.title = title;
        this.uri = uri.toString();
        this.modified = modified.toString();
        this.validationState= validationState;
    }
    public DataSetModelSwagger(Id id, List<LangContent> title, Uri uri, Modified modified, String validationState, List<ThemeModelSwagger> themeModelSwaggerS) {
        this.id = id.toString();
        this.title = title;
        this.uri = uri.toString();
        this.modified = modified.toString();
        this.validationState = validationState;
        this.themeModelSwaggerS = themeModelSwaggerS;
    }

    public Id getIdWithTypeId(){
        Id id = new Id(this.id);
        return id;
    }

    public Uri getUriWithTypeUri(){
        Uri uri = new Uri(this.uri);
        return uri;
    }
}
