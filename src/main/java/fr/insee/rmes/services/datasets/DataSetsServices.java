package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.model.datasets.Distributions;
import fr.insee.rmes.modelSwagger.dataset.Distribution;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONObject;

import java.net.MalformedURLException;


public interface DataSetsServices {

    String getListDataSets() throws RmesException, JsonProcessingException;

    String getDataSetByID(String id) throws RmesException, JsonProcessingException;

    String getDataSetByIDSummary(String id) throws RmesException, JsonProcessingException;

    Distribution findDistributions(String id) throws RmesException, JsonProcessingException;

    Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException;

    String patchDataset(String datasetId, String observationNumber, String token) throws RmesException, MalformedURLException;
}
