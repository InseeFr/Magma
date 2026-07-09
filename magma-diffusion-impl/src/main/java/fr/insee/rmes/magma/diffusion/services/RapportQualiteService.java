package fr.insee.rmes.magma.diffusion.services;

import fr.insee.rmes.magma.model.RapportQualite;
import fr.insee.rmes.magma.diffusion.utils.RapportQualiteDTO;

public interface RapportQualiteService {
    RapportQualite transformDTOenRapportQualite(RapportQualiteDTO rapportQualiteDTO);
}
