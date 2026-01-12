package fr.insee.rmes.magma.diffusion.queries.parameters;


import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;
import fr.insee.rmes.magma.queries.parameters.ParametersForQuery;

import java.lang.reflect.RecordComponent;

public interface ParametersForQueryDiffusion<E extends Record & ParametersForQuery<E>> extends ParametersForQuery<E> {

    default ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent){
        return ParameterValueDecoderDiffusion.of(recordComponent.getType());
    }


}
