package fr.insee.rmes.controller;

import fr.insee.rmes.utils.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClient;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = {
        "fr.insee.rmes.magma.force.ssl=false",
        "fr.insee.rmes.magma.gestion.rdfServer = ",
        "fr.insee.rmes.magma.gestion.repository = ",
        "fr.insee.rmes.magma.collections.baseURI = ",
        "fr.insee.rmes.magma.operations.families.baseURI = ",
        "fr.insee.rmes.magma.operations.series.baseURI = ",
        "fr.insee.rmes.magma.products.baseURI = ",
        "fr.insee.rmes.magma.documentations.baseURI = ",
        "fr.insee.rmes.magma.documents.baseURI = ",
        "fr.insee.rmes.magma.links.baseURI = ",
        "fr.insee.rmes.magma.documentation.geographie.baseURI = ",
        "fr.insee.rmes.role.administrateur = ",
        "fr.insee.rmes.role.gestionnaire.dataset = ",
        "fr.insee.rmes.security.whitelist-matchers = ",
        "fr.insee.rmes.security.token.oidc-claim-role=role",
        "fr.insee.rmes.security.token.oidc-claim-username=username"
})
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DataSetResourcesIntegrationTest {

    static final MockServerRestClientCustomizer customizer=new MockServerRestClientCustomizer();

    @Autowired
    MockMvc mockMvc;


    @Test
    @WithMockUser(username = "someone")
    void patchDatasetTest_whenBodyOkThenOkNoContent() throws Exception {
        customizer.getServer().expect(requestTo("/1"))
                //TODO check header
                .andRespond(withSuccess());
        mockMvc.perform(patch("/dataset/1")
                        .header("Authorization","Bearer toto")
                )
                .andExpect(status().isNoContent());
    }


    @Test
    @WithMockUser(username = "someone")
    void patchDatasetTest_whenNoBodyThenBadRequest() throws Exception {
        mockMvc.perform(patch("/dataset/1")
                        .header("Authorization","Bearer toto")
                )
                .andExpect(status().isBadRequest());
    }

    @TestConfiguration
    @Profile("test")
    static class  AdditionalConfigurationFotThisTest {
        @Bean
        public RestClient restClient(Config config) {
            var builder = RestClient.builder();
            customizer.customize(builder);
            return builder.build();
        }
    }


}