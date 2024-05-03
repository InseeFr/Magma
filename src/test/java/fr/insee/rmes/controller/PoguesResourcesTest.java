package fr.insee.rmes.controller;

import org.json.JSONArray;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@ExtendWith(MockitoExtension.class)  // initialise les classes et mocks avant d'exécuter les tests
@MockitoSettings(strictness = Strictness.LENIENT)
class PoguesResourcesTest {

    @InjectMocks //
    private PoguesResources poguesResources;

    @Mock //
    private JSONArray operationsList;

    @Test
    void getallCodesLists() {
    }

    @Test
    void getCodeList() {
    }

    @Test
    void getOperationsBySerie() {
    }

    @Test
    void getOperationByCode() {
    }
}