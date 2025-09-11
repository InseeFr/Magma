package fr.insee.rmes.metadata.api.testcontainers.queries;


import fr.insee.rmes.metadata.api.GeoArrondissementMunipalEndpoints;
import fr.insee.rmes.metadata.api.GeoDepartementEndpoints;
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


public class GeoArrondissementMunicipalQueriesTest extends TestcontainerTest {

    @Autowired
    GeoArrondissementMunipalEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/13202/ascendants renvoie 11 ascendants

    /////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385?date=2025-09-04

//    geo/arrondissementMunicipal/69392?date=2025-09-04 renvoie 404


    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementsMunicipaux                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementsMunicipaux?date=2025-09-04//


//    geo/arrondissementsMunicipaux?date=*

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/precedents renvoie l’arrondissement municipal de Lyon 5e

//    geo/arrondissementMunicipal/69385/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_ArrondissementMunicipalCodePrecedents_code69385_date19450101() throws Exception{
        mockMvc.perform(get("/geo/arrondissementMunicipal/69385/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les arrondissements de Lyon 5e et Lyon 9e

    ////////////////////////////////////////////////////////////////////
    ///        geo/arrondissementMunicipal/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/arrondissementMunicipal/69385/suivants?date=1960-01-01 renvoie 2 arrondissements municipaux

}
