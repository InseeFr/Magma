package fr.insee.rmes.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.persistence.RepositoryGestion;
import fr.insee.rmes.services.structures.StructuresImpl;
import fr.insee.rmes.services.structures.stub.StructuresImplMockBuildRequest;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@JsonTest(properties = {"spring.profiles.active=test"})
@Import({Config.class, StructuresImplMockBuildRequest.class, RepositoryGestion.class})
class StructuresImplIntegrationIntegrationTest {

    @Autowired
    StructuresImpl structuresImplMockBuildRequest;

    @MockBean
    RepositoryGestion repositoryGestion;

    @Test
    void getStructureTest() throws RmesException, JsonProcessingException {

        Mockito.when(repositoryGestion.getResponseAsArray("getStructure.ftlh")).thenReturn(new JSONArray("[{\"dateMiseAJour\":\"2023-02-10T14:37:14.691014\",\"dateCreation\":\"2022-10-28T13:53:59.228157\",\"prefLabelLg1\":\"set\",\"notation\":\"est\",\"prefLabelLg2\":\"set\",\"statutValidation\":\"Unpublished\",\"id\":\"dsd1001\",\"uri\":\"http://bauhaus/structuresDeDonnees/structure/dsd1001\"}]"));
        Mockito.when(repositoryGestion.getResponseAsArray("getStructureComponents.ftlh")).thenReturn(new JSONArray("[{\"ordre\":\"1\",\"id\":\"d1001\"}]"));
        Mockito.when(repositoryGestion.getResponseAsObject("getComponent.ftlh")).thenReturn(new JSONObject("{\"dateMiseAJour\":\"2022-10-28T13:53:24.204248\",\"dateCreation\":\"2022-10-28T13:53:24.204248\",\"prefLabelLg1\":\"test_list_part-fr\",\"notation\":\"test_list_part\",\"prefLabelLg2\":\"test_list_part-en\",\"statutValidation\":\"Unpublished\",\"id\":\"d1001\",\"type\":\"Dimension\",\"uri\":\"http://bauhaus/structuresDeDonnees/composants/dimension/d1001\",\"representation\":\"http://bauhaus/codes/concept/CodeTestSuppressionCasAvecListePartielle\"}"));
        Mockito.when(repositoryGestion.getResponseAsArray("getComponentChildren.ftlh")).thenReturn(new JSONArray("[]"));

        String expected = "{\"dateMiseAJour\":\"2023-02-10T14:37:14.691014\",\"dateCreation\":\"2022-10-28T13:53:59.228157\",\"notation\":\"est\",\"statutValidation\":\"Provisoire, jamais publiée\",\"mesures\":[],\"id\":\"dsd1001\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"set\"},{\"langue\":\"en\",\"contenu\":\"set\"}],\"uri\":\"http://bauhaus/structuresDeDonnees/structure/dsd1001\",\"attributs\":[],\"dimensions\":[{\"ordre\":\"1\",\"notation\":\"test_list_part\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test_list_part-fr\"},{\"langue\":\"en\",\"contenu\":\"test_list_part-en\"}],\"uri\":\"http://bauhaus/structuresDeDonnees/composants/dimension/d1001\",\"representation\":\"http://bauhaus/codes/concept/CodeTestSuppressionCasAvecListePartielle\",\"dateMiseAJour\":\"2022-10-28T13:53:24.204248\",\"dateCreation\":\"2022-10-28T13:53:24.204248\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"d1001\",\"type\":\"Dimension\"}]}";
        assertEquals(expected,structuresImplMockBuildRequest.getStructure("dsd1001"),true);
    }


    @Test
    void getStructureComponentsTest(){
        String structure="{\"dateMiseAJour\":\"2023-02-10T14:37:14.691014\",\"dateCreation\":\"2022-10-28T13:53:59.228157\",\"notation\":\"est\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"dsd1001\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"set\"},{\"langue\":\"en\",\"contenu\":\"set\"}],\"uri\":\"http://bauhaus/structuresDeDonnees/structure/dsd1001\"}";
        String id="dsd1001";
        String result="{\"dateMiseAJour\":\"2023-02-10T14:37:14.691014\",\"dateCreation\":\"2022-10-28T13:53:59.228157\",\"notation\":\"est\",\"statutValidation\":\"Provisoire, jamais publiée\",\"mesures\":[],\"id\":\"dsd1001\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"set\"},{\"langue\":\"en\",\"contenu\":\"set\"}],\"uri\":\"http://bauhaus/structuresDeDonnees/structure/dsd1001\",\"attributs\":[],\"dimensions\":[{\"dateMiseAJour\":\"2022-10-28T13:53:24.204248\",\"dateCreation\":\"2022-10-28T13:53:24.204248\",\"ordre\":\"1\",\"notation\":\"test_list_part\",\"statutValidation\":\"Provisoire, jamais publiée\",\"id\":\"d1001\",\"label\":[{\"langue\":\"fr\",\"contenu\":\"test_list_part-fr\"},{\"langue\":\"en\",\"contenu\":\"test_list_part-en\"}],\"type\":\"Dimension\",\"uri\":\"http://bauhaus/structuresDeDonnees/composants/dimension/d1001\",\"representation\":\"http://bauhaus/codes/concept/CodeTestSuppressionCasAvecListePartielle\"}]}";
    }



}
