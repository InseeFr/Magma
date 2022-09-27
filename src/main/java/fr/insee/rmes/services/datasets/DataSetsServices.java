package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;



@Service
public interface DataSetsServices {

    String getListDataSets() throws RmesException, JsonProcessingException;


    String getDataSetByID(String id) throws RmesException, JsonProcessingException;

    String getDataSetByIDFilterByDateMaj(String id) throws RmesException, JsonProcessingException;
}
