package fr.insee.rmes.controller;


import fr.insee.rmes.modelSwagger.organisations.OrganisationsModelSwagger;
import fr.insee.rmes.services.organisations.OrganisationsServices;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Organisations", description = "Consultation des organisations de la base de gestion")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success",content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})

public class OrganisationsController {

    @Autowired
    OrganisationsServices organisationsServices;


    @GetMapping(path = "/organisations", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getAllOrganisations", summary = "Get all organisations",security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrganisationsModelSwagger.class)))})
    public ResponseEntity<String> getAllOrganisations () throws RmesException, IOException {

        String jsonResult = (String) organisationsServices.getAllOrganisations();

        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

    @GetMapping(path = "/organisation/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getOrganisationById.ftlh", summary = "Get one organisation",security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OrganisationsModelSwagger.class)))})

    public ResponseEntity<String> getOrganisationById(@PathVariable("id") String id) throws RmesException, IOException {
        String jsonResult = organisationsServices.getOrganisationById(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

}
