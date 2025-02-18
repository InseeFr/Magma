package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.annotation.Generated;
import java.io.Serializable;


@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uri",
        "id",
        "dsd",
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class Structure implements Serializable {

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("id")
    private String id;

    @JsonProperty("dsd")
    private String dsd;


    public Structure(String uri) {
        this.uri = uri;
    }

    public Structure(String id, String dsd) {
        this.id = id;
        this.dsd = dsd;
    }

    public Structure(String uri,String id, String dsd) {
        this.uri = uri;
        this.id = id;
        this.dsd = dsd;
    }


}
