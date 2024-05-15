package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.model.datasets.Distributions;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;


public interface DataSetsServices {

    String getListDataSets(String dateMiseAJour) throws RmesException, JsonProcessingException;

    String getDataSetByID(String id) throws RmesException, JsonProcessingException;

    String getDataSetByIDSummary(String id) throws RmesException, JsonProcessingException;

    Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException;

    ResponseEntity<String> patchDataset(String datasetId, String patchDataset, String token) throws RmesException, MalformedURLException;
}
