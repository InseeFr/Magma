package fr.insee.rmes.services.utils;

public class StructuresUtilsTest {
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
    public static final String EXPECTED_SLICE_STRUCTURE = """
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

}
