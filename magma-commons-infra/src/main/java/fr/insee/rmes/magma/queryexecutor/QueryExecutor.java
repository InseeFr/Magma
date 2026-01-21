package fr.insee.rmes.magma.queryexecutor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.magma.queries.Query;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;

@Slf4j
@Component
public record QueryExecutor(RestClient restClient, String urlTemplate) {

    @Autowired
    public QueryExecutor(@Value("${fr.insee.rmes.magma.api.sparqlEndpoint}") String sparqlEndpoint) {
        this(RestClient.builder()
                .defaultHeader(HttpHeaders.ACCEPT, "text/csv")
                .build(),sparqlEndpoint + "?query={query}"
        );
    }

    public static final String PREFIXES =
            """
            PREFIX dcterms:<http://purl.org/dc/terms/>
            PREFIX xkos:<http://rdf-vocabulary.ddialliance.org/xkos#>
            PREFIX evoc:<http://eurovoc.europa.eu/schema#>
            PREFIX skos:<http://www.w3.org/2004/02/skos/core#>
            PREFIX skosxl:<http://www.w3.org/2008/05/skos-xl#>
            PREFIX dc:<http://purl.org/dc/elements/1.1/>
            PREFIX insee:<http://rdf.insee.fr/def/base#>
            PREFIX geo:<http://www.opengis.net/ont/geosparql#>
            PREFIX igeo:<http://rdf.insee.fr/def/geo#>
            PREFIX rdf:<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
            PREFIX pav:<http://purl.org/pav/>
            PREFIX foaf:<http://xmlns.com/foaf/0.1/>
            PREFIX org:<http://www.w3.org/ns/org#>
            PREFIX prov:<http://www.w3.org/ns/prov#>
            PREFIX xsd:<http://www.w3.org/2001/XMLSchema#>
            PREFIX sdmx-mm:<http://www.w3.org/ns/sdmx-mm#>
            PREFIX qb:<http://purl.org/linked-data/cube#>
            PREFIX rdfs:<http://www.w3.org/2000/01/rdf-schema#>
            PREFIX dcmitype:<http://purl.org/dc/dcmitype/>
            PREFIX adms:<http://www.w3.org/ns/adms#>
            PREFIX stat-dcat-ap:<http://data.europa.eu/m8g/>
            PREFIX dcat:<http://www.w3.org/ns/dcat#>
            """;

    public Csv execute(@NonNull Query query) {
        String prefixedQuery = PREFIXES + query.value();
        return new Csv(restClient.get()
                .uri(urlTemplate, prefixedQuery)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        (HttpRequest request, ClientHttpResponse response) -> {
                            log.error("""
                                Encoded request in error : {}
                                raw request in error : {}
                                """, request.getURI(), prefixedQuery);
                            throw new RuntimeException("Error "+response.getStatusText()+" with message "+new String(response.getBody().readAllBytes()));
                        })
                .body(String.class));
    }

public Boolean executeAskQuery(@NonNull Query query) {
    String prefixedQuery = PREFIXES + query.value();
    log.debug("Executing SPARQL ASK query: {}", prefixedQuery);

    try {
        String response = restClient.get()
                .uri(urlTemplate, prefixedQuery)
                .header("Accept", "application/sparql-results+json") // Format standard pour les résultats SPARQL
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, reponse) -> {
                    String errorMessage = "SPARQL ASK query failed: " + request.getURI();
                    try {
                        String body = new String(reponse.getBody().readAllBytes());
                        errorMessage += " - Response: " + body;
                    } catch (IOException e) {
                        errorMessage += " - Error reading response body: " + e.getMessage();
                    }
                    log.error(errorMessage);
                    throw new RuntimeException("SPARQL query error: " + reponse.getStatusText());
                })
                .body(String.class);

        log.debug("SPARQL ASK response: {}", response);

        // Parse the Json response to extract the boolean
        return parseAskResponse(response);
    } catch (Exception e) {
        log.error("Unexpected error while executing SPARQL ASK query: {}", e.getMessage(), e);
        throw new RuntimeException("Failed to execute SPARQL ASK query", e);
    }
}

    // Method to parse the JSON response of a SPARQL ASK request
    private Boolean parseAskResponse(String jsonResponse) {
        try {
            // Example of a Json response for an ASK request :
            // {"head":{},"boolean":true}
            JsonNode rootNode = new ObjectMapper().readTree(jsonResponse);
            return rootNode.path("boolean").asBoolean();
        } catch (Exception e) {
            log.error("Failed to parse SPARQL ASK response: {}", jsonResponse, e);
            throw new RuntimeException("Invalid SPARQL ASK response format", e);
        }
    }

}
