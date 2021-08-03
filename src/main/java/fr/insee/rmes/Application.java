package fr.insee.rmes;

import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	private static final String PROPERTIES_FILENAME = "rmeswsgi";

	private static final Logger LOG = LoggerFactory.getLogger(Application.class);

	@Autowired
	private Environment env;

	public static void main(String[] args) {
		System.setProperty("spring.config.name", PROPERTIES_FILENAME);
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// Application name to find the properties that fits
		// default = "application".
		System.setProperty("spring.config.name", PROPERTIES_FILENAME);

		return builder
				.properties("spring.config.location=classpath:/,file:///${catalina.base}/webapps/"+PROPERTIES_FILENAME+".properties")
				.properties("spring.config.additional-location:classpath:rmeswsgi-magma.properties")
				.sources(Application.class);
	}

	@PostConstruct
	public void startupApplicationLog() {
		printPropertiesInLog();
	}

	private void printPropertiesInLog() {
		ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILENAME);
		List<String> properties = rb.keySet().stream().collect(Collectors.toList());
		Collections.sort(properties);
		LOG.info("========================Properties values================");
		properties.forEach(key -> LOG.info("{} = {}", key, printValueWithoutPassword(key)));
		LOG.info("==========================================================================");
	}

	private String printValueWithoutPassword(String key) {
		if (StringUtils.containsAny(key, "password", "pwd", "keycloak.key")) {
			return "******";
		}
		return env.getProperty(key);
	}

}
