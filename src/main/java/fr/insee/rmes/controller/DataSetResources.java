package fr.insee.rmes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.dto.dataset.DataSetDTO;
import fr.insee.rmes.services.datasets.DataSetsServices;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "datasets", description = "Consultation Magma API - datasets")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
        @ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
        @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})
public class DataSetResources {

    @Autowired
    DataSetsServices dataSetsServices;

    @GetMapping("/datasets/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getListDatasets", summary = "Get list of datasets", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "array", implementation = DataSetDTO.class)))})
    public ResponseEntity<String> getListDatasets() throws RmesException, JsonProcessingException {
        String jsonResult = dataSetsServices.getListDataSets();
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

    @GetMapping("/datasets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getDataSetById", summary = "Get one dataset", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "string", implementation = DataSetDTO.class)))})

    public ResponseEntity<String> getDataSetByID(@PathVariable("id") String id) throws RmesException, JsonProcessingException {

        String jsonResult = dataSetsServices.getDataSetByID(id);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }


    }



