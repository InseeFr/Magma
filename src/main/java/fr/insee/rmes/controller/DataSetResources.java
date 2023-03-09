package fr.insee.rmes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.modelSwagger.dataset.DataSetModelSwagger;
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
import org.springframework.web.bind.annotation.*;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


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
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "array", implementation = DataSetModelSwagger.class)))})
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
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "string", implementation = DataSetModelSwagger.class)))})

    public ResponseEntity<String> getDataSetByID(@PathVariable("id") String id,
                                                 @RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour
                                                 ) throws RmesException, JsonProcessingException {

        // par défaut ce booléen est faux et donc on renvoie tout les infos d'un dataset
        if (!boolDateMiseAJour){
            String jsonResult = dataSetsServices.getDataSetByID(id);
            if (jsonResult.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }
        // Sinon, on renvoie juste la date MiseAJour
        else {
            String jsonResult = dataSetsServices.getDataSetByIDFilterByDateMaj(id);
            if (jsonResult.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }


    }



    }



