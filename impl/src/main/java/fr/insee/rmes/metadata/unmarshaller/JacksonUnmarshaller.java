package fr.insee.rmes.metadata.unmarshaller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@Component
@Slf4j
// TODO proposer une autre implémentation d'unmarshaller ?
public record JacksonUnmarshaller(CsvMapper csvMapper) implements Unmarshaller {

    public JacksonUnmarshaller() {
        this(CsvMapper.csvBuilder().enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .addModule(enumModule(TerritoireBase.TypeArticleEnum.class, TerritoireBase.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(TerritoireTousAttributs.TypeArticleEnum.class, TerritoireTousAttributs.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(TerritoireBaseChefLieu.TypeArticleEnum.class, TerritoireBaseChefLieu.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(AireDAttractionDesVilles2020.TypeArticleEnum.class, AireDAttractionDesVilles2020.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Arrondissement.TypeArticleEnum.class, Arrondissement.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(ArrondissementMunicipal.TypeArticleEnum.class, ArrondissementMunicipal.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(BassinDeVie2022.TypeArticleEnum.class, BassinDeVie2022.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Canton.TypeArticleEnum.class, Canton.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(CantonOuVille.TypeArticleEnum.class, CantonOuVille.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(CirconscriptionTerritoriale.TypeArticleEnum.class, CirconscriptionTerritoriale.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(CollectiviteDOutreMer.TypeArticleEnum.class, CollectiviteDOutreMer.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Commune.TypeArticleEnum.class, Commune.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(CommuneAssociee.TypeArticleEnum.class, CommuneAssociee.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(CommuneDeleguee.TypeArticleEnum.class, CommuneDeleguee.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(District.TypeArticleEnum.class, District.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Departement.TypeArticleEnum.class, Departement.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Intercommunalite.TypeArticleEnum.class, Intercommunalite.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(Region.TypeArticleEnum.class, Region.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(UniteUrbaine2020.TypeArticleEnum.class, UniteUrbaine2020.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(enumModule(ZoneDEmploi2020.TypeArticleEnum.class, ZoneDEmploi2020.TypeArticleEnum._0_CHARNIERE_DE_))
                .addModule(new JavaTimeModule())
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build());
    }


    private static <E extends Enum<E>>  Module enumModule(Class<E> enumClass, E defaultValue) {
        var module = new SimpleModule();
        module.addDeserializer(enumClass, new JsonDeserializer<>()  {
            @Override
            public E deserialize(JsonParser parser, DeserializationContext ctxt) {
                try {
                    return enumClass.getEnumConstants()[Integer.parseInt(parser.getValueAsString())];
                } catch (NumberFormatException | IOException e) {
                    return defaultValue;
                }
            }
        });
        return module;
    }

    @Override
    public <G> Optional<G> unmarshal(@NonNull Csv csv, @NonNull Class<G> targetClass) {
        return unmarshallAll(csv.content(), targetClass, Optional.empty(), l-> l.isEmpty()?Optional.empty():Optional.of(l.getFirst()));
    }

    @Override
    public <G> G unmarshalOrNull(@NonNull Csv csv, @NonNull Class<G> targetClass) {
        return unmarshallAll(csv.content(), targetClass, null,
                l -> l.isEmpty() ? null : l.getFirst());
    }

    @Override
    public <G> List<G> unmarshalList(@NonNull Csv csv, @NonNull Class<G> targetClass) {
        return unmarshallAll(csv.content(), targetClass, List.of(), Function.identity());
    }

    private <R, G> R unmarshallAll(String csv, Class<G> targetClass, R resultEmpty, Function<List<G>, R> extractResults){
        log.atDebug().log(() -> "Deserialize for "+findReturned(targetClass, resultEmpty)
                        +". CSV header is "+ csv.lines().limit(1).findFirst().orElse(null));
        CsvSchema schema = CsvSchema.emptySchema()
                .withHeader()
                .withNullValue("");
        ObjectReader reader = csvMapper.readerFor(targetClass).with(schema);
        List<G> results;
        try (MappingIterator<G> mappingIterator = reader.readValues(csv)) {
            results = mappingIterator.readAll();
        } catch (IOException e) {
            log.error("While reading \n{}\nMESSAGE : {}\n===> RETURN WILL BE EMPTY", csv, e.getMessage());
            return resultEmpty;
        }
        return extractResults.apply(results);
    }

    private <G, R> String findReturned(Class<G> targetClass, R resultEmpty) {
        return switch (resultEmpty){
            case List<?> ignored -> "List<"+targetClass.getName()+">";
            case Optional<?> ignored -> targetClass.getName();
            default -> "Unknown wrapper for "+targetClass.getName();
        };
    }
}
