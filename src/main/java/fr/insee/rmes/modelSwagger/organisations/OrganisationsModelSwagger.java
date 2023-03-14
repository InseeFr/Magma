package fr.insee.rmes.modelSwagger.organisations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "label"
})
public class OrganisationsModelSwagger implements Serializable {
    @JsonProperty("id")
    private String organisationId;
    @JsonProperty("label")
    @Valid
    private List<Label> labelOrganisation = null;

    public OrganisationsModelSwagger(String organisationId, List<Label> labelOrganisation) {
        super();
        this.organisationId = organisationId;
        this.labelOrganisation = labelOrganisation;
    }

    public List<Label> getLabelOrganisation() {
        return labelOrganisation;
    }
}


