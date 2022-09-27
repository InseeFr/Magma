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
    @JsonProperty("titre")
    //@Valid
    private List<Titre> titre ;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("dateMiseAJour")
    private String dateModification;
    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty ("statutValidation")
    private String statutValidation;
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

    public DataSetModelSwagger(String id, List<Titre> titre, String uri, String dateModification, String dateCreation, List<ThemeModelSwagger> themeModelSwaggerS, List<SerieModelSwagger> serieModelSwaggerS, List<OperationModelSwagger> operationModelSwaggerS) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
        this.dateCreation = dateCreation;
        this.themeModelSwaggerS = themeModelSwaggerS;
        this.serieModelSwaggerS = serieModelSwaggerS;
        this.operationModelSwaggerS = operationModelSwaggerS;
    }

    public DataSetModelSwagger(String id, List<Titre> titre, String uri, String dateModification, String dateCreation, String statutValidation,List<ThemeModelSwagger> themeModelSwaggerS, List<SerieModelSwagger> serieModelSwaggerS, List<OperationModelSwagger> operationModelSwaggerS) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
        this.dateCreation = dateCreation;
        this.statutValidation=statutValidation;
        this.themeModelSwaggerS = themeModelSwaggerS;
        this.serieModelSwaggerS = serieModelSwaggerS;
        this.operationModelSwaggerS = operationModelSwaggerS;
    }
    public DataSetModelSwagger(String id, String uri, String dateModification) {
        this.id = id;
        this.uri = uri;
        this.dateModification = dateModification;
    }


    public DataSetModelSwagger(String id, List<Titre> titre, String uri, String dateModification) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
    }

    public DataSetModelSwagger(String id, List<Titre> titre, String uri, String dateModification,String statutValidation) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
        this.statutValidation= statutValidation;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("titre")
    public List<Titre> getTitre() {
        return titre;
    }

    @JsonProperty("titre")
    public void setTitre(List<Titre> titres) {
        this.titre = titres;
    }

    @JsonProperty("uri")
    public String getUri() {
        return uri;
    }

    @JsonProperty("uri")
    public void setUri(String uri) {
        this.uri = uri;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateModification() {
        return dateModification;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateModification(String DateModification) {
        this.dateModification = DateModification;
    }

    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    @JsonProperty("statutValidation")
    public String getStatutValidation() {
        return statutValidation;
    }

    @JsonProperty("statutValidation")
    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
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
