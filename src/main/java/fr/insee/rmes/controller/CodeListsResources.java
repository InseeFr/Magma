package fr.insee.rmes.controller;

import java.util.Objects;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.dto.codeList.CodeList;
import fr.insee.rmes.dto.codeList.CodeListNotation;
import fr.insee.rmes.services.codelists.CodeListsServices;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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


    @GetMapping("/listesCodes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getAllCodesLists", summary = "Get all codes lists",security = @SecurityRequirement(name = "bearerScheme"),
            responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CodeList.class)))})
    public ResponseEntity <String> getallCodesLists() throws RmesException {
        String jsonResult = codeListsServices.getAllCodesLists();
        if(jsonResult.isEmpty()){
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }


    @GetMapping("/listeCode/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getCodesList", summary = "Get one codes list",security = @SecurityRequirement(name = "bearerScheme"),responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CodeListNotation.class)))})
    public ResponseEntity<String> getCodesList(@PathVariable(Constants.NOTATION) String notation) throws RmesException {
        String jsonResult = codeListsServices.getCodesList(notation);
        if(Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult)){
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
        }else{

            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }
    }

}
