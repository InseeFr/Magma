package fr.insee.rmes.magma.diffusion.config;

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
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

@org.springframework.context.annotation.Configuration
@Slf4j
@ServletComponentScan
public class MetadataConfig {

    @Bean
    public Configuration freemarkerConfiguration(
            @Value("${fr.insee.rmes.magma.api.freemarker.locale-language}") String localLanguage,
            @Value("${fr.insee.rmes.magma.api.geographie.types-autorises}") String typesAutorises
    ) throws TemplateModelException {
        var configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // Charge les templates depuis le classpath (dans src/main/resources/request)
        ClassTemplateLoader classTemplateLoader = new ClassTemplateLoader(MetadataConfig.class, "/request");

        MultiTemplateLoader mtl = new MultiTemplateLoader(new TemplateLoader[]{classTemplateLoader});

        //log.info("Init freemarker templateloader {}", freemarkerTemplatesDirectory);
        configuration.setTemplateLoader(mtl);

        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.of(localLanguage));

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        configuration.setLogTemplateExceptions(false);

        configuration.setWrapUncheckedExceptions(true);

        String listeTypesGeo = Arrays.stream(typesAutorises.split(","))
                .map(t -> "\"" + t.trim() + "\"")
                .collect(Collectors.joining(", "));
        configuration.setSharedVariable("listeTypesGeo", listeTypesGeo);

        return configuration;
    }
}
