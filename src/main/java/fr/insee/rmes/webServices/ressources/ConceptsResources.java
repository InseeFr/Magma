package fr.insee.rmes.webServices.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.utils.exceptions.RmesException;
import fr.insee.rmes.webServices.concepts.ConceptsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/magma2")
@Tag(name = "Concepts", description = "Consultation Gestion API")
@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Success"),
		@ApiResponse(responseCode = "204", description = "No Content"),
		@ApiResponse(responseCode = "400", description = "Bad Request"),
		@ApiResponse(responseCode = "401", description = "Unauthorized"),
		@ApiResponse(responseCode = "403", description = "Forbidden"),
		@ApiResponse(responseCode = "404", description = "Not found"),
		@ApiResponse(responseCode = "406", description = "Not Acceptable"),
		@ApiResponse(responseCode = "500", description = "Internal server error")})
public class ConceptsResources {

	@Autowired
	ConceptsServices conceptsService;
	
	@GET
	@GetMapping("/concept/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getDetailedConcept", summary = "Informations sur la définition d'un concept statistique de l'Insee")
	public Response getDetailedConcept(@Parameter(required = true, description = "Identifiant du concept (format : c[0-9]{4})", schema = @Schema(pattern = "c[0-9]{4}", type = "string"), example = "c2066") @PathVariable("id") String id) {
		String jsonResultat;
		try {
			jsonResultat = conceptsService.getDetailedConcept(id);
		} catch (RmesException e) {
			return Response.status(e.getStatus()).entity(e.getDetails()).type(MediaType.TEXT_PLAIN).build();
		}
		return Response.status(HttpStatus.SC_OK).entity(jsonResultat).build();
	}

	@GET
	@GetMapping("/concepts")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "getAllConcepts", summary = "Get all concepts")
	public Response getAllConcepts() {
		String jsonResultat;
		try {
			jsonResultat = conceptsService.getAllConcepts();
		} catch (RmesException e) {
			return Response.status(e.getStatus()).entity(e.getDetails()).type(MediaType.TEXT_PLAIN).build();
		}
		return Response.status(HttpStatus.SC_OK).entity(jsonResultat).build();
	}

}
