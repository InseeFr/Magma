package fr.insee.rmes.modelSwagger.dataset;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

import javax.annotation.Generated;
import java.io.Serializable;


@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "dsd"
})
@Generated("jsonschema2pojo")
public class Structure implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("dsd")
    private String dsd;

    public Structure() {
    }

    public Structure(String id, String dsd) {
        this.id = id;
        this.dsd = dsd;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }
    @JsonProperty("dsd")
    public String getDsd() {
        return dsd;
    }
    @JsonProperty("dsd")
    public void setDsd(String dsd) {
        this.dsd = dsd;
    }
}
