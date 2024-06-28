package fr.insee.rmes.controller;

import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonObject;
import fr.insee.rmes.utils.config.Config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.MockServerRestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest( properties = {
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
        "fr.insee.rmes.role.administrateur = admin",
        "fr.insee.rmes.role.gestionnaire.dataset = gest",
        "fr.insee.rmes.security.whitelist-matchers = ",
        "fr.insee.rmes.security.token.oidc-claim-role=realm_access",
        "fr.insee.rmes.security.token.oidc-claim-username=preferred_username",
        "fr.insee.rmes.security.token.oidc-roles-in-claim-role=roles",
        "fr.insee.rmes.security.token.email=email",
        "spring.security.oauth2.resourceserver.jwt.issuer-uri=",
        "logging.level.org.springframework.security=DEBUG",
        "logging.level.org.springframework.security.web.access=TRACE"
})
@ActiveProfiles("test")
@AutoConfigureMockMvc
class DataSetResourcesIntegrationTest {

    static final MockServerRestClientCustomizer customizer=new MockServerRestClientCustomizer();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    void patchDatasetTest_whenBodyOkThenOkNoContent() throws Exception {
        //GIVEN
        customizer.getServer().expect(requestTo("/1"))
                .andExpect(header("Authorization", "Bearer toto"))
                .andRespond(withNoContent());
        configureJwtDecoderMock(jwtDecoder, "FBibonne", "INSEE", List.of("admin"));
        //WHEN THEN
        mockMvc.perform(patch("/dataset/1")
                        .content("{\"numObservations\":2}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization","Bearer toto")
                )
                .andExpect(status().isNoContent());
    }

    private void configureJwtDecoderMock(JwtDecoder jwtDecoderMock, String idep, String timbre, List<String> roles) {
        when(jwtDecoderMock.decode(anyString())).then(invocation -> Jwt.withTokenValue(invocation.getArgument(0))
                .header("typ", "JWT")
                .header("alg", "none")
                .claim("preferred_username", idep)
                .claim("timbre", timbre)
                .claim("realm_access", jsonRoles(roles))
                .build());
    }

    private static JsonObject jsonRoles(List<String> roles) {
        var jsonObject = new JsonObject();
        var jsonArray=new JsonArray();
        roles.forEach(jsonArray::add);
        jsonObject.add("roles", jsonArray);
        return jsonObject;
    }


    @Test
    void patchDatasetTest_whenNoBodyThenBadRequest() throws Exception {
        //GIVEN
        configureJwtDecoderMock(jwtDecoder, "FBibonne", "INSEE", List.of("admin"));
        //WHEN THEN
        mockMvc.perform(patch("/dataset/1")
                        .header("Authorization", "Bearer toto")
                        .content("")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void patchDatasetTest_whenBadRoleThenForbiden() throws Exception {
        //GIVEN
        configureJwtDecoderMock(jwtDecoder, "FBibonne", "INSEE", List.of("bad_role"));
        //WHEN THEN
        mockMvc.perform(patch("/dataset/1")
                        .header("Authorization", "Bearer toto")
                        .content("")
                )
                .andExpect(status().isForbidden());
    }

    @Test
    void patchDatasetTest_whenAnonymousThenUnauthorized() throws Exception {
        mockMvc.perform(patch("/dataset/1")
                )
                .andExpect(status().isUnauthorized());
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