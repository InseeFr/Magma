package fr.insee.rmes.api;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.insee.rmes.dto.Structure.StructureListId;
import fr.insee.rmes.dto.codeList.CodeList;
import fr.insee.rmes.dto.component.ComponentID;
import fr.insee.rmes.dto.component.Components;
import fr.insee.rmes.services.components.ComponentServices;
import fr.insee.rmes.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fr.insee.rmes.utils.exceptions.RmesException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;



    @RestController
    @RequestMapping(value="/",produces = {"application/json"})
    @Tag(name = "Composants", description = "Consultation Gestion API - composants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",content = {@Content }),
            @ApiResponse(responseCode = "404", description = "Not found",content = {@Content }),
            @ApiResponse(responseCode = "500", description = "Internal server error",content = {@Content })})


    public class ComponentsResources {

        @Autowired
        ComponentServices componentServices;

        @GetMapping("/composants")
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(operationId = "getAllComponents", summary = "Get all components",
                responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array",implementation = Components.class)))})
        public ResponseEntity<String> getAllComponents() throws RmesException {
            String jsonResult = componentServices.getAllComponents();
            if(jsonResult.isEmpty()){
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }

        }

        @GetMapping("/composant/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        @Operation(operationId = "getComponent", summary = "Get a component", responses = { @ApiResponse(content = @Content(mediaType = "application/json", schema = @Schema(type = "array",implementation = ComponentID.class)))})
        public ResponseEntity <String> getComponent(@PathVariable(Constants.ID) String id) throws RmesException {
            String jsonResult = componentServices.getComponent(id);
            if(jsonResult.isEmpty()){
                return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body("No result found");
            }else {
                return ResponseEntity.status(HttpStatus.SC_OK).body(jsonResult);
            }
        }

    }
