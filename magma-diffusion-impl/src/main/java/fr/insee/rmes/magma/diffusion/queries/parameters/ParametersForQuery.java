package fr.insee.rmes.magma.diffusion.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.ReflectionUtils.invokeMethod;

public interface ParametersForQuery<E extends Record & ParametersForQuery<E>> {
    default Map<String, Object> toParameters(){
        RecordComponent[] recordComponents = this.getClass().getRecordComponents();
        if (recordComponents ==null){
            throw new RuntimeException("Class " + this.getClass().getName() + " has no record components");
        }
        return Arrays.stream(recordComponents)
                        .collect(Collectors.toMap(
                                RecordComponent::getName,
                                this::decodeValue
                        ));
    }

    private String decodeValue(RecordComponent recordComponent) {
        ParameterValueDecoder<Object> decoder = (ParameterValueDecoder<Object>) findParameterValueDecoder(recordComponent);
        return decoder.decode(value(recordComponent));
    }

    private Object value(RecordComponent recordComponent){
        return invokeMethod(recordComponent.getAccessor(), this);
    }

    default ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent){
        return ParameterValueDecoder.of(recordComponent.getType());
    }


}
