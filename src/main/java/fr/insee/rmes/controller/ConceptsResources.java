package fr.insee.rmes.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.modelSwagger.concept.AllConceptModelSwagger;
import fr.insee.rmes.modelSwagger.concept.CollectionOfConceptsModelSwagger;
import fr.insee.rmes.modelSwagger.concept.ConceptByIdModelSwagger;
import fr.insee.rmes.modelSwagger.concept.SetOfConceptsModelSwagger;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@GetMapping(path = "/concept/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "getDetailedConcept", summary = "Get one concept", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = AllConceptModelSwagger.class)))})
	public ResponseEntity<String> getDetailedConcept(@Parameter(required = true, description = "Identifiant du concept (format : c[0-9]{4})", schema = @Schema(pattern = "c[0-9]{4}", type = "string"), example = "c2066")
													 @PathVariable("id") String id,
													 @RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour)
			throws RmesException, JsonProcessingException {

		if (!boolDateMiseAJour) {
			String jsonResult = conceptsService.getDetailedConcept(id);
			if (jsonResult.isEmpty()) {
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
			} else {
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		} else {
			String jsonResult = conceptsService.getDetailedConceptDateMAJ(id);
			if (jsonResult.isEmpty()) {
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
			} else {
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		}

	}

	@GetMapping(path = "/concepts", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "getAllConcepts", summary = "List of concepts", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = ConceptByIdModelSwagger.class)))})
	public ResponseEntity<String> getAllConcepts(@RequestParam(required = false) @Parameter(description = "Date of last update. Example: 2023-01-31") String dateMiseAJour) throws RmesException {
		if (dateMiseAJour == null) {
			dateMiseAJour = "";
		}
		String jsonResult = conceptsService.getAllConcepts(dateMiseAJour);
		if (jsonResult.isEmpty()) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		} else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

	@GetMapping(path = "/concepts/collection{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "getCollectionOfConcepts", summary = "List of collection's concepts", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = CollectionOfConceptsModelSwagger.class)))})
	public ResponseEntity<String> getCollectionOfConcepts(@Parameter(required = true, description = "Identifiant interne de la collection ", schema = @Schema(type = "string"), example = "definitions-insee-fr")
														  @PathVariable("id") String id) throws RmesException, JsonProcessingException {
		String jsonResult = conceptsService.getCollectionOfConcepts(id);
		if (jsonResult.isEmpty()) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

	@GetMapping(path = "/concepts/collection/{id}/concepts", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(operationId = "getSetOfConceptsInACollection", summary = "Set of concepts in a collection", security = @SecurityRequirement(name = "bearerScheme"), responses = {@ApiResponse(content = @Content(schema = @Schema(type = "array", implementation = SetOfConceptsModelSwagger.class)))})
	public ResponseEntity<String> getSetOfConceptsInACollection(
			@Parameter(required = true,
					description = "Identifiant interne de la collection ",
					schema = @Schema(type = "string"), example = "definitions-insee-fr") @PathVariable("id") String id
	) throws RmesException, JsonProcessingException {
		String jsonResult = conceptsService.getSetOfConceptsInACollection(id);

		if (jsonResult.isEmpty()) {
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
		} else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}
	}


