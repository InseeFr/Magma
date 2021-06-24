package fr.insee.rmes.persistence;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import fr.insee.rmes.model.ValidationStatus;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

public abstract class RdfService {

	@Value("${fr.insee.rmes.magma.lg1}")
	public String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public String LG2;	
	@Value("${fr.insee.rmes.magma.baseGraph}")
	public String BASE_GRAPH;
	
	@Autowired
	protected RepositoryGestion repoGestion;
	
    protected static String buildRequest(String fileName, HashMap<String, Object> params) throws RmesException {
        return FreeMarkerUtils.buildRequest("concepts/", fileName, params);
    }
    
    protected JSONArray formatLabel(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("prefLabelLg1"));
        lg2.put("contenu", obj.getString("prefLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }
    
    protected String getValidationState(String validationState){
        if(ValidationStatus.VALIDATED.toString().equalsIgnoreCase(validationState)){
            return "Publiée";
        }
        if(ValidationStatus.MODIFIED.toString().equalsIgnoreCase(validationState)){
            return "Provisoire, déjà publiée";
        }
        if(ValidationStatus.UNPUBLISHED.toString().equalsIgnoreCase(validationState)){
            return "Provisoire, jamais publiée";
        }
        return validationState;
    }
}
