package fr.insee.rmes.magma.results;

import fr.insee.rmes.magma.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;

public record SingleResult<E>(E result) {
    public ResponseEntity<E> toResponseEntity() {
        return EndpointsUtils.toResponseEntity(result);
    }
}
