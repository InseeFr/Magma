package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.GeoPaysEndpoints;
import fr.insee.rmes.magma.diffusion.model.Pays;
import fr.insee.rmes.magma.diffusion.model.TerritoireTousAttributs;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

public class GeoPaysQueriesTest extends TestcontainerTest  {

    @Autowired
    GeoPaysEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    /////////////////////////////////////////////////////////////////////
    ///                geo/pays/{code}                                ///
    /////////////////////////////////////////////////////////////////////

//    geo/pays/99132?date=2025-09-04
    @Test
    void should_return_Pays99132_when_PaysCode_code99132_date20250904(){
        var response  = endpoints.getcogpays("99132", LocalDate.of(2025, 9, 4));
        var result = response.getBody();
        assertAll(
                () -> assertEquals("99132", result.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/pays/7c3380f3-897b-4470-a12f-2ae3b61fe4d0", result.getUri()),
                () -> assertEquals(Pays.TypeEnum.PAYS, result.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), result.getDateCreation()),
                () -> assertEquals("Royaume-Uni", result.getIntitule()),
                () -> assertEquals("Royaume-Uni de Grande-Bretagne et d’Irlande du Nord", result.getIntituleComplet()),
                () -> assertEquals("GB", result.getIso3166alpha2()),
                () -> assertEquals("GBR", result.getIso3166alpha3()),
                () -> assertEquals("826", result.getIso3166num())
        );
    }

    //    geo/pays/99000?date=2025-09-04
    @Test
    void should_return_404_when_PaysCode_code99000_date20250904() throws Exception{
        mockMvc.perform(get("/geo/pays/99000")
                        .param("date", "2025-09-01"))
                .andExpect(status().isNotFound());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/pays/descendants                    ///
    ////////////////////////////////////////////////////////////////////

//    geo/pays/99132/descendants?date=2025-09-04
    @Test
    void should_return_7_territoires_when_PaysCodeDescendants_code99132_date20250904_typeNull() {
        var response  = endpoints.getcogpaysdesc("99132", LocalDate.of(2025, 9, 4), null);
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(7, result.size());
        assertAll(
                () -> assertEquals("99133", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/territoire/1af281f6-f58a-4197-a40c-3c1514ebd9c5", resultItem1.getUri()),
                () -> assertEquals(TerritoireTousAttributs.TypeEnum.TERRITOIRE, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1964,9,21), resultItem1.getDateCreation()),
                () -> assertEquals("Territoires britanniques en Méditerranée", resultItem1.getIntitule()),
                () -> assertEquals("Gibraltar, Akrotiri et Dhekelia", resultItem1.getIntituleComplet())
        );

    }

//    geo/pays/99132/descendants?date=2025-09-04&type=Region - only Territoire is authorized for type
    @Test
    void should_return_400_when_PaysCodeDescendants_code99000_date20250904_typeRegion() throws Exception{
        mockMvc.perform(get("/geo/pays/99000/descendants")
                        .param("date", "2025-09-01")
                        .param("type", "Region"))
                .andExpect(status().isBadRequest());
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/pays                                    ///
    ////////////////////////////////////////////////////////////////////

//    geo/pays?date=2025-09-04
    @Test
    void should_return_196_pays_when_Pays_date20250904() {
        var response  = endpoints.getcogpayslist("2025-09-04");
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(196, result.size());
        assertAll(
                () -> assertEquals("99100", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/pays/b7e3f0c9-b653-4a3e-904a-de63b80e108b", resultItem1.getUri()),
                () -> assertEquals(Pays.TypeEnum.PAYS, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("France", resultItem1.getIntitule()),
                () -> assertEquals("République française", resultItem1.getIntituleComplet()),
                () -> assertEquals("FR", resultItem1.getIso3166alpha2()),
                () -> assertEquals("FRA", resultItem1.getIso3166alpha3()),
                () -> assertEquals("250", resultItem1.getIso3166num())
        );

    }

//    geo/pays?date=*
    @Test
    void should_return_343_pays_when_Pays_dateEtoile(){
        var response  = endpoints.getcogpayslist ("*");
        var result = response.getBody();
        var resultItem1= result.getFirst();

        assertAll(
                () -> assertEquals(343, result.size()),
                () -> assertEquals("99100", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/pays/b7e3f0c9-b653-4a3e-904a-de63b80e108b", resultItem1.getUri()),
                () -> assertEquals(Pays.TypeEnum.PAYS, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1943,1,1), resultItem1.getDateCreation()),
                () -> assertEquals("France", resultItem1.getIntitule()),
                () -> assertEquals("République française", resultItem1.getIntituleComplet()),
                () -> assertEquals("FR", resultItem1.getIso3166alpha2()),
                () -> assertEquals("FRA", resultItem1.getIso3166alpha3()),
                () -> assertEquals("250", resultItem1.getIso3166num())
        );
    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/pays/precedents                         ///
    ////////////////////////////////////////////////////////////////////

//    geo/pays/99309/descendants?date=1965-01-01
    @Test
    void should_return_2_pays_when_PaysCodePrecedents_code99309_date19650101() {
        var response  = endpoints.getcogpaysprec("99309", LocalDate.of(1965, 1, 1));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(2, result.size());
        assertAll(
                () -> assertEquals("99309", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/pays/aed778dc-3f9e-467d-a7c2-c875a12cb44d", resultItem1.getUri()),
                () -> assertEquals(Pays.TypeEnum.PAYS, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1962,12,9), resultItem1.getDateCreation()),
                () -> assertEquals(LocalDate.of(1964,4,26), resultItem1.getDateSuppression()),
                () -> assertEquals("Tanganyika", resultItem1.getIntitule()),
                () -> assertEquals("République du Tanganyika", resultItem1.getIntituleComplet())
        );

    }

    ////////////////////////////////////////////////////////////////////
    ///                  geo/pays/suivants                           ///
    ////////////////////////////////////////////////////////////////////

//    geo/pays/99121/suivants?date=1950-01-01
    @Test
    void should_return_3_pays_when_PaysCodeSuivants_code99121_date19500101() {
        var response  = endpoints.getcogpayssuiv("99121", LocalDate.of(1950, 1, 1));
        var result = response.getBody();
        var resultItem1= result.getFirst();
        assertEquals(3, result.size());
        assertAll(
                () -> assertEquals("99119", resultItem1.getCode()),
                () -> assertEquals("http://id.insee.fr/geo/pays/0b0d2b49-54a2-4d6c-be48-8900c905eaaa", resultItem1.getUri()),
                () -> assertEquals(Pays.TypeEnum.PAYS, resultItem1.getType()),
                () -> assertEquals(LocalDate.of(1991,6,25), resultItem1.getDateCreation()),
                () -> assertEquals("Croatie", resultItem1.getIntitule()),
                () -> assertEquals("République de Croatie", resultItem1.getIntituleComplet()),
                () -> assertEquals("HR", resultItem1.getIso3166alpha2()),
                () -> assertEquals("HRV", resultItem1.getIso3166alpha3()),
                () -> assertEquals("191", resultItem1.getIso3166num())
        );

    }
}
