
package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import fr.insee.rmes.utils.config.Config;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import jakarta.annotation.Generated;
import java.io.Serializable;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lang",
        "content"
})
@Generated("jsonschema2pojo")
@EqualsAndHashCode
public class LangContent implements Serializable
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
    public LangContent() {
    }

    /**
     *
     * @param lang
     * @param content
     */
    public LangContent(String lang, String content) {
        super();
        this.lang = lang;
        this.content = content;
    }

    public static LangContent lg1(String elementLg1) {
        LangContent titre1 = new LangContent(Config.LG1, elementLg1);
        return titre1;
    }
    public static LangContent lg2(String elementLg2) {
        LangContent titre2 = new LangContent(Config.LG2, elementLg2);
        return titre2;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }

    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }

    public LangContent withLang(String lang) {
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

    public LangContent withContent(String content) {
        this.content = content;
        return this;
    }
}
