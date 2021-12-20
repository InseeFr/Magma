package fr.insee.rmes.services.components;
import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;


@Service
public interface ComponentServices {

    String getAllComponents() throws RmesException;

    String getComponent(String id) throws RmesException;


}
