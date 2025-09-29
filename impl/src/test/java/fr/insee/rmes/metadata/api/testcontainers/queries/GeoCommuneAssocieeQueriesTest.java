package fr.insee.rmes.metadata.api.testcontainers.queries;

import fr.insee.rmes.metadata.api.GeoCommuneAssocieeEndpoints;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")


public class GeoCommuneAssocieeQueriesTest {
    
        @Autowired
        GeoCommuneAssocieeEndpoints endpoints;
        @Autowired
        private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///        geo/communeAssociee/{code}/ascendants        ///
    /////////////////////////////////////////////////////////////////////


        /////////////////////////////////////////////////////////////////////
        ///        geo/communeAssociee/{code}                     ///
        /////////////////////////////////////////////////////////////////////

//    geo/communeAssociee/69385?date=2025-09-04

//    geo/communeAssociee/69392?date=2025-09-04 renvoie 404



        ////////////////////////////////////////////////////////////////////
        ///        geo/collectivitesDOutreMer                         ///
        ////////////////////////////////////////////////////////////////////

//    geo/communesAssociees?date=2025-09-04//


//    geo/communesAssociees?date=*


    

}
