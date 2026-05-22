package fr.insee.rmes.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.model.datasets.Distributions;
import fr.insee.security.User;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.http.ResponseEntity;
import tools.jackson.core.JacksonException;

import java.net.MalformedURLException;
import java.util.Optional;


public interface DataSetsServices {

    String getListDataSets(String dateMiseAJour) throws RmesException, JacksonException;

    String getDataSetByID(String id) throws RmesException, JacksonException;

    String getDataSetByIDSummary(String id) throws RmesException, JacksonException;

    Distributions[] getDataSetDistributionsById(String id) throws RmesException, JacksonException;

    ResponseEntity<String> patchDataset(String datasetId, PatchDatasetDTO patchDataset, String token, Optional<User> user) throws RmesException, MalformedURLException;
}
