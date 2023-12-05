package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpatialTemporal {
    private String spatialTemporal;

    public SpatialTemporal(String spatialTemporal) {
        this.spatialTemporal = spatialTemporal;
    }
}
