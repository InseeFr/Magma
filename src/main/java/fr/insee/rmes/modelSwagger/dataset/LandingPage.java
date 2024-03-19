package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.annotation.Generated;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lang",
        "url"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class LandingPage {
    @JsonProperty("lang")
    private String lang;

    @JsonProperty("url")
    private String url;


    public LandingPage(String lang, String url) {
        this.lang = lang;
        this.url = url;
    }
}
