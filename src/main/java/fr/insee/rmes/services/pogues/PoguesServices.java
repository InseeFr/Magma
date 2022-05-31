package fr.insee.rmes.services.pogues;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PoguesServices {
    String getAllSeriesLists(Boolean survey) throws RmesException, IOException;

    String getSerieById(String id) throws RmesException, IOException;

    String getOperationsBySerieId(String id) throws RmesException, IOException;

    String getOperationByCode(String id) throws RmesException, IOException;
}
