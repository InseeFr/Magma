package fr.insee.rmes;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static fr.insee.rmes.magma.gestion.old.configuration.SecurityConfigWithAccessControl.STARTER_SECURITY_DISABLED;

@SpringBootApplication
public class MagmaAppMain {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "magma-app");
        new SpringApplicationBuilder().sources(MagmaAppMain.class)
                .profiles(STARTER_SECURITY_DISABLED)
                .build().run(args);

    }

}