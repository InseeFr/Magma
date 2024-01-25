package fr.insee.rmes.services.codelists;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CodeListImplTest {

    //    écriture en Java 17 (pas compatible en Java11)
//    public static final String RDF_OUTPUT_EXPECTED = """
//            [
//              {
//                "notation": "ISO-639",
//                "statutValidation": "Validated"
//              },
//              {
//                "notation": "CL_SURVEY_UNIT"
//              },
//              {
//                "notation": "CL_SURVEY_STATUS"
//              },
//              {
//                "notation": "CL_FREQ"
//              },
//              {
//                "notation": "CL_COLLECTION_MODE"
//              },
//              {
//                "notation": "CL_SOURCE_CATEGORY"
//              }
//            ]
//            """;

    public static final String RDF_OUTPUT_EXPECTED = " [\n" +
            "              {\n" +
            "                \"notation\": \"ISO-639\",\n" +
            "                \"statutValidation\": \"Publiée\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"notation\": \"CL_SURVEY_UNIT\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"notation\": \"CL_SURVEY_STATUS\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"notation\": \"CL_FREQ\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"notation\": \"CL_COLLECTION_MODE\"\n" +
            "              },\n" +
            "              {\n" +
            "                \"notation\": \"CL_SOURCE_CATEGORY\"\n" +
            "              }\n" +
            "            ]";


    @InjectMocks
    CodeListImpl codeList = new CodeListImpl();
    @Mock
    RepositoryGestion repoGestion;
    public static final ObjectMapper MAPPER = new JsonMapper();

    @Test
    void getAllCodesLists_shouldReturnList() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(RDF_OUTPUT_EXPECTED);

        Mockito.when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(codeList.getAllCodesLists())).isEqualTo(MAPPER.readTree(RDF_OUTPUT_EXPECTED));

    }
}
