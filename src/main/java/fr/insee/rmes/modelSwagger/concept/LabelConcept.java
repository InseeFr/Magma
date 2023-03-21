
package fr.insee.rmes.modelSwagger.concept;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "langue",
    "contenu"
})
@Generated("jsonschema2pojo")
public class LabelConcept implements Serializable
{

    @JsonProperty("langue")
    private String langue;
    @JsonProperty("contenu")
    @JsonAlias({"contenu","seriesLabelLg1","seriesLabelLg2","labelSerieLg1","labelSerieLg2","operationLabelLg1","operationLabelLg2","labelOperationLg1","labelOperationLg2","familyLabelLg1","familyLabelLg2","typeLabelLg1","typeLabelLg2",
            "periodicityLabelLg1","periodicityLabelLg2","labelThemeLg1","labelThemeLg2","prefLabelLg1","prefLabelLg2"})
    private String contenu;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3598103317305309574L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LabelConcept() {
    }

    /**
     *
     * @param langue
     * @param contenu
     */
    public LabelConcept(String langue, String contenu) {
        super();
        this.langue = langue;
        this.contenu = contenu;
    }



    @JsonProperty("langue")
    public String getLangue() {
        return langue;
    }

    @JsonProperty("langue")
    public void setLangue(String langue) {
        this.langue = langue;
    }

    public LabelConcept withLangue(String langue) {
        this.langue = langue;
        return this;
    }

    @JsonProperty("contenu")
    public String getContenu() {
        return contenu;
    }

    @JsonProperty("contenu")
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LabelConcept withContenu(String contenu) {
        this.contenu = contenu;
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

    public LabelConcept withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
