package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lang",
        "url"
})
@Generated("jsonschema2pojo")
public class LandingPage {
    @JsonProperty("lang")
    private String lang;

    @JsonProperty("url")
    private String url;

    public LandingPage() {
    }

    public LandingPage(String lang, String url) {
        this.lang = lang;
        this.url = url;
    }

    @JsonProperty("lang")
    public String getLang() {
        return lang;
    }
    @JsonProperty("lang")
    public void setLang(String lang) {
        this.lang = lang;
    }
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }
}
