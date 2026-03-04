package fr.insee.rmes.magma.gestion.old.services.structures;

import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;


@Service
public interface StructuresServices {

	String getAllStructures(String dateMiseAJour) throws RmesException;

	String getStructure(String id) throws RmesException, JacksonException;

	String getAllComponents(String dateMiseAJour) throws RmesException;

	JSONObject getComponent(String id) throws RmesException;


    String getStructureDateMAJ(String id) throws RmesException, JacksonException;

	JSONObject getComponentDateMAJ(String id) throws RmesException;
	String getSlice(String id) throws RmesException;
}
