package fr.insee.rmes.controller;

import fr.insee.rmes.modelSwagger.operation.OperationByIdModelSwagger;
import fr.insee.rmes.modelSwagger.operation.OperationBySerieIdModelSwagger;
import fr.insee.rmes.modelSwagger.operation.SerieByIdModelSwagger;
import fr.insee.rmes.services.pogues.PoguesServices;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping(value = "/", produces = {"application/json"})
@Tag(name = "Series-Opérations", description = "Consultation des Séries et Opérations de la base de gestion")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Not found", content = {@Content}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content})})

public class PoguesResources {

    @Autowired
    PoguesServices poguesServices;


    @GetMapping(path = "/operations/series", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getAllSeries", summary = "Get all series",security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SerieByIdModelSwagger.class)))})
    public ResponseEntity<String> getAllSeriesLists(@Parameter(
            description = "param for survey only",
            required = false) @RequestParam("Survey") Boolean survey) throws RmesException, IOException {


        String jsonResult = (String) poguesServices.getAllSeriesLists(survey);

        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }

    @GetMapping(path = "/operations/serie/{id}/", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getSerieById", summary = "Get one serie",security = @SecurityRequirement(name = "bearerScheme"),

            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = SerieByIdModelSwagger.class)))})

    public ResponseEntity<String> getCodeList(@PathVariable("id") String id) throws RmesException, IOException {
        String jsonResult = poguesServices.getSerieById(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }


    @GetMapping(path = "/operations/serie/{id}/operations", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getOperationsBySerie", summary = "Get operations by serie",security = @SecurityRequirement(name = "bearerScheme"),

            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OperationBySerieIdModelSwagger.class)))})

    public ResponseEntity<String> getOperationsBySerie(@PathVariable("id") String id) throws RmesException, IOException {
        String jsonResult = poguesServices.getOperationsBySerieId(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }


    @GetMapping(path = "/operations/operation/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getOperationsBycode", summary = "Get operations by code",security = @SecurityRequirement(name = "bearerScheme"),

            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OperationByIdModelSwagger.class)))})

    public ResponseEntity<String> getOperationByCode(@PathVariable("id") String id) throws RmesException, IOException {
        String jsonResult = poguesServices.getOperationByCode(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

}
