package fr.insee.rmes.magma.api.testcontainers.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

@Slf4j
@TestPropertySource(properties = "spring.profiles.active=security.disabled")
public class TestcontainerTestGestion {
    static GraphDBContainerGestion container = new GraphDBContainerGestion("ontotext/graphdb:10.8.8").withReuse(false);

    @BeforeAll
    static void startContainer(){
        container.start();

    }


    @DynamicPropertySource
    static void overrideSpringProperties(DynamicPropertyRegistry registry) {
        String url  = "http://" + container.getHost() + ":" + container.getMappedPort(7200)+ "/repositories/gestion";
        log.info("Graphdb URL: " + url);
        registry.add("fr.insee.rmes.magma.api.sparqlEndpoint", () -> url) ;
    }
}
