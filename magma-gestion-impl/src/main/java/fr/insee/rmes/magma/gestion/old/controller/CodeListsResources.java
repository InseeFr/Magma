package fr.insee.rmes.magma.gestion.old.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.magma.gestion.old.modelSwagger.codeList.AllListCodeModelSwagger;
import fr.insee.rmes.magma.gestion.old.modelSwagger.codeList.ListCodeByIdModelSwagger;
import fr.insee.rmes.magma.gestion.old.services.codelists.CodeListsServices;
import fr.insee.rmes.magma.gestion.old.utils.Constants;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Codes lists", description = "Consultation Gestion API - listes des codes")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		@ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})
    public class CodeListsResources {

    @Autowired
    CodeListsServices codeListsServices;

    private static final String ERROR_PAGINATION = "The page you are looking for does not exist. Try a smaller page number.";

    @GetMapping(path = "/listesCodes", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getAllCodesLists", summary = "Get all codes lists", security = @SecurityRequirement(name = "bearerScheme"),
            responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = AllListCodeModelSwagger.class)))})
    public ResponseEntity<String> getallCodesLists(@RequestParam(required = false) @Parameter(description = "Date of last update. Example: 2023-01-31") String dateMiseAJour) throws RmesException {
        if (dateMiseAJour == null){
            dateMiseAJour = "";
        }
        String jsonResult = codeListsServices.getAllCodesLists(dateMiseAJour);
        if (jsonResult.isEmpty()) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }

    // en fait ici l'id correspond à la notation
    @RequestMapping(path="/listeCode/{id}", method=GET, params="withCodes=true", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getCodesList", summary = "Get one codes list", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ListCodeByIdModelSwagger.class)))})
    public ResponseEntity<String> getCodesList(
            @PathVariable(Constants.NOTATION) String notation,
            @RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour,
            @RequestParam(name = "withCodes") Boolean boolWithCodes
    ) throws RmesException {

        if (!boolDateMiseAJour) {
            String jsonResult = codeListsServices.getCodesList(notation);
            if (Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult)) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }
        else {
            String jsonResult = codeListsServices.getCodesListDateMiseAJour(notation);
            if (Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult)) {
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            } else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }
    }

    @RequestMapping(path="/listeCode/{id}", method=GET, params="withCodes=false", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getCodesList", summary = "Get one codes list", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ListCodeByIdModelSwagger.class)))})
    public ResponseEntity<String> getCodesListWithoutCodes(
            @PathVariable(Constants.NOTATION) String notation,
            @RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour,
            @RequestParam(name = "withCodes") Boolean boolWithCodes) throws RmesException {
        String jsonResult = codeListsServices.getCodesListWithoutCodes(notation);
        if (Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult)) {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }


    @GetMapping(path = "/listeCode/{id}/pagination", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(operationId = "getCodesListPagination", summary = "Get one codes list with  5 codes per page.", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ListCodeByIdModelSwagger.class)))})
    public ResponseEntity<String> getCodesListPage(
            @PathVariable(Constants.NOTATION) String notation,
            @RequestParam(name = "pageNumber") int pageNumber
    ) throws RmesException, JsonProcessingException {
        if(pageNumber > codeListsServices.getMaxpage(notation)){
            return ResponseEntity.status(HttpStatus.SC_REQUESTED_RANGE_NOT_SATISFIABLE).body(ERROR_PAGINATION);
        }
        else {
            String response = codeListsServices.getCodesListPagination(notation, pageNumber);
            return ResponseEntity.status(HttpStatus.SC_OK).body(response);
        }

    }
}
