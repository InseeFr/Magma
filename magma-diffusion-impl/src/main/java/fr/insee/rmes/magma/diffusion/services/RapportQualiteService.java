package fr.insee.rmes.magma.diffusion.services;

import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.RapportQualite;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;

public interface RapportQualiteService {
    RapportQualite transformDTOenRapportQualite(RapportQualiteDTO rapportQualiteDTO);
}
