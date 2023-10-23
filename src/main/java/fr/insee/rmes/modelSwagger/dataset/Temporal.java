package fr.insee.rmes.modelSwagger.dataset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Generated;
import java.io.Serializable;

@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "startPeriod",
        "endPeriod"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
public class Temporal implements Serializable {
    @JsonProperty("startPeriod")
    private String startPeriod;

    @JsonProperty("endPeriod")
    private String endPeriod;

    public Temporal(String startPeriod, String endPeriod) {
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
    }

    @JsonProperty("startPeriod")
    public String getStartPeriod() {
        return startPeriod;
    }

    @JsonProperty("startPeriod")
    public void setStartPeriod(String startPeriod) {
        this.startPeriod = startPeriod;
    }
    @JsonProperty("endPeriod")
    public String getEndPeriod() {
        return endPeriod;
    }
    @JsonProperty("endPeriod")
    public void setEndPeriod(String endPeriod) {
        this.endPeriod = endPeriod;
    }
}
