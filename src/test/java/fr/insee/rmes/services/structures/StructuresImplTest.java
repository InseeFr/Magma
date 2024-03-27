package fr.insee.rmes.services.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.persistence.RepositoryGestion;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StructuresImplTest {
    @InjectMocks
    StructuresImpl structuresImpl=new StructuresImpl(new FreeMarkerUtilsStub());
    @Mock
    RepositoryGestion repoGestion;
    @Mock
    Config config;
    public static final String EMPTY_STRUCTURE = "[]";
    public static final String EMPTY_COMPONENT = "{}";
    @BeforeAll
    static void setUp(){
        Config.LG1="fr";
        Config.LG2="en";
        Config.BASE_GRAPH="http://rdf.insee.fr/graphes/";
        Config.STRUCTURES_GRAPH="structures";
    }

    @Test
    void getStructureDateMAJ_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(EMPTY_STRUCTURE);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->structuresImpl.getStructureDateMAJ("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent structure identifier");
    }

    @Test
    void getStructureDateMAJFalse_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONArray mockJSON = new JSONArray(EMPTY_STRUCTURE);
        when(repoGestion.getResponseAsArray(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->structuresImpl.getStructure("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent structure identifier");
    }

    @Test
    void getComponentDateMAJ_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(EMPTY_COMPONENT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->structuresImpl.getComponentDateMAJ("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent component identifier");
    }

    @Test
    void getComponentDateMAJFalse_shouldReturn404IfInexistentId() throws RmesException, JsonProcessingException {
        JSONObject mockJSON = new JSONObject(EMPTY_COMPONENT);
        when(repoGestion.getResponseAsObject(Mockito.anyString())).thenReturn(mockJSON);

        assertThatThrownBy(()->structuresImpl.getComponent("1")).isInstanceOf(RmesException.class)
                .matches(rmesException->((RmesException)rmesException).getStatus()==404)
                .hasMessageContaining("Non existent component identifier");
    }


}
