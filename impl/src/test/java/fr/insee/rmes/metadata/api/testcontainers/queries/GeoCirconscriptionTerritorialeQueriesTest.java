package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoArrondissementMunipalEndpoints;
import fr.insee.rmes.metadata.api.GeoCirconscriptionTerritorialeEndpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoCirconscriptionTerritorialeQueriesTest {

    @Autowired
    GeoCirconscriptionTerritorialeEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/circonscriptionTerritoriale/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/circonscriptionTerritoriale/13202/ascendants renvoie 11 ascendants

    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/circonscriptionTerritoriale/69385?date=2025-09-04

//    geo/circonscriptionTerritoriale/69392?date=2025-09-04 renvoie 404



}
