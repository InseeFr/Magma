
package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "lang",
    "content"
})
@Generated("jsonschema2pojo")
public class Titre implements Serializable
{

    @JsonProperty("lang")
    private String lang;
    @JsonProperty("content")
    @JsonAlias({"content","seriesLabelLg1","seriesLabelLg2","operationLabelLg1","operationLabelLg2","familyLabelLg1","familyLabelLg2","typeLabelLg1","typeLabelLg2",
            "periodicityLabelLg1","periodicityLabelLg2","titreLg1","titreLg2"})
    private String content;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3598103317305309574L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Titre() {
    }

    /**
     * 
     * @param lang
     * @param content
     */
    public Titre(String lang, String content) {
        super();
        this.lang  = lang;
        this.content = content;
    }

    @JsonProperty("lang")
    public String getLangue() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLangue(String lang) {
        this.lang = lang;
    }

    public Titre withLangue(String lang) {
        this.lang = lang;
        return this;
    }

    @JsonProperty("content")
    public String getContenu() {
        return content;
    }

    @JsonProperty("content")
    public void setContenu(String content) {
        this.content = content;
    }

    public Titre withContenu(String content) {
        this.content = content;
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

    public Titre withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
