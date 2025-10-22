package fr.insee.rmes.magma.diffusion.api.testcontainers.queries;

import fr.insee.rmes.magma.diffusion.api.NomenclaturesEndpoints;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Tag("integration")

public class NomenclaturesQueriesTest extends TestcontainerTest {

    @Autowired
    NomenclaturesEndpoints endpoints;

    @Test
    void shouldReturnCorrectNafr2Classification () {
        var response = endpoints.getClassificationByCode("nafr2", "sousClasses", "33.16Z");
        var result = response.getBody();

        Assertions.assertNotNull(result);
        assertAll(
                () -> assertEquals("http://id.insee.fr/codes/nafr2/sousClasse/33.16Z", result.getUri()),
                () -> assertEquals("Réparation et maintenance d'aéronefs et d'engins spatiaux", result.getIntitule())
        );
    }

    @Test
    void shouldReturnCorrectNaf2025Classification () {
        var response = endpoints.getClassificationByCode("naf2025", "sousClasses", "01.13Y");
        var result = response.getBody();

        Assertions.assertNotNull(result);
        assertAll(
                () -> assertEquals("http://id.insee.fr/codes/naf2025/sousClasse/01.13Y", result.getUri()),
                () -> assertEquals("Culture de légumes, de melons, de racines et de tubercules", result.getIntitule())
        );
    }

    @Test
    void shouldReturnCorrectCjClassification () {
        var response = endpoints.getClassificationByCode("cj", "niveauIII", "5520");
        var result = response.getBody();

        Assertions.assertNotNull(result);
        assertAll(
                () -> assertEquals("http://id.insee.fr/codes/cj/niveauIII/5520", result.getUri()),
                () -> assertEquals("Fonds à forme sociétale à conseil d'administration", result.getIntitule())
        );
    }

    @Test
    void shouldReturnCorrectPcs2020Classification () {
        var response = endpoints.getClassificationByCode("pcs2020", "profession", "21B4");
        var result = response.getBody();

        Assertions.assertNotNull(result);
        assertAll(
                () -> assertEquals("http://id.insee.fr/codes/pcs2020/profession/21B4", result.getUri()),
                () -> assertEquals("Artisans plombiers / Artisanes plombières, chauffagistes", result.getIntitule())
        );
    }

}
