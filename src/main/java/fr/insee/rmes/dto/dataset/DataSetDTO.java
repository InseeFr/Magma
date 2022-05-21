package fr.insee.rmes.dto.dataset;

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
import java.util.Optional;

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
public class DataSetDTO extends DataSetListDTO implements Serializable  {
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
    @JsonProperty("theme")
    @JsonInclude(NON_NULL)
    private List<ThemeDTO> themeDTOS;
    @JsonProperty("serie")
    @JsonInclude(NON_NULL)
    private List<SerieDTO> serieDTOS;
    @JsonProperty("operation")
    @JsonInclude(NON_NULL)
    private List<OperationDTO> operationDTOS;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public DataSetDTO() {
    }

    public DataSetDTO(String id, List<Titre> titre, String uri, String dateModification, String dateCreation, List<ThemeDTO> themeDTOS, List<SerieDTO> serieDTOS, List<OperationDTO> operationDTOS) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
        this.dateCreation = dateCreation;
        this.themeDTOS = themeDTOS;
        this.serieDTOS = serieDTOS;
        this.operationDTOS = operationDTOS;
    }

    public DataSetDTO(String id, List<Titre> titre, String uri, String dateModification) {
        this.id = id;
        this.titre = titre;
        this.uri = uri;
        this.dateModification = dateModification;
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

    @JsonProperty("theme")
    public List<ThemeDTO> getThemeDTO() {
        return themeDTOS;
    }

    @JsonProperty("theme")
    public void setThemeDTO(List<ThemeDTO> themeListDTO) {
        this.themeDTOS = themeDTOS;
    }

    @JsonProperty("serie")
    public List<SerieDTO> getSerieDTO() {
        return serieDTOS;
    }
    @JsonProperty("serie")
    public void setSerieDTO(List<SerieDTO> serieDTO) {
        this.serieDTOS = serieDTOS;
    }

    @JsonProperty("operation")
    public List<OperationDTO> getOperationDTO() {
        return operationDTOS;
    }
    @JsonProperty("operation")
    public void setOperationDTO(List<OperationDTO> operationDTO) {
        this.operationDTOS = operationDTOS;
    }
}
