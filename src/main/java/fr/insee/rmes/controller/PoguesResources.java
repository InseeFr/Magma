package fr.insee.rmes.controller;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import fr.insee.rmes.dto.pogues.NodePogues;
import fr.insee.rmes.dto.pogues.OperationByCode;
import fr.insee.rmes.dto.pogues.PoguesListId;
import fr.insee.rmes.dto.pogues.PoguesListOperationByIdSerie;
import fr.insee.rmes.services.pogues.PoguesServices;
import fr.insee.rmes.utils.Constants;
import io.swagger.v3.oas.annotations.media.Content;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Pogues", description = "Consultation Gestion API - Pogues")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})

public class PoguesResources {
    @Autowired
    PoguesServices poguesServices;


    @GetMapping("/operations/series")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getAllCodesLists", summary = "Get all series",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PoguesListId.class)))})
    public ResponseEntity<String> getallCodesLists(@Parameter(
            description = "param for survey only",
            required = false)@QueryParam("Survey")  Boolean survey) throws RmesException {

        String jsonResult = (String) poguesServices.getAllCodesLists(survey);

        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }

    @GetMapping("/operations/serie/{id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getCodeList", summary = "Get one serie",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PoguesListId.class)))})

    public ResponseEntity<String> getCodeList(@PathVariable("id") String id) throws RmesException {
        String jsonResult = poguesServices.getCodesList(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }

    @GetMapping("/operations/serie/{id}/operations")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getOperationsBySerie", summary = "Get operations by serie",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = PoguesListOperationByIdSerie.class)))})

    public ResponseEntity<String> getOperationsBySerie(@PathVariable("id") String id) throws RmesException {
        String jsonResult = poguesServices.getOperationsBySerie(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

    @GetMapping("/operations/operation/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getOperationsBycode", summary = "Get operations by code",
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = OperationByCode.class)))})

    public ResponseEntity<String> getOperationByCode(@PathVariable("id") String id) throws RmesException {
        String jsonResult = poguesServices.getOperationByCode(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

}
