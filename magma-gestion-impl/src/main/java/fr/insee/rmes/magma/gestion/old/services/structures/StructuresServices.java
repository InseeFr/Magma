package fr.insee.rmes.magma.gestion.old.services.structures;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;



@Service
public interface StructuresServices {

	String getAllStructures(String dateMiseAJour) throws RmesException;

	String getStructure(String id) throws RmesException, JsonProcessingException;

	String getAllComponents(String dateMiseAJour) throws RmesException;

	JSONObject getComponent(String id) throws RmesException;


    String getStructureDateMAJ(String id) throws RmesException, JsonProcessingException;

	JSONObject getComponentDateMAJ(String id) throws RmesException;
	String getSlice(String id) throws RmesException;
}
