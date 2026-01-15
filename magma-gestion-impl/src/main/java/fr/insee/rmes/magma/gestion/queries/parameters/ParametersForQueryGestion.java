package fr.insee.rmes.magma.gestion.queries.parameters;

import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;
import fr.insee.rmes.magma.queries.parameters.ParametersForQuery;

import java.lang.reflect.RecordComponent;

public interface ParametersForQueryGestion <E extends Record & ParametersForQuery<E>> extends ParametersForQuery<E> {

        default ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent){
            return ParameterValueDecoderGestion.of(recordComponent.getType());
        }


    }

