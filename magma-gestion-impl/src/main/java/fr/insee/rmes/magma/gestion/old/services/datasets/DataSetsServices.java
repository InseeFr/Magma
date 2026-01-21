package fr.insee.rmes.magma.gestion.old.services.datasets;

import tools.jackson.core.JacksonException;
import fr.insee.rmes.magma.gestion.old.datasets.PatchDatasetDTO;
import fr.insee.rmes.magma.gestion.old.model.datasets.Distributions;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import fr.insee.rmes.magma.gestion.security.User;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.util.Optional;


public interface DataSetsServices {

    String getListDataSets(String dateMiseAJour) throws RmesException, JacksonException;

    String getDataSetByID(String id) throws RmesException, JacksonException;

    String getDataSetByIDSummary(String id) throws RmesException, JacksonException;

    Distributions[] getDataSetDistributionsById(String id) throws RmesException, JacksonException;

    ResponseEntity<String> patchDataset(String datasetId, PatchDatasetDTO patchDataset, String token, Optional<User> user) throws RmesException, MalformedURLException;
}
