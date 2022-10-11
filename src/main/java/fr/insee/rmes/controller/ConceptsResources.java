package fr.insee.rmes.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.modelSwagger.concept.AllConceptModelSwagger;
import fr.insee.rmes.modelSwagger.concept.ConceptByIdModelSwagger;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.insee.rmes.services.concepts.ConceptsServices;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Concepts", description = "Consultation Gestion API - Concepts")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		@ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})
public class ConceptsResources {

	@Autowired
	ConceptsServices conceptsService;
	
	@GET
	@GetMapping("/concept/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getDetailedConcept", summary = "Get one concept",security = @SecurityRequirement(name = "bearerScheme"), responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = AllConceptModelSwagger.class)))})
	public ResponseEntity <String> getDetailedConcept(@Parameter(required = true, description = "Identifiant du concept (format : c[0-9]{4})", schema = @Schema(pattern = "c[0-9]{4}", type = "string"), example = "c2066")
			@PathVariable("id") String id,
			@RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour)
			throws RmesException, JsonProcessingException {

		if (!boolDateMiseAJour){
			String jsonResult = conceptsService.getDetailedConcept(id);
			if(jsonResult.isEmpty()){
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
			}else{
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		}
		else{
			String jsonResult = conceptsService.getDetailedConceptDateMAJ(id);
			if(jsonResult.isEmpty()){
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
			}else{
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		}

	}

	@GET
	@GetMapping("/concepts")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllConcepts", summary = "List of concepts",security = @SecurityRequirement(name = "bearerScheme"), responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ConceptByIdModelSwagger.class)))})
	public ResponseEntity <String> getAllConcepts() throws RmesException {
		String jsonResult = conceptsService.getAllConcepts();
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		}else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

}
