package fr.insee.rmes.controller;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Disabled
@ExtendWith(MockitoExtension.class)  // initialise les classes et mocks avant d'exécuter les tests
@MockitoSettings(strictness = Strictness.LENIENT)
class PoguesResourcesTest {

    @InjectMocks //
    private PoguesResources poguesResources;

    @Mock //
    private JSONArray operationsList;

    @Test
    @Disabled
    void getAllSeriesListsTest() throws RmesException, IOException {
        System.out.println(ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build());
        PoguesResources p = new PoguesResources();
//        PoguesServices pp = p.poguesServices;
      //  String toto = p.getAllSeriesLists(false);
       // System.out.println(ResponseEntity.status(HttpStatus.SC_OK).body(toto));
    }

    @Test
    void getCodeListTest() {
    }

    @Test
    void getOperationsBySerieTest() {
    }

    @Test
    void getOperationByCodeTest() {
    }
}