package fr.insee.rmes.metadata.queries.parameters;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record TerritoireRequestParametizer(String code,
                                           LocalDate date,
                                           Class<?> typeOrigine,
                                           String filtreNom,
                                           String chefLieu,
                                           boolean com) implements ParametersForQuery<TerritoireRequestParametizer> {

    //for geo/departement/{code} and geo/region/{code}
    public TerritoireRequestParametizer(String code,
                                        LocalDate date,
                                        Class<?> typeOrigine,
                                        String chefLieu) {
        this(code, date, typeOrigine, "*", chefLieu, true);
    }

    //for geo/departements
    public TerritoireRequestParametizer(LocalDate date,
                                        Class<?> typeOrigine,
                                        String chefLieu,
                                        boolean com) {
        this("none", date, typeOrigine, "*", chefLieu, com);
}

    //for geo/communes and geo/bassinsDeVie2022
    public TerritoireRequestParametizer(LocalDate date,
                                        Class<?> typeOrigine,
                                        String filtreNom,
                                        String chefLieu,
                                        boolean com) {
        this("none", date, typeOrigine, filtreNom, chefLieu, com);
    }

    //for geo/arrondissements, geo/aireDAttractionDesVilles2020, etc
    public TerritoireRequestParametizer(LocalDate date,
                                        Class<?> typeOrigine,
                                        String chefLieu) {
        this("none", date, typeOrigine, "*", chefLieu, true);
    }

    //for geo/iris/{code} (hasIrisDescendant)
    public TerritoireRequestParametizer(String code,
                                        Class<?> typeOrigine) {
        this(code, LocalDate.now(), typeOrigine, "*", "prefecture", true);
    }

    @Override
    public ParameterValueDecoder<?> findParameterValueDecoder(RecordComponent recordComponent) {
        return ParametersForQuery.super.findParameterValueDecoder(recordComponent);
    }


}