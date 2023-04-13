package fr.insee.rmes.persistence;

import fr.insee.rmes.model.ValidationStatus;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public abstract class RdfService {

	
	@Autowired
	protected RepositoryGestion repoGestion;

    @Autowired(required = false)
    protected Config config;
	
    protected static String buildRequest(String path, String fileName, Map<String, Object> params) throws RmesException {
        return FreeMarkerUtils.buildRequest(path, fileName, params);
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
