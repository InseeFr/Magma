package fr.insee.rmes;

import fr.insee.rmes.configuration.PropertiesLogger;
import fr.insee.rmes.utils.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import javax.annotation.PostConstruct;


@SpringBootApplication public class Application {

        @Autowired
        private Config config;

        public static void main(String[] args) {
                configureApplicationBuilder(new SpringApplicationBuilder()).build().run(args);        }

        public static SpringApplicationBuilder configureApplicationBuilder(SpringApplicationBuilder springApplicationBuilder){
                return springApplicationBuilder.sources(Application.class)
                    .listeners(new PropertiesLogger());
        }

        @PostConstruct
        public void startupApplication() {
                config.init();
        }

}
