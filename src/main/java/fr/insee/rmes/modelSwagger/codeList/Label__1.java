
package fr.insee.rmes.modelSwagger.codeList;

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
    "langue",
    "contenu"
})
@Generated("jsonschema2pojo")
public class Label__1 implements Serializable
{

    @JsonProperty("langue")
    private String langue;
    @JsonProperty("contenu")
    private String contenu;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1829699267786986671L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Label__1() {
    }

    /**
     * 
     * @param langue
     * @param contenu
     */
    public Label__1(String langue, String contenu) {
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

    public Label__1 withLangue(String langue) {
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

    public Label__1 withContenu(String contenu) {
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

    public Label__1 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
