package fr.insee.rmes.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Hidden
@RequestMapping("/starter")
@Controller
@ResponseBody
public class StarterController {

        @Autowired(required = false)
        private Optional<BuildProperties> buildProperties;

        @GetMapping("/healthcheck")
        public ResponseEntity<String> healthcheck(){
                return ResponseEntity.ok(
                    """
                         OK
                         
                         Version %s
                         Utilisateur %s
                    """
                        .formatted(
                            buildProperties.map(BuildProperties::getVersion).orElse("n.a"),
                            SecurityContextHolder.getContext().getAuthentication().getName()
                        )                );
        }

        @GetMapping("/healthcheckadmin")
        public ResponseEntity<String> healthcheckadmin(){
                return ResponseEntity.ok(
                    """
                         OK 
                         
                         Version %s
                         Administrateur %s
                    """
                    .formatted(
                        buildProperties.map(BuildProperties::getVersion).orElse("n.a"),
                        SecurityContextHolder.getContext().getAuthentication().getName()
                    )
                );
        }


}
