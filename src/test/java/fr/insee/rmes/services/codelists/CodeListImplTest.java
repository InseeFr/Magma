package fr.insee.rmes.services.codelists;

import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@JsonTest(properties = {
        "fr.insee.rmes.magma.force.ssl=false",
})
@Import({CodeListImpl.class, RepositoryGestion.class, Config.class})
class CodeListImplTest {

    @MockBean
    private RepositoryGestion repositoryGestion;

    @Autowired
    private CodeListImpl codeListImpl;

    @Test
    void getCodesListTest() throws RmesException {
        // # Mocker l'appel repoGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params));
        // retour de buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params)
        var argument = "TODO : retour de buildRequest(Constants.CODELISTS_QUERIES_PATH,\"getCodesList.ftlh\", params)";
        // TODO capturer le retour de repositoryGestion.getResponseAsObject(buildRequest(Constants.CODELISTS_QUERIES_PATH,"getCodesList.ftlh", params)
        JSONObject jsonResult = null;
        when(repositoryGestion.getResponseAsObject(argument)).thenReturn(jsonResult);
        var notation = "TODO : mettre une valeur pertinente";
        // TODO capturer le retour de getCodesList(notation) et le mettre dans la var attendu :
        var attendu = "";
        assertEquals(attendu, codeListImpl.getCodesList(notation));

    }

    @Configuration
    @Import({RepositoryGestion.class, CodeListImpl.class})
    public class TestConfig {
    }

}