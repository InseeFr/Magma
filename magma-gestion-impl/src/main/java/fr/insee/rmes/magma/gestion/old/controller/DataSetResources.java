package fr.insee.rmes.magma.gestion.old.controller;

import tools.jackson.core.JacksonException;
import fr.insee.rmes.magma.gestion.old.datasets.PatchDatasetDTO;
import fr.insee.rmes.magma.gestion.old.model.datasets.Distributions;
import fr.insee.rmes.magma.gestion.old.modelSwagger.dataset.DataSetModelSwagger;
import fr.insee.rmes.magma.gestion.old.services.datasets.DataSetsServices;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import fr.insee.rmes.magma.gestion.security.UserDecoder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequiredArgsConstructor
public class DataSetResources {

    private static final String EXAMPLE_PATCH_DATASET = """
            {
              "issued": "2022-11-11",
              "modified": "2023-12-31",
              "temporal": {"startPeriod": "2020-01-01", "endPeriod": "2024-01-01"},
              "numObservations": 150,
              "numSeries": 12
            }
            """;
    private final UserDecoder userDecoder;
    private final DataSetsServices dataSetsServices;

    @GetMapping(path = "/datasets/list", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getListDatasets", summary = "Get list of datasets", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "array", implementation = DataSetModelSwagger.class)))})
    public ResponseEntity<String> getListDatasets(@RequestParam(required = false) @Parameter(description = "Date of last update. Example: 2023-01-31") String dateMiseAJour) throws RmesException, JacksonException {
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

    @GetMapping(path = "/dataset/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getDataSetById", summary = "Get one dataset", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(type = "string", implementation = DataSetModelSwagger.class)))})

    public ResponseEntity<String> getDataSetByID(@PathVariable("id") String id,
                                                 @RequestParam(name = "dateMiseAJour", defaultValue = "false") boolean boolDateMiseAJour
    ) throws RmesException, JacksonException {

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


    @GetMapping(path = "/dataset/{id}/distributions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Distributions[]>  getDataSetDistributionsById(@PathVariable String id) throws RmesException, JacksonException {

        return ResponseEntity.ok(dataSetsServices.getDataSetDistributionsById(id));
    }


    //@PatchMapping(value = "/dataset/{id}")
    //@Operation(operationId = "update some properties of a dataset ", summary = "Update ObservationNumber, issued, modified, temporal, or numSeries  of a dataset")
    public ResponseEntity<String> patchDataSetDistributionsByIdNombreObservations(
            @PathVariable("id") String datasetId,
            @Parameter(hidden = true)
            @RequestHeader(name = "Authorization",required = false) String token,
            @Schema(name ="patchDataset" ,description = "Json with parameters you want to change", example = EXAMPLE_PATCH_DATASET)
            @RequestBody(required = true) PatchDatasetDTO stringPatchDataset,
            @AuthenticationPrincipal Object principal
    ) throws RmesException, MalformedURLException {
        if (token == null){
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).build();
        }
        return this.dataSetsServices.patchDataset(datasetId,stringPatchDataset,token, this.userDecoder.fromPrincipal(principal));
    }

}



