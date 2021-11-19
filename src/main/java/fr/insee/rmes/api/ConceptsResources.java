package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insee.rmes.dto.codeList.CodeList;
import fr.insee.rmes.dto.ConceptList;
import io.swagger.v3.oas.annotations.media.Content;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.services.concepts.ConceptsServices;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/concepts",produces = {"application/json"})
@Tag(name = "Concepts", description = "Consultation Gestion API")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		/*@ApiResponse(responseCode = "204", description = "No Content"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),*/
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		/*@ApiResponse(responseCode = "406", description = "Not Acceptable"),*/
		@ApiResponse(responseCode = "500", description = "Internal server error")})
public class ConceptsResources {

	@Autowired
	ConceptsServices conceptsService;
	
	@GET
	@GetMapping("/concept/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getDetailedConcept", summary = "Informations sur la définition d'un concept statistique de l'Insee", responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ConceptList.class)))})
	public ResponseEntity <Object> getDetailedConcept(@Parameter(required = true, description = "Identifiant du concept (format : c[0-9]{4})", schema = @Schema(pattern = "c[0-9]{4}", type = "string"), example = "c2066") @PathVariable("id") String id) throws RmesException {
		JSONObject jsonResult;
		jsonResult = (JSONObject) conceptsService.getDetailedConcept(id);
		if (Objects.isNull(jsonResult) || StringUtils.isEmpty(jsonResult.toString())) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		} else {
			return new ResponseEntity(jsonResult.toMap(), org.springframework.http.HttpStatus.valueOf(HttpStatus.SC_OK));
		}
	}

	@GET
	@GetMapping("/concepts")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllConcepts", summary = "Get all concepts", responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ConceptList.class)))})
	public ResponseEntity <Object> getAllConcepts() throws RmesException {
		List jsonResult;
		jsonResult = conceptsService.getAllConcepts();
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		}else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

}
