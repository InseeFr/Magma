package fr.insee.rmes.services.pogues;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.utils.PoguesUtilsTest;
import fr.insee.rmes.services.utils.ResponseUtilsTest;
import fr.insee.rmes.stubs.FreeMarkerUtilsStub;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PoguesImplTest {
    @InjectMocks
    PoguesImpl poguesImpl=new PoguesImpl(new FreeMarkerUtilsStub());
    @Mock
    RepositoryGestion repoGestion;
    public static final ObjectMapper MAPPER = new JsonMapper();
    @BeforeAll
    static void setUp(){
        Config.LG1="fr";
        Config.LG2="en";
    }

    @Test
    void getOperationsByCodeTest() throws IOException, RmesException {
        JSONObject mockJSON = new JSONObject(PoguesUtilsTest.OPERATION_EXAMPLE);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(poguesImpl.getOperationByCode("1"))).isEqualTo(MAPPER.readTree(PoguesUtilsTest.EXPECTED_OPERATION.toString()));
    }

    @Test
    void getOperationsBySerieIdTest() throws IOException, RmesException {
        JSONArray mockJSON = new JSONArray(PoguesUtilsTest.OPERATION_BY_SERIE);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(poguesImpl.getOperationsBySerieId("1"))).isEqualTo(MAPPER.readTree(PoguesUtilsTest.EXPECTED_OPERATION_BY_SERIE.toString()));
    }
    @Test
    void getSerieByIdTest() throws IOException, RmesException {
        JSONArray mockJSON = new JSONArray(PoguesUtilsTest.SERIES_EXAMPLE);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(poguesImpl.getSerieById("1"))).isEqualTo(MAPPER.readTree(PoguesUtilsTest.EXPECTED_SERIE.toString()));
    }
    @Test
    void getAllSeriesListsTest() throws IOException, RmesException {
        JSONArray mockJSON = new JSONArray(PoguesUtilsTest.SERIES_LIST);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);
        assertThat(MAPPER.readTree(poguesImpl.getAllSeriesLists(true))).isEqualTo(MAPPER.readTree(PoguesUtilsTest.EXPECTED_GET_SERIES_LIST.toString()));
    }

    @Test
    void getSerieById_shouldReturn404IfInexistentId() throws RmesException {
        JSONArray mockJSON = new JSONArray(ResponseUtilsTest.EMPTY_JSON_ARRAY);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->poguesImpl.getSerieById("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent series identifier");
    }

    @Test
    void getOperationByCode_shouldReturn404IfInexistentId() throws RmesException {
        JSONObject mockJSON = new JSONObject(ResponseUtilsTest.EMPTY_JSON_OBJECT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->poguesImpl.getOperationByCode("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent operation identifier");
    }



}