package fr.insee.rmes.magma.services;

import fr.insee.rmes.magma.model.RapportQualite;
import fr.insee.rmes.magma.utils.RapportQualiteDTO;

public interface RapportQualiteService {
    RapportQualite transformDTOintoRapportQualite(RapportQualiteDTO rapportQualiteDTO);
}
