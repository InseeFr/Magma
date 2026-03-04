package fr.insee.rmes.magma.diffusion.services;


import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;

public interface ConceptService {
    Concept transformDTOenConcept(ConceptDTO conceptDTO);
    ConceptForList transformDTOenDefinition(ConceptDTO conceptDTO);
}