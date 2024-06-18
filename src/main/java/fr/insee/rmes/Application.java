package fr.insee.rmes;


import fr.insee.rmes.configuration.PropertiesLogger;
import fr.insee.rmes.utils.config.Config;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static final String PROPERTIES_FILENAME = "rmeswsgi";

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);
	@Autowired
	private Config config;

	public static void main(String[] args) {
		configureApplicationBuilder(new SpringApplicationBuilder()).build().run(args);        }

	public static SpringApplicationBuilder configureApplicationBuilder(SpringApplicationBuilder springApplicationBuilder){
		System.setProperty("spring.config.name", PROPERTIES_FILENAME);
		return springApplicationBuilder.sources(Application.class)
				.listeners(new PropertiesLogger());
	}

	@PostConstruct
	public void startupApplication() {
		config.init();
	}




}
