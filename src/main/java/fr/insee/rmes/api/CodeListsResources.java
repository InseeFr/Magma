package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insee.rmes.dto.codeList.CodeList;
import fr.insee.rmes.dto.codeList.CodeListNotation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.services.codelists.CodeListsServices;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/codelist",produces = {"application/json"})
@Tag(name = "Codelists", description = "Consultation Gestion API - Codelists")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		/*@ApiResponse(responseCode = "204", description = "No Content"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),*/
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		/*@ApiResponse(responseCode = "406", description = "Not Acceptable"),*/
		@ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})

    public class CodeListsResources {

	@Autowired
	CodeListsServices codeListsServices;

    @GET
    @GetMapping("/listeCode")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getAllCodesLists", summary = "List of codes",
            responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CodeList.class)))})
    public ResponseEntity <Object> getallCodesLists() throws RmesException {
        List <Object> jsonResult;
        jsonResult = codeListsServices.getAllCodesLists();
        if(jsonResult.isEmpty()){
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
        }else{
            return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
        }

    }

    @GET
    @GetMapping("/listeCode/{notation}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getCodesList", summary = "Get one codes list",responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CodeListNotation.class)))})
    public ResponseEntity <Object> getCodesList(@PathVariable(Constants.NOTATION) String notation) throws RmesException {
        JSONObject jsonResult;
        jsonResult = (JSONObject) codeListsServices.getCodesList(notation);
        if(Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult.toString())){
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
        }else{

            return new ResponseEntity<>(jsonResult.toMap(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
        }
    }

}
