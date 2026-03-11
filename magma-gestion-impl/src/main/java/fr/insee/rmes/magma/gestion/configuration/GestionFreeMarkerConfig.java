package fr.insee.rmes.magma.gestion.configuration;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@org.springframework.context.annotation.Configuration
@Slf4j
public class GestionFreeMarkerConfig {

    @Bean
    public Configuration freemarkerConfiguration(
            @Value("${fr.insee.rmes.magma.api.freemarker.locale-language:fr}") String localLanguage,
            @Value("${fr.insee.rmes.magma.baseGraph}") String baseGraph,
            @Value("${fr.insee.rmes.magma.codeLists.graph}") String codeListsGraph,
            @Value("${fr.insee.rmes.magma.structures.graph}") String structuresGraph,
            @Value("${fr.insee.rmes.magma.lg1}") String lg1,
            @Value("${fr.insee.rmes.magma.lg2}") String lg2
    ) throws TemplateException {
        var configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(GestionFreeMarkerConfig.class, "/queries");
        configuration.setTemplateLoader(new MultiTemplateLoader(new TemplateLoader[]{classTemplateLoader}));

        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.of(localLanguage));

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        configuration.setWrapUncheckedExceptions(true);

        configuration.setSharedVariable("CODELIST_GRAPH", baseGraph + codeListsGraph);
        configuration.setSharedVariable("STRUCTURES_GRAPH", baseGraph + structuresGraph);
        configuration.setSharedVariable("LG1", lg1);
        configuration.setSharedVariable("LG2", lg2);

        return configuration;
    }
}