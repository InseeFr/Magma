package fr.insee.rmes.magma.diffusion.queries.parameters;

import fr.insee.rmes.magma.diffusion.model.Iris;
import fr.insee.rmes.magma.queries.parameters.ParameterValueDecoder;

import java.lang.reflect.RecordComponent;
import java.time.LocalDate;

public record TerritoireRequestParametizer(String code,
                                           LocalDate date,
                                           Class<?> typeOrigine,
                                           String filtreNom,
                                           String chefLieu,
                                           boolean com) implements ParametersForQueryDiffusion<TerritoireRequestParametizer> {

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

    //for geo/iris
    public TerritoireRequestParametizer(LocalDate date,
                                        boolean com) {
        this("none", date, Iris.class , "*", "*", com);//regardless of the values of typeOrigine, filtreNom et chefLieu
    }

    //for geo/iris/{code}
    public TerritoireRequestParametizer(String code, LocalDate date) {
        this(code, date, Iris.class, "*", "*", true);
        //regardless of the values of typeOrigine, filtreNom, chefLieu and com, + date for hasIrisDescendant
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
        return ParametersForQueryDiffusion.super.findParameterValueDecoder(recordComponent);
    }


}