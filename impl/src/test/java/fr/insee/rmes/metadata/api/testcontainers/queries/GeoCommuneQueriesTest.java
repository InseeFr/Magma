package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoArrondissementMunipalEndpoints;
import fr.insee.rmes.metadata.api.GeoCommuneEndpoints;
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


public class GeoCommuneQueriesTest {

    @Autowired
    GeoCommuneEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/commune/{code}/ascendants          ///
    /////////////////////////////////////////////////////////////////////

//    geo/commune/13202/ascendants renvoie 11 ascendants

    /////////////////////////////////////////////////////////////////////
    ///        geo/commune/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/commune/69385?date=2025-09-04

//    geo/commune/69392?date=2025-09-04 renvoie 404


    ////////////////////////////////////////////////////////////////////
    ///        geo/communes                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/communes?date=2025-09-04//


//    geo/communes?date=*


    ////////////////////////////////////////////////////////////////////
    ///                     geo/commune/{code}/descendants           ///
    ////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////
    ///        geo/commune/{code}/precedents         ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/precedents renvoie l’arrondissement municipal de Lyon 5e

//    geo/commune/69385/precedents?date=1945-01-01  renvoie 404
    @Test
    void should_return_404_when_communeCodePrecedents_code69385_date19450101() throws Exception{
        mockMvc.perform(get("/geo/commune/69385/precedents")
                        .param("date", "1945-01-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///        geo/commune/{code}/projetes           ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/projetes?date=1960-01-01&dateProjection=2011-12-31: renvoie les arrondissements de Lyon 5e et Lyon 9e

    ////////////////////////////////////////////////////////////////////
    ///        geo/commune/{code}/suivants           ///
    ////////////////////////////////////////////////////////////////////

//    geo/commune/69385/suivants?date=1960-01-01 renvoie 2 arrondissements municipaux


}
