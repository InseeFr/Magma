package fr.insee.rmes.magma.gestion.old.persistence;

import fr.insee.rmes.magma.gestion.old.model.ValidationStatus;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public abstract class RdfService {

	
	@Autowired
	protected RepositoryGestion repoGestion;

    @Autowired
    protected Config config;
    protected final FreeMarkerUtils freeMarkerUtils;

    protected RdfService(){
        this(FreeMarkerUtils.getInstance());
    }

    protected String buildRequest(String path, String fileName, Map<String, Object> params) throws RmesException {
        return this.freeMarkerUtils.buildRequest(path, fileName, params);
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

    protected JSONArray formatNom(JSONObject obj) {
        JSONArray nom = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("altLabelLg1"));
        lg2.put("contenu", obj.getString("altLabelLg2"));

        nom.put(lg1);
        nom.put(lg2);

        return nom;
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
