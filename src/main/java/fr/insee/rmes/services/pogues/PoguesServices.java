package fr.insee.rmes.services.pogues;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;

@Service
public interface PoguesServices {
    String getAllCodesLists(Boolean survey) throws RmesException;

    String getCodesList(String id) throws RmesException;

    String getOperationsBySerie(String id) throws RmesException;

    String getOperationByCode(String id) throws RmesException;
}
