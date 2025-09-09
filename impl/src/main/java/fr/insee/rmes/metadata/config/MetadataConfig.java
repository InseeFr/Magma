package fr.insee.rmes.metadata.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;

@org.springframework.context.annotation.Configuration
@Slf4j
@ServletComponentScan
public class MetadataConfig {

    @Bean
    public Configuration freemarkerConfiguration(@Value("${fr.insee.rmes.metadata.api.freemarker.locale-language}") String localLanguage) throws URISyntaxException, IOException {
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

        return configuration;
    }
}
