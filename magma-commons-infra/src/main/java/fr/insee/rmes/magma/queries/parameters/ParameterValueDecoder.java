package fr.insee.rmes.magma.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;
import java.util.function.Function;

@FunctionalInterface
public interface ParameterValueDecoder<T> {


    String decode(T value);

    /**
     * Class for custom decoder in {@link ParametersForQuery} records such as {@link AscendantsDescendantsRequestParametizer} which
     * need to customize decoder for a type which has yet a standard decoder in the children of ParameterValueDecoder
     * <p>
     * For example {@link AscendantsDescendantsRequestParametizer} needs a custom treatment for Strings for attribute <code>filtreNom</code>.
     * So it overrides the method {@link  ParametersForQuery#findParameterValueDecoder(RecordComponent)} and for the attribute
     * <code>filtreNom</code>, it returns its custom decoder with this kind of code :
     * <code>
     * if ("filtreNom".equals(recordComponent.getName())){
     * return new ParameterValueDecoder.DelegaterDecoder<String>(value -> value ==null?"*": value);
     * }
     * </code>
     * <p>
     * The DelegaterDecoder is never be returned by
     * { @link fr.insee.rmes.magma.diffusion.queries.parameters.ParameterValueDecoder#of(java.lang.Class)} and can only be
     * used when explicitly instanced in a method such as {@link AscendantsDescendantsRequestParametizer#findParameterValueDecoder(RecordComponent)}
     *
     * @param delegatedDecoder : a function applied to decode the value
     * @param <U>              : the type for which the instance will decode
     */
    record DelegaterDecoder<U>(Function<U, String> delegatedDecoder) implements ParameterValueDecoder<U> {
        @Override
        public String decode(U value) {
            return delegatedDecoder.apply(value);
        }
    }

}
