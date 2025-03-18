package fr.insee.rmes.services.utils;

public class ResponseUtilsTest {
    public static final String EMPTY_JSON_OBJECT = "{}";
    public static final String EMPTY_JSON_ARRAY = "[]";
    public static final String FAKE_TOKEN = "fake_token";

    public static final String COLLECTION_JSON_OBJECT = "{\n" +
            "  \"uri\": \"http://bauhaus/concepts/collection/definitions-insee-fr\",\n" +
            "  \"date_mis_a_jour\": \"2024-11-01T00:00:00.000+01:00\",\n" +
            "  \"intitule_fr\": \"Concepts affichés sur insee.fr\",\n" +
            "  \"intitule_en\": \"Concepts displayed on insee.fr\",\n" +
            "  \"description_fr\": \"Ensemble des concepts affichés sur la page définitions du site insee.fr\",\n" +
            "  \"description_en\": \"All concepts displayed on the \\\"définitions\\\" section from the website insee.fr\",\n" +"}";

    public static final String COLLECTION_MODELSWAGGER = "{\"id\":\"definitions-insee-fr\",\"uri\":\"http://bauhaus/concepts/collection/definitions-insee-fr\",\"dateMiseAJour\":\"2024-11-01T00:00:00.000+01:00\"}";

    public static final String SET_OF_CONCEPTS_JSON_ARRAY ="[\n"+
            "      {\n" +
            "        \"uri_concept\" : {\n" +
            "          \"type\" : \"uri\",\n" +
            "          \"value\" : \"http://bauhaus/concepts/definition/c1365\"\n" +
            "        },\n" +
            "        \"id_concept\" : {\n" +
            "          \"type\" : \"literal\",\n" +
            "          \"value\" : \"c1365\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"uri_concept\" : {\n" +
            "          \"type\" : \"uri\",\n" +
            "          \"value\" : \"http://bauhaus/concepts/definition/c1632\"\n" +
            "        },\n" +
            "        \"id_concept\" : {\n" +
            "          \"type\" : \"literal\",\n" +
            "          \"value\" : \"c1632\"\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n";
    }