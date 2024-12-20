package fr.insee.rmes.services.utils;

public class CodeListUtilsTest {
    public static final String CODELIST_GRAPH = "CODELIST_GRAPH";
    public static final String CODELIST_WITH_STATUT_VALIDATION = "{\"prefLabelLg1\":\"Fréquence\",\"prefLabelLg2\":\"Frequency\",\"statutValidation\":\"Unpublished\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_WITH_STATUT_VALIDATION_EXPECTED = "{\"codes\":[{\"code\":\"B\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"}]},{\"code\":\"Q\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Trimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Trimestrielle\"}]},{\"code\":\"L\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Infra-annuelle\"},{\"langue\":\"en\",\"contenu\":\"Infra-annuelle\"}]},{\"code\":\"A\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Annuelle\"},{\"langue\":\"en\",\"contenu\":\"Annuelle\"}]},{\"code\":\"M\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Mensuelle\"},{\"langue\":\"en\",\"contenu\":\"Mensuelle\"}]},{\"code\":\"S\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Semestrielle\"},{\"langue\":\"en\",\"contenu\":\"Semestrielle\"}]},{\"code\":\"P\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Ponctuelle ou apériodique\"},{\"langue\":\"en\",\"contenu\":\"Ponctuelle ou apériodique\"}]},{\"code\":\"I\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bisannuelle\"},{\"langue\":\"en\",\"contenu\":\"Bisannuelle\"}]},{\"code\":\"N\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les minutes\"},{\"langue\":\"en\",\"contenu\":\"Toutes les minutes\"}]},{\"code\":\"C\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Continue\"},{\"langue\":\"en\",\"contenu\":\"Continue\"}]},{\"code\":\"T\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Bimestrielle\"}]},{\"code\":\"W\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Hebdomadaire\"},{\"langue\":\"en\",\"contenu\":\"Hebdomadaire\"}]},{\"code\":\"D\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne\"}]},{\"code\":\"H\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les heures\"},{\"langue\":\"en\",\"contenu\":\"Toutes les heures\"}]},{\"code\":\"U\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Pluriannuelle\"},{\"langue\":\"en\",\"contenu\":\"Pluriannuelle\"}]},{\"code\":\"A3\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Triannuelle\"},{\"langue\":\"en\",\"contenu\":\"Triannuelle\"}]}],\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public static final String CODELIST_WITHOUT_STATUT_VALIDATION_WITHOUT_CODE = "{\"prefLabelLg1\":\"Fréquence\",\"prefLabelLg2\":\"Frequency\",\"id\":\"CL_TEST\"}";
    public static final String CODELIST_WITHOUT_STATUT_VALIDATION_EXPECTED = "{\"codes\":[{\"code\":\"B\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne \\u2013 jours ouvrés\"}]},{\"code\":\"Q\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Trimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Trimestrielle\"}]},{\"code\":\"L\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Infra-annuelle\"},{\"langue\":\"en\",\"contenu\":\"Infra-annuelle\"}]},{\"code\":\"A\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Annuelle\"},{\"langue\":\"en\",\"contenu\":\"Annuelle\"}]},{\"code\":\"M\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Mensuelle\"},{\"langue\":\"en\",\"contenu\":\"Mensuelle\"}]},{\"code\":\"S\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Semestrielle\"},{\"langue\":\"en\",\"contenu\":\"Semestrielle\"}]},{\"code\":\"P\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Ponctuelle ou apériodique\"},{\"langue\":\"en\",\"contenu\":\"Ponctuelle ou apériodique\"}]},{\"code\":\"I\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bisannuelle\"},{\"langue\":\"en\",\"contenu\":\"Bisannuelle\"}]},{\"code\":\"N\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les minutes\"},{\"langue\":\"en\",\"contenu\":\"Toutes les minutes\"}]},{\"code\":\"C\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Continue\"},{\"langue\":\"en\",\"contenu\":\"Continue\"}]},{\"code\":\"T\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Bimestrielle\"},{\"langue\":\"en\",\"contenu\":\"Bimestrielle\"}]},{\"code\":\"W\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Hebdomadaire\"},{\"langue\":\"en\",\"contenu\":\"Hebdomadaire\"}]},{\"code\":\"D\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Quotidienne\"},{\"langue\":\"en\",\"contenu\":\"Quotidienne\"}]},{\"code\":\"H\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Toutes les heures\"},{\"langue\":\"en\",\"contenu\":\"Toutes les heures\"}]},{\"code\":\"U\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Pluriannuelle\"},{\"langue\":\"en\",\"contenu\":\"Pluriannuelle\"}]},{\"code\":\"A3\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Triannuelle\"},{\"langue\":\"en\",\"contenu\":\"Triannuelle\"}]}],\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public static final String CODELIST_WITHOUT_CODE_EXPECTED = "{\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public static final String CODELIST_DATASET = "{\"prefLabelLg1\":\"Fréquence\",\"prefLabelLg2\":\"Frequency\",\"statutValidation\":\"Unpublished\",\"id\":\"CL_TEST\"}";
    public static final String RDF_OUTPUT_EXPECTED = """
            [
              {
                "notation": "ISO-639",
                "statutValidation": "Publiée"
              },
              {
                "notation": "CL_SURVEY_UNIT"
              },
              {
                "notation": "CL_SURVEY_STATUS"
              },
              {
                "notation": "CL_FREQ"
              },
              {
                "notation": "CL_COLLECTION_MODE"
              },
              {
                "notation": "CL_SOURCE_CATEGORY"
              }
            ]
            """;
    public static final String RDF_OUTPUT = """
            [
              {
                "notation": "ISO-639",
                "statutValidation": "Validated"
              },
              {
                "notation": "CL_SURVEY_UNIT"
              },
              {
                "notation": "CL_SURVEY_STATUS"
              },
              {
                "notation": "CL_FREQ"
              },
              {
                "notation": "CL_COLLECTION_MODE"
              },
              {
                "notation": "CL_SOURCE_CATEGORY"
              }
            ]
            """;
    public static final String CODES = "[{\"code\":\"B\",\"prefLabelLg1\":\"Quotidienne \\u2013 jours ouvrés\",\"prefLabelLg2\":\"Quotidienne \\u2013 jours ouvrés\",\"uri\":\"http://bauhaus/codes/frequence/B\"},{\"code\":\"Q\",\"prefLabelLg1\":\"Trimestrielle\",\"prefLabelLg2\":\"Trimestrielle\",\"uri\":\"http://bauhaus/codes/frequence/Q\"},{\"code\":\"L\",\"prefLabelLg1\":\"Infra-annuelle\",\"prefLabelLg2\":\"Infra-annuelle\",\"uri\":\"http://bauhaus/codes/frequence/L\"},{\"code\":\"A\",\"prefLabelLg1\":\"Annuelle\",\"prefLabelLg2\":\"Annuelle\",\"uri\":\"http://bauhaus/codes/frequence/A\"},{\"code\":\"M\",\"prefLabelLg1\":\"Mensuelle\",\"prefLabelLg2\":\"Mensuelle\",\"uri\":\"http://bauhaus/codes/frequence/M\"},{\"code\":\"S\",\"prefLabelLg1\":\"Semestrielle\",\"prefLabelLg2\":\"Semestrielle\",\"uri\":\"http://bauhaus/codes/frequence/S\"},{\"code\":\"P\",\"prefLabelLg1\":\"Ponctuelle ou apériodique\",\"prefLabelLg2\":\"Ponctuelle ou apériodique\",\"uri\":\"http://bauhaus/codes/frequence/P\"},{\"code\":\"I\",\"prefLabelLg1\":\"Bisannuelle\",\"prefLabelLg2\":\"Bisannuelle\",\"uri\":\"http://bauhaus/codes/frequence/I\"},{\"code\":\"N\",\"prefLabelLg1\":\"Toutes les minutes\",\"prefLabelLg2\":\"Toutes les minutes\",\"uri\":\"http://bauhaus/codes/frequence/N\"},{\"code\":\"C\",\"prefLabelLg1\":\"Continue\",\"prefLabelLg2\":\"Continue\",\"uri\":\"http://bauhaus/codes/frequence/C\"},{\"code\":\"T\",\"prefLabelLg1\":\"Bimestrielle\",\"prefLabelLg2\":\"Bimestrielle\",\"uri\":\"http://bauhaus/codes/frequence/T\"},{\"code\":\"W\",\"prefLabelLg1\":\"Hebdomadaire\",\"prefLabelLg2\":\"Hebdomadaire\",\"uri\":\"http://bauhaus/codes/frequence/W\"},{\"code\":\"D\",\"prefLabelLg1\":\"Quotidienne\",\"prefLabelLg2\":\"Quotidienne\",\"uri\":\"http://bauhaus/codes/frequence/D\"},{\"code\":\"H\",\"prefLabelLg1\":\"Toutes les heures\",\"prefLabelLg2\":\"Toutes les heures\",\"uri\":\"http://bauhaus/codes/frequence/H\"},{\"code\":\"U\",\"prefLabelLg1\":\"Pluriannuelle\",\"prefLabelLg2\":\"Pluriannuelle\",\"uri\":\"http://bauhaus/codes/frequence/U\"},{\"code\":\"A3\",\"prefLabelLg1\":\"Triannuelle\",\"prefLabelLg2\":\"Triannuelle\",\"uri\":\"http://bauhaus/codes/frequence/A3\"}]";
    public static final String CODES_FOR_DATASET_EXPECTED = "{\"codes\":[],\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"CL_TEST\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"Fréquence\"},{\"langue\":\"en\",\"contenu\":\"Frequency\"}]}";
    public static final String EMPTY_ARRAY = "[]";
}
