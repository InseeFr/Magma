package fr.insee.rmes.model.datasets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(onConstructor_={@JsonCreator})
public class SpatialTemporal {
    @JsonValue
    private final String spatialTemporal;

}
