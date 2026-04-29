package fr.insee.rmes.magma.gestion.api.testcontainers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@Slf4j
public class TestcontainerTest {
    static GraphDBContainer container = new GraphDBContainer("ontotext/graphdb:10.8.8").withReuse(false);

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
