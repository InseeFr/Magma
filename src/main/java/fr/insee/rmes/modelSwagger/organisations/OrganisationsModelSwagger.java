package fr.insee.rmes.modelSwagger.organisations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "uri",
        "label",
        "altLabel",
        "abreviation",
        "uniteDe",
        "sousTelleDe"
})
public class OrganisationsModelSwagger implements Serializable {
    @JsonProperty("id")
    private String organisationId;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("label")
    @Valid
    private List<Label> labelOrganisation = null;

    @JsonProperty("altlabel")
    private List<Label> altlabelOrganisation = null;
    @JsonProperty("abreviation")
    private String abreviation;

    @JsonProperty("uniteDe")
    private String uniteDe;
    @JsonProperty("sousTelleDe")
    private String sousTelleDe;

    public OrganisationsModelSwagger(String organisationId,List<Label> labelOrganisation) {
        super();
        this.organisationId = organisationId;
        this.labelOrganisation = labelOrganisation;
    }

    public OrganisationsModelSwagger(String organisationId, String uri,List<Label> labelOrganisation) {
        super();
        this.uri = uri ;
        this.organisationId = organisationId;
        this.labelOrganisation = labelOrganisation;
    }



    public List<Label> getLabelOrganisation() {
        return labelOrganisation;
    }

    public String getUri() {
        return uri;
    }

    public List<Label> getAltlabelOrganisation() {
        return altlabelOrganisation;
    }

    public void setAltlabelOrganisation(List<Label> altlabelOrganisation) {
        this.altlabelOrganisation = altlabelOrganisation;
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
}


