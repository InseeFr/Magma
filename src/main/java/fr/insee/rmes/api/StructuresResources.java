package fr.insee.rmes.api;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insee.rmes.dto.Structure.StructureListId;
import fr.insee.rmes.dto.Structure.StructureList;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.services.structures.StructuresServices;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value="/",produces = {"application/json"})
@Tag(name = "Structures", description = "Consultation Gestion API - Structures")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
		@ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
		@ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})

public class StructuresResources {

	@Autowired
	StructuresServices structuresServices;

	@GET
	@GetMapping("/structures")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllStructures", summary = "Get all structures", responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = StructureList.class)))})
	public ResponseEntity <String> getAllStructures() throws RmesException {
		String jsonResult = structuresServices.getAllStructures();
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		}else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}

	@GET
	@GetMapping("/structure/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getStructure", summary = "Get a structure", responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array", implementation = StructureListId.class)))})
	public ResponseEntity <String> getStructure(@PathVariable(Constants.ID) String id) throws RmesException {
		String jsonResult = structuresServices.getStructure(id);
		if(jsonResult.isEmpty()){
			return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
		}else {
			return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
		}
	}
}
