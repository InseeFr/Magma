package fr.insee.rmes.utils.request;

import fr.insee.rmes.model.datasets.User;
import org.json.JSONObject;

import java.util.Base64;

public class TokenManagement {

    public static User getUserFromJWT(String jwt){
        String[] parts = jwt.split("\\.");
        JSONObject payload = new JSONObject(decode(parts[1]));
        return new User(payload.getString("preferred_username"),payload.getString("email"));
    }

    public static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }
}
