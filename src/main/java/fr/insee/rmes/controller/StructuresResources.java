package fr.insee.rmes.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.modelSwagger.structure.AllStructureModelSwagger;
import fr.insee.rmes.modelSwagger.structure.StructureByIdModelSwagger;
import fr.insee.rmes.modelSwagger.component.AllComponentModelSwagger;
import fr.insee.rmes.modelSwagger.component.ComponentByIdModelSwagger;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fr.insee.rmes.services.structures.StructuresServices;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Structures/Composants", description = "Consultation Magma API - Structures/Composants")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		@ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})

public class StructuresResources {

	private static final String NO_RESULT_FOUND = "No result found";
	@Autowired
	StructuresServices structuresServices;

	@GET
	@GetMapping("/structures")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllStructures", summary = "Get all structures",security = @SecurityRequirement(name = "bearerScheme"), responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = AllStructureModelSwagger.class)))})
	public ResponseEntity <String> getAllStructures() throws RmesException {
		String jsonResult = structuresServices.getAllStructures();
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(NO_RESULT_FOUND);
		}else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

	@GET
	@GetMapping("/structure/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getStructure", summary = "Get a structure",security = @SecurityRequirement(name = "bearerScheme"), responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = StructureByIdModelSwagger.class)))})
	public ResponseEntity <String> getStructure(@PathVariable(Constants.ID) String id,
												@RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour
	) throws RmesException, JsonProcessingException {

		// par défaut ce booléen est faux et donc on renvoie tout les infos d'un dataset
		if (!boolDateMiseAJour){
			String jsonResult = structuresServices.getStructure(id);
			if(jsonResult.isEmpty()){
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(NO_RESULT_FOUND);
			}else {
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		}
		else {
			String jsonResult = structuresServices.getStructureDateMAJ(id);
			if(jsonResult.isEmpty()){
				return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(NO_RESULT_FOUND);
			}else {
				return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
			}
		}

	}

	@GetMapping("/composants")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllComponents", summary = "Get all components",security = @SecurityRequirement(name = "bearerScheme"),
			responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array",implementation = AllComponentModelSwagger.class)))})
	public ResponseEntity<String> getAllComponents() throws RmesException {
		String jsonResult = structuresServices.getAllComponents();
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
		}else{
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}

	}

	@GetMapping("/composant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getComponent", summary = "Get a component",security = @SecurityRequirement(name = "bearerScheme"), responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array",implementation = ComponentByIdModelSwagger.class)))})
	public ResponseEntity<Object> getComponentById(
			@PathVariable(Constants.ID) String id,
			@RequestParam(name = "dateMiseAJour", defaultValue = "false") Boolean boolDateMiseAJour
	) {
		if (!boolDateMiseAJour){
			String jsonResultat;
			try {
				jsonResultat = structuresServices.getComponent(id).toString();
			} catch (RmesException e) {
				return ResponseEntity.status(e.getStatus()).body(e.getDetails());
			}
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResultat);
		}

		else{
			String jsonResultat;
			try {
				jsonResultat = structuresServices.getComponentDateMAJ(id).toString();
			} catch (RmesException e) {
				return ResponseEntity.status(e.getStatus()).body(e.getDetails());
			}
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResultat);
		}

	}
}
