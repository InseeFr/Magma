package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

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
        "titre",
        "dateMiseAJour",
        "dateCreation",
        "statut de validation"

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

    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified, String created, List<ThemeModelSwagger> themeModelSwaggerS, List<SerieModelSwagger> serieModelSwaggerS, List<OperationModelSwagger> operationModelSwaggerS) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.created = created;
        this.themeModelSwaggerS = themeModelSwaggerS;
        this.serieModelSwaggerS = serieModelSwaggerS;
        this.operationModelSwaggerS = operationModelSwaggerS;
    }

    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified, String created, String validationState, List<ThemeModelSwagger> themeModelSwaggerS, List<SerieModelSwagger> serieModelSwaggerS, List<OperationModelSwagger> operationModelSwaggerS) {
        this.id = id;
        this.title = title;
        this.uri = uri;
        this.modified = modified;
        this.created = created;
        this.validationState = validationState;
        this.themeModelSwaggerS = themeModelSwaggerS;
        this.serieModelSwaggerS = serieModelSwaggerS;
        this.operationModelSwaggerS = operationModelSwaggerS;
    }
    public DataSetModelSwagger(String id, String uri, String modified) {
        this.id = id;
        this.uri = uri;
        this.modified = modified;
    }


    public DataSetModelSwagger(String id, List<Title> title, String uri, String modified) {
        this.id = id;
        this.title = title;
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
    public void setTitle(List<Title> title) {
        this.title = title;
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
    public void setModified(String DateModification) {
        this.modified = DateModification;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String dateCreation) {
        this.created = dateCreation;
    }

    @JsonProperty("validationState")
    public String getValidationState() {
        return validationState;
    }

    @JsonProperty("validationState")
    public void setValidationState(String statutValidation) {
        this.validationState = statutValidation;
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
