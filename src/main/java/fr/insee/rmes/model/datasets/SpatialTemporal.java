package fr.insee.rmes.model.datasets;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class SpatialTemporal {

    @NonNull
    private final String spatialTemporal;

}
