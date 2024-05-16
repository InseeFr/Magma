package fr.insee.rmes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.model.datasets.Distributions;
import fr.insee.rmes.modelSwagger.dataset.DataSetModelSwagger;
import fr.insee.rmes.services.datasets.DataSetsServices;
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
import jakarta.ws.rs.core.MediaType;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@RestController
@RequestMapping(value = "/", produces = {"application/json"})
@Tag(name = "datasets", description = "Consultation Magma API - datasets")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Success", content = {@Content}),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content}),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content}),
        @ApiResponse(responseCode = "404", description = "Not found", content = {@Content}),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = {@Content})})
public class DataSetResources {

    @Autowired
    DataSetsServices dataSetsServices;

    private static final String EXAMPLE_PATCH_DATASET = """
            {
              "issued": "2022-11-11",
              "modified": "2023-12-31",
              "temporal": {"startPeriod": "2020-01-01", "endPeriod": "2024-01-01"},
              "numObservations": 150,
              "numSeries": 12
            }
            """;

    @GetMapping("/datasets/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getListDatasets", summary = "Get list of datasets", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "array", implementation = DataSetModelSwagger.class)))})
    public ResponseEntity<String> getListDatasets(@RequestParam(required = false) @Parameter(description = "Date of last update. Example: 2023-01-31") String dateMiseAJour) throws RmesException, JsonProcessingException {
        if (dateMiseAJour == null){
            dateMiseAJour = "";
        }
        String jsonResult = dataSetsServices.getListDataSets(dateMiseAJour);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

    @GetMapping("/dataset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getDataSetById", summary = "Get one dataset", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = "string", implementation = DataSetModelSwagger.class)))})

    public ResponseEntity<String> getDataSetByID(@PathVariable("id") String id,
                                                 @RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour
    ) throws RmesException, JsonProcessingException {

        // par défaut ce booléen est faux et donc on renvoie tout les infos d'un dataset
        if (!boolDateMiseAJour) {
            String jsonResult = dataSetsServices.getDataSetByID(id);
            if (jsonResult.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }
        // Sinon, on renvoie juste la date MiseAJour
        else {
            String jsonResult = dataSetsServices.getDataSetByIDSummary(id);
            if (jsonResult.isEmpty()) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }


    }


    @GetMapping("/dataset/{id}/distributions")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Distributions[]>  getDataSetDistributionsById(@PathVariable String id) throws RmesException, JsonProcessingException {

        return ResponseEntity.ok(dataSetsServices.getDataSetDistributionsById(id));
    }


    @PatchMapping(value = "/dataset/{id}")
    @Operation(operationId = "update some properties of a dataset ", summary = "Update ObservationNumber, issued, modified, temporal, or numSeries  of a dataset")
    public ResponseEntity<String> patchDataSetDistributionsByIdNombreObservations(
            @PathVariable("id") String datasetId,
            @Parameter(hidden = true)
            @RequestHeader(name = "Authorization",required = false) String token,
            @Schema(name ="patchDataset" ,description = "Json with parameters you want to change", example = EXAMPLE_PATCH_DATASET)
            @RequestBody(required = true) PatchDatasetDTO stringPatchDataset
    ) throws RmesException, MalformedURLException {
        if (token == null){
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
        }
        return this.dataSetsServices.patchDataset(datasetId,stringPatchDataset,token);
    }

}



