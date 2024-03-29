package fr.insee.rmes.model.concept;
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
        "dateCreation",
        "dateMiseAJour",
        "prefLabelLg1",
        "prefLabelLg2",
        "statutValidation",
        "id",
        "dateFinValidite",
        "uri",
        "version",
        "defcourte",
        "name"
})
@Generated("jsonschema2pojo")
public class ConceptById implements Serializable{
    @JsonProperty("dateCreation")
    private String dateCreation;
    @JsonProperty("dateMiseAJour")
    private String dateMiseAJour;
    @JsonProperty("prefLabelLg1")
    private String prefLabelLg1;
    @JsonProperty("prefLabelLg2")
    private String prefLabelLg2;
    @JsonProperty("statutValidation")
    private String statutValidation;
    @JsonProperty("id")
    private String id;
    @JsonProperty("dateFinValidite")
    private String dateFinValidite;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("version")
    private String version;
    @JsonProperty("name")
    private String name;


    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    /**
     * No args constructor for use in serialization
     *
     */
    public ConceptById() {
    }

    /**
     *
     * @param dateCreation
     * @param dateMiseAJour
     * @param prefLabelLg1
     * @param prefLabelLg2
     * @param statutValidation
     * @param id
     * @param dateFinValidite
     * @param uri
     * @param version
     * @param name
     */
    public ConceptById(String dateCreation, String dateMiseAJour, String prefLabelLg1, String prefLabelLg2, String statutValidation, String id, String dateFinValidite, String uri, String version, String name) {
        super();
        this.dateCreation = dateCreation;
        this.dateMiseAJour = dateMiseAJour;
        this.prefLabelLg1 = prefLabelLg1;
        this.prefLabelLg2 = prefLabelLg2;
        this.statutValidation = statutValidation;
        this.id = id;
        this.dateFinValidite = dateFinValidite;
        this.uri = uri;
        this.version = version;
        this.name =name;
    }


    @JsonProperty("dateCreation")
    public String getDateCreation() {
        return dateCreation;
    }

    @JsonProperty("dateCreation")
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public ConceptById withDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
        return this;
    }

    @JsonProperty("dateMiseAJour")
    public String getDateMiseAJour() {
        return dateMiseAJour;
    }

    @JsonProperty("dateMiseAJour")
    public void setDateMiseAJour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
    }

    public ConceptById withDateMiseAjour(String dateMiseAJour) {
        this.dateMiseAJour = dateMiseAJour;
        return this;
    }

    @JsonProperty("prefLabelLg1")
    public String getPrefLabelLg1() {
        return prefLabelLg1;
    }

    @JsonProperty("prefLabelLg1")
    public void setPrefLabelLg1(String prefLabelLg1) {
        this.prefLabelLg1 = prefLabelLg1;
    }

    public ConceptById withPrefLabelLg1(String prefLabelLg1) {
        this.prefLabelLg1 = prefLabelLg1;
        return this;
    }

    @JsonProperty("prefLabelLg2")
    public String getPrefLabelLg2() {
        return prefLabelLg2;
    }

    @JsonProperty("prefLabelLg2")
    public void setPrefLabelLg2(String prefLabelLg2) {
        this.prefLabelLg2 = prefLabelLg2;
    }

    public ConceptById withPrefLabelLg2(String prefLabelLg2) {
        this.prefLabelLg2 = prefLabelLg2;
        return this;
    }

    @JsonProperty("statutValidation")
    public String getStatutValidation() {
        return statutValidation;
    }

    @JsonProperty("statutValidation")
    public void setStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
    }

    public ConceptById withStatutValidation(String statutValidation) {
        this.statutValidation = statutValidation;
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

    public ConceptById withId(String id) {
        this.id = id;
        return this;
    }

    @JsonProperty("dateFinValidite")
    public String getDateFinValidite() {
        return dateFinValidite;
    }

    @JsonProperty("dateFinValidite")
    public void setDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public ConceptById withDateFinValidite(String dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
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

    public ConceptById withUri(String uri) {
        this.uri = uri;
        return this;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }




    public ConceptById withVersion(String version) {
        this.version = version;
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

    public ConceptById withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }
}
