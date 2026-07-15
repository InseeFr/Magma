package fr.insee.rmes.magma.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;

@org.springframework.context.annotation.Configuration
@Slf4j
@ServletComponentScan
public class MagmaConfig {

    @Bean
    public Configuration freemarkerConfiguration
            (@Value("${fr.insee.rmes.magma.api.freemarker.locale-language:fr}") String localLanguage,
            @Value("${fr.insee.rmes.magma.baseGraph:http://rdf.insee.fr/graphes/}") String baseGraph,
            @Value("${fr.insee.rmes.magma.codeLists.graph:codes}") String codeListsGraph,
            @Value("${fr.insee.rmes.magma.structures.graph:structures}") String structuresGraph,
            @Value("${fr.insee.rmes.magma.datasets.graph:datasets}") String datasetsGraph,
            @Value("${fr.insee.rmes.magma.organisations.graph:organisations}") String organisationsGraph,
            @Value("${fr.insee.rmes.magma.adms.graph:adms}") String admsGraph,
            @Value("${fr.insee.rmes.magma.ontologies.baseURI:ontologies/insee/base}") String ontologiesGraph,
            @Value("${fr.insee.rmes.magma.operations.graph:operations}") String seriesOperationsGraph,
            @Value("${fr.insee.rmes.magma.lg1:fr}") String lg1,
            @Value("${fr.insee.rmes.magma.lg2:en}") String lg2) throws URISyntaxException, IOException, TemplateModelException {
        var configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // Charge les templates depuis le classpath (dans src/main/resources/request)
        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(MagmaConfig.class, "/request");

        MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[]{classTemplateLoader});

        //log.info("Init freemarker templateloader {}", freemarkerTemplatesDirectory);
        configuration.setTemplateLoader(mtl);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.of(localLanguage));

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        configuration.setLogTemplateExceptions(false);

        configuration.setWrapUncheckedExceptions(true);

        configuration.setSharedVariable("CODELIST_GRAPH", baseGraph + codeListsGraph);
        configuration.setSharedVariable("STRUCTURES_GRAPH", baseGraph + structuresGraph);
        configuration.setSharedVariable("DATASETS_GRAPH", baseGraph + datasetsGraph);
        configuration.setSharedVariable("ORGANISATIONS_GRAPH", baseGraph + organisationsGraph);
        configuration.setSharedVariable("ADMS_GRAPH", baseGraph + admsGraph);
        configuration.setSharedVariable("ONTOLOGIES_GRAPH", baseGraph + ontologiesGraph);
        configuration.setSharedVariable("OPERATIONS_SERIES_GRAPH", baseGraph + seriesOperationsGraph);
        configuration.setSharedVariable("LG1", lg1);
        configuration.setSharedVariable("LG2", lg2);

        return configuration;
    }
}

