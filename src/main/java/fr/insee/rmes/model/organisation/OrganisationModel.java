package fr.insee.rmes.model.organisation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "LabelLg2",
        "LabelLg1",

})
@Generated("jsonschema2pojo")
public class OrganisationModel implements Serializable {

    @JsonProperty("Id")
    private String id;
    @JsonProperty("LabelLg2")
    private String labelLg2;
    @JsonProperty("LabelLg1")
    private String labelLg1;

    public OrganisationModel(String id, String labelLg2, String labelLg1) {
        this.id = id;
        this.labelLg2 = labelLg2;
        this.labelLg1 = labelLg1;
    }

    public OrganisationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelLg2() {
        return labelLg2;
    }

    public void setLabelLg2(String labelLg2) {
        this.labelLg2 = labelLg2;
    }

    public String getLabelLg1() {
        return labelLg1;
    }

    public void setLabelLg1(String labelLg1) {
        this.labelLg1 = labelLg1;
    }
}
