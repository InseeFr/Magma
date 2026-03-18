package fr.insee.rmes.magma.diffusion.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.ReflectionUtils.invokeMethod;

public interface ParametersForQuery<E extends Record & ParametersForQuery<E>> {
    default Map<String, Object> toParameters(){
        RecordComponent[] recordComponents = this.getClass().getRecordComponents();
        if (recordComponents == null){
            throw new RuntimeException("Class " + this.getClass().getName() + " has no record components");
        }
        Map<String, Object> result = new HashMap<>();
        for (RecordComponent rc : recordComponents) {
            String value = decodeValue(rc);
            if (value != null) {
                result.put(rc.getName(), value);
            }
        }
        return result;
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
