package fr.insee.rmes.magma.gestion.old.services.datasets;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.insee.rmes.magma.gestion.old.dto.datasets.PatchDatasetDTO;
import fr.insee.rmes.magma.gestion.old.model.datasets.Distributions;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import fr.insee.security.User;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.Optional;


public interface DataSetsServices {

    String getListDataSets(String dateMiseAJour) throws RmesException, JsonProcessingException;

    String getDataSetByID(String id) throws RmesException, JsonProcessingException;

    String getDataSetByIDSummary(String id) throws RmesException, JsonProcessingException;

    Distributions[] getDataSetDistributionsById(String id) throws RmesException, JsonProcessingException;

    ResponseEntity<String> patchDataset(String datasetId, PatchDatasetDTO patchDataset, String token, Optional<User> user) throws RmesException, MalformedURLException;
}
