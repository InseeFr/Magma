
package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.*;
import lombok.ToString;

import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lang",
        "content"
})
@Generated("jsonschema2pojo")
@ToString
public class LabelDataSet implements Serializable
{

    @JsonProperty("lang")
    private String lang;
    @JsonProperty("content")
    @JsonAlias({"content","seriesLabelLg1","seriesLabelLg2","labelSerieLg1","labelSerieLg2","operationLabelLg1","operationLabelLg2","labelOperationLg1","labelOperationLg2","familyLabelLg1","familyLabelLg2","typeLabelLg1","typeLabelLg2",
            "periodicityLabelLg1","periodicityLabelLg2","labelThemeLg1","labelThemeLg2"})
    private String content;
    private final static long serialVersionUID = -3598103317305309574L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LabelDataSet() {
    }

    /**
     *
     * @param lang
     * @param content
     */
    public LabelDataSet(String lang, String content) {
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

    public LabelDataSet withLang(String lang) {
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

    public LabelDataSet withContent(String content) {
        this.content = content;
        return this;
    }


}
