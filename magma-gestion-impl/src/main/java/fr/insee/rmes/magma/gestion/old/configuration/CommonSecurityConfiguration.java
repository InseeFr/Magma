package fr.insee.rmes.magma.gestion.old.configuration;

import fr.insee.security.configuration.UserDecoderConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@Import(UserDecoderConfiguration.class)
public class CommonSecurityConfiguration {
}
