package fr.insee.rmes.persistence;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.insee.rmes.model.ValidationStatus;
import fr.insee.rmes.utils.exceptions.RmesException;

@Service
public abstract class RdfService {

	@Value("${fr.insee.rmes.magma.lg1}")
	public String LG1;
	@Value("${fr.insee.rmes.magma.lg2}")
	public String LG2;	
	
	@Value("${fr.insee.rmes.magma.baseGraph}")
	public String BASE_GRAPH;
	
	@Value("${fr.insee.rmes.magma.concepts.baseURI}")
	public String CONCEPTS_BASE_URI;
	@Value("${fr.insee.rmes.magma.structures.baseURI}")
	public String STRUCTURES_BASE_URI;
	@Value("${fr.insee.rmes.magma.codeLists.baseURI}")
	public String CODELISTS_BASE_URI;
	
	@Value("${fr.insee.rmes.magma.codeLists.graph}")
	public Object CODELIST_GRAPH; 
	
	
	@Autowired
	protected RepositoryGestion repoGestion;
	
    protected static String buildRequest(String path, String fileName, Map<String, Object> params) throws RmesException {
        return FreeMarkerUtils.buildRequest(path, fileName, params);
    }
    
    protected JSONArray formatLabel(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", LG1);
        lg2.put("langue", LG2);
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
