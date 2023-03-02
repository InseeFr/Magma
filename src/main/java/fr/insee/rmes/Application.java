package fr.insee.rmes;

import fr.insee.rmes.configuration.PropertiesLogger;
import fr.insee.rmes.utils.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

        @Autowired
        private Config config;

        public static void main(String[] args) {
                configureApplicationBuilder(new SpringApplicationBuilder()).build().run(args);        }

        public static SpringApplicationBuilder configureApplicationBuilder(SpringApplicationBuilder springApplicationBuilder){
                return springApplicationBuilder.sources(Application.class)
                    .listeners(new PropertiesLogger());
        }
        @Override
        protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                return configureApplicationBuilder(builder);
        }

        @PostConstruct
        public void startupApplication() {
                config.init();
        }

}
