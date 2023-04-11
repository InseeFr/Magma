package fr.insee.rmes.services.organisations;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface OrganisationsServices {

    String getAllOrganisations() throws RmesException, IOException;
    String getOrganisationById(String id) throws RmesException, IOException;
}
