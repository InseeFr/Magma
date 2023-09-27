
package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lang",
        "content"
})
@Generated("jsonschema2pojo")
public class Title implements Serializable
{

    @JsonProperty("lang")
    private String lang;
    @JsonProperty("content")
    @JsonAlias({"content","seriesLabelLg1","seriesLabelLg2","operationLabelLg1","operationLabelLg2","familyLabelLg1","familyLabelLg2","typeLabelLg1","typeLabelLg2",
            "periodicityLabelLg1","periodicityLabelLg2","titreLg1","titreLg2"})
    private String content;
    private final static long serialVersionUID = -3598103317305309574L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Title() {
    }

    /**
     *
     * @param lang
     * @param content
     */
    public Title(String lang, String content) {
        super();
        this.lang = lang;
        this.content = content;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    public Title withLang(String lang) {
        this.lang = lang;
        return this;
    }

    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    public Title withContent(String content) {
        this.content = content;
        return this;
    }
}
