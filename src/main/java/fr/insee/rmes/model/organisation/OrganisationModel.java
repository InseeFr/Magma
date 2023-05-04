package fr.insee.rmes.model.organisation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
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
    private String preflabelLg2;
    @JsonProperty("prefLabelLg1")
    private String preflabelLg1;

    @JsonProperty("altLabelLg2")
    private String altlabelLg2;
    @JsonProperty("altLabelLg1")
    private String altlabelLg1;

    public OrganisationModel(String id, String uri,String abreviation,String preflabelLg2, String preflabelLg1, String altlabelLg1 ,String altlabelLg2, String uniteDe, String sousTelleDe) {
        this.uri = uri;
        this.id = id;
        this.uniteDe = uniteDe;
        this.sousTelleDe = sousTelleDe;
        this.abreviation = abreviation;
        this.preflabelLg2 = preflabelLg2;
        this.preflabelLg1 = preflabelLg1;
        this.altlabelLg2 = altlabelLg2;
        this.altlabelLg1 = altlabelLg1;
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

    public String getPreflabelLg2() {
        return preflabelLg2;
    }

    public void setPreflabelLg2(String preflabelLg2) {
        this.preflabelLg2 = preflabelLg2;
    }

    public String getPreflabelLg1() {
        return preflabelLg1;
    }

    public void setPreflabelLg1(String preflabelLg1) {
        this.preflabelLg1 = preflabelLg1;
    }

    public String getAltlabelLg2() {
        return altlabelLg2;
    }

    public void setAltlabelLg2(String altlabelLg2) {
        this.altlabelLg2 = altlabelLg2;
    }

    public String getAltlabelLg1() {
        return altlabelLg1;
    }

    public void setAltlabelLg1(String altlabelLg1) {
        this.altlabelLg1 = altlabelLg1;
    }
}
