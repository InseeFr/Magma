package fr.insee.rmes.magma.gestion.old.model.organisation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "uri",
        "abreviation",
        "prefLabelLg2",
        "prefLabelLg1",
        "altLabelLg2",
        "altLabelLg1",
        "uniteDe",
        "sousTelleDe"
})
@Generated("jsonschema2pojo")
public class OrganisationModel implements Serializable {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("abreviation")
    private String abreviation;

    @JsonProperty("uniteDe")
    private String uniteDe;
    @JsonProperty("sousTelleDe")
    private String sousTelleDe;
    @JsonProperty("prefLabelLg2")
    private String prefLabelLg2;
    @JsonProperty("prefLabelLg1")
    private String prefLabelLg1;

    @JsonProperty("altLabelLg2")
    private String altLabelLg2;
    @JsonProperty("altLabelLg1")
    private String altLabelLg1;

    public OrganisationModel(String id, String uri, String abreviation, String uniteDe, String sousTelleDe, String prefLabelLg2, String prefLabelLg1, String altLabelLg2, String altLabelLg1) {
        this.id = id;
        this.uri = uri;
        this.abreviation = abreviation;
        this.uniteDe = uniteDe;
        this.sousTelleDe = sousTelleDe;
        this.prefLabelLg2 = prefLabelLg2;
        this.prefLabelLg1 = prefLabelLg1;
        this.altLabelLg2 = altLabelLg2;
        this.altLabelLg1 = altLabelLg1;
    }

    public OrganisationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getUniteDe() {
        return uniteDe;
    }

    public void setUniteDe(String uniteDe) {
        this.uniteDe = uniteDe;
    }

    public String getSousTelleDe() {
        return sousTelleDe;
    }

    public void setSousTelleDe(String sousTelleDe) {
        this.sousTelleDe = sousTelleDe;
    }

    public String getPrefLabelLg2() {
        return prefLabelLg2;
    }

    public void setPrefLabelLg2(String prefLabelLg2) {
        this.prefLabelLg2 = prefLabelLg2;
    }

    public String getPrefLabelLg1() {
        return prefLabelLg1;
    }

    public void setPrefLabelLg1(String prefLabelLg1) {
        this.prefLabelLg1 = prefLabelLg1;
    }

    public String getAltLabelLg2() {
        return altLabelLg2;
    }

    public void setAltLabelLg2(String altLabelLg2) {
        this.altLabelLg2 = altLabelLg2;
    }

    public String getAltLabelLg1() {
        return altLabelLg1;
    }

    public void setAltLabelLg1(String altLabelLg1) {
        this.altLabelLg1 = altLabelLg1;
    }
}
