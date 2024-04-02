package fr.insee.rmes.services.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StructuresImplTest {
    public static final String SLICE_STRUCTURE = """
        [
            {
                "componentIds":"d0996,d0997",
                "prefLabelLg1":"Séries temporelles",
                "prefLabelLg2":"Time series",
                "sliceKey":"http://bauhaus/structuresDeDonnees/structure/dsd0995/sk1000",
                "typeSliceKey":"TS"
            },
            {
                "componentIds":"d0996,d0995",
                "prefLabelLg1":"Slice par région",
                "prefLabelLg2":"Slice by area",
                "sliceKey":"http://bauhaus/structuresDeDonnees/structure/dsd0995/sk1001"
            }
        ]
        """;
    public final String EXPECTED_SLICE_STRUCTURE = """
            [
                {
                    "composants":[
                        "d0996",
                        "d0997"
                    ],
                    "label":[
                        {
                            "langue":"fr",
                            "contenu":"Séries temporelles"
                        },
                        {
                            "langue":"en",
                            "contenu":"Time series"
                        }
                    ],
                    "type":"TS"
                },
                {
                    "composants":[
                        "d0996",
                        "d0995"
                    ],
                    "label":
                        [
                            {"langue":"fr",
                            "contenu":"Slice par région"
                            },
                            {
                            "langue":"en",
                            "contenu":"Slice by area"
                            }
                        ]
                    }
                ]
            """
            ;

    @Mock
    RepositoryGestion repoGestion;
    @Mock
    Config config;
    @InjectMocks
    StructuresImpl structuresImpl =new StructuresImpl(new FreeMarkerUtilsStub());
    public static final ObjectMapper MAPPER = new JsonMapper();

    @BeforeAll
    static void setUp(){
        Config.LG1="fr";
        Config.LG2="en";
        Config.BASE_GRAPH="http://rdf.insee.fr/graphes/";
        Config.STRUCTURES_GRAPH="structures";
        Config.STRUCTURES_COMPONENTS_GRAPH="composants";
        Config.CODELIST_GRAPH="codes";
    }

    @Test
    void getStructureWithSliceKeys_shouldReturnExpectedStructure() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(SLICE_STRUCTURE);
        when(repoGestion.getResponseAsArray("getStructureSliceKeys.ftlh")).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(structuresImpl.getSlice("1"))).isEqualTo(MAPPER.readTree(EXPECTED_SLICE_STRUCTURE.toString()));
    }
}
