package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneDelegueeEndpoints;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")


public class GeoCommuneDelegueeQueriesTest {

    @Autowired
    GeoCommuneDelegueeEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/communeDeleguee/{code}/ascendants        ///
    /////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////
    ///        geo/communeDeleguee/{code}                     ///
    /////////////////////////////////////////////////////////////////////

//    geo/communeDeleguee/69385?date=2025-09-04

//    geo/communeDeleguee/69392?date=2025-09-04 renvoie 404



    ////////////////////////////////////////////////////////////////////
    ///        geo/collectivitesDOutreMer                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/communesDeleguees?date=2025-09-04//


//    geo/communesDeleguees?date=*




}
