package fr.insee.rmes.magma.gestion.old;

import fr.insee.rmes.magma.gestion.old.configuration.PropertiesLogger;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MagmaGestion {


    private static final Logger LOG = LoggerFactory.getLogger(MagmaGestion.class);
    @Autowired
    private Config config;

    public static void main(String[] args) {
        configureApplicationBuilder(new SpringApplicationBuilder()).build().run(args);
    }

    public static SpringApplicationBuilder configureApplicationBuilder(SpringApplicationBuilder springApplicationBuilder){
        return springApplicationBuilder.sources(MagmaGestion.class)
                .listeners(new PropertiesLogger());
    }

    @PostConstruct
    public void startupApplication() {
        config.init();
    }
}
