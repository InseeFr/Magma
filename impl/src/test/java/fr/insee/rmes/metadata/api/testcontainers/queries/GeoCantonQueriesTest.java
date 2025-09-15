package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCantonEndpoints;
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

public class GeoCantonQueriesTest {

    @Autowired
    GeoCantonEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/13202/ascendants renvoie 11 ascendants

    /////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/canton/69385?date=2025-09-04

//    geo/canton/69392?date=2025-09-04 renvoie 404


    ////////////////////////////////////////////////////////////////////
    ///        geo/cantons                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/cantons?date=2025-09-04//


//    geo/cantons?date=*

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/precedents renvoie l’arrondissement municipal de Lyon 5e

//    geo/canton/69385/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_CantonCodePrecedents_code69385_date19450101() throws Exception{
        mockMvc.perform(get("/geo/canton/69385/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les cantons de Lyon 5e et Lyon 9e

    ////////////////////////////////////////////////////////////////////
    ///        geo/canton/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/canton/69385/suivants?date=1960-01-01 renvoie 2 cantons
}
