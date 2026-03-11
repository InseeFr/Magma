package fr.insee.rmes.magma.gestion.api.testcontainers;

import fr.insee.rmes.magma.gestion.api.StructuresComposantsEndpoints;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@Tag("integration")

class StructuresComposantsQueriesTest  extends TestcontainerTest {

    @Autowired
    StructuresComposantsEndpoints endpoints;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllComponents() {
    }

    @Test
    void getAllStructures() {
    }

    @Test
    void getComponentById() {
    }

    @Test
    void getSliceKeys() {
    }

    @Test
    void getStructure() {
    }
}