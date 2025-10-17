package fr.insee.rmes.magma.diffusion.queryexecutor;

import fr.insee.rmes.magma.diffusion.queries.Query;
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
                    PREFIX igeo: <http://rdf.insee.fr/def/geo#>
                    PREFIX dcterms: <http://purl.org/dc/terms/>
                    PREFIX xkos: <http://rdf-vocabulary.ddialliance.org/xkos#>
                    PREFIX evoc: <http://eurovoc.europa.eu/schema#>
                    PREFIX skos: <http://www.w3.org/2004/02/skos/core#>
                    PREFIX dc: <http://purl.org/dc/elements/1.1/>
                    PREFIX insee: <http://rdf.insee.fr/def/base#>
                    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                    PREFIX pav: <http://purl.org/pav/>
                    PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
                    PREFIX prov: <http://www.w3.org/ns/prov#>
                    PREFIX sdmx-mm: <http://www.w3.org/ns/sdmx-mm#>
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

}
