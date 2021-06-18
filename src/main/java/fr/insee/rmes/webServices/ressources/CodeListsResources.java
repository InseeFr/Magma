package fr.insee.rmes.webServices.ressources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.exceptions.RmesException;
import fr.insee.rmes.webServices.codeLists.CodeListsServices;
import fr.insee.rmes.webServices.concepts.ConceptsServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/magma")
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

public class CodeListsResources {

	@Autowired
	CodeListsServices codeListsServices;

    @GET()
    @Path("/listesCodes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getAllCodesLists", summary = "Get all codes lists")
    public Response getAllCodesLists() {
        String jsonResultat;
        try {
            jsonResultat = codeListsServices.getAllCodesLists();
        } catch (RmesException e) {
            return Response.status(e.getStatus()).entity(e.getDetails()).type(MediaType.TEXT_PLAIN).build();
        }
        return Response.status(HttpStatus.SC_OK).entity(jsonResultat).build();
    }

    @GET()
    @Path("/listeCode/{notation}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(operationId = "getCodesList", summary = "Get one codes list")
    public Response getCodesList(@PathParam(Constants.NOTATION) String notation) {
        String jsonResultat;
        try {
            jsonResultat = codeListsServices.getCodesList(notation);
        } catch (RmesException e) {
            return Response.status(e.getStatus()).entity(e.getDetails()).type(MediaType.TEXT_PLAIN).build();
        }
        return Response.status(HttpStatus.SC_OK).entity(jsonResultat).build();
    }

}
