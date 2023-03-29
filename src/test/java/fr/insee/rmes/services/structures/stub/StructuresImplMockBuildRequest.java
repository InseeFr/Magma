package fr.insee.rmes.services.structures.stub;

import fr.insee.rmes.services.structures.StructuresImpl;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StructuresImplMockBuildRequest extends StructuresImpl {

    @Override
    protected String buildRequest(String path, String fileName, Map<String, Object> params) throws RmesException {
        switch (fileName) {
            case "getStructure.ftlh":
            case "getStructureComponents.ftlh":
            case "getComponent.ftlh":
            case "getComponentChildren.ftlh":
                return fileName;

            default:
                throw new RuntimeException("Not implemented " + fileName);
        }

    }
}
