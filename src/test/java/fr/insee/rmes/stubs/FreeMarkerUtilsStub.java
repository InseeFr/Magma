package fr.insee.rmes.stubs;

import fr.insee.rmes.persistence.FreeMarkerUtils;
import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.Map;

public class FreeMarkerUtilsStub extends FreeMarkerUtils {

    @Override
    public String buildRequest(String root, String fileName, Map<String, Object> params) {
        return fileName;
    }
}
