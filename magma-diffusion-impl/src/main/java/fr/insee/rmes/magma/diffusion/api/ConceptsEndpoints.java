package fr.insee.rmes.magma.diffusion.api;


import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessorDiffusion;
import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import fr.insee.rmes.magma.diffusion.queries.parameters.ConceptsRequestParametizer;
import fr.insee.rmes.magma.diffusion.services.ConceptService;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessorDiffusion requestProcessorDiffusion;
    private final ConceptService conceptService;

    public ConceptsEndpoints(RequestProcessorDiffusion requestProcessorDiffusion, ConceptService conceptService) {
        this.requestProcessorDiffusion = requestProcessorDiffusion;
        this.conceptService = conceptService;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessorDiffusion.queryToFindConcept()
                .with(ConceptsRequestParametizer.ofId(id))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO != null) {
            if (conceptDTO.hasLinkValue()) {
               conceptDTO = getNearbyConcepts(conceptDTO);
            }

            if (conceptDTO.hasIntitulesAlternatifsValue()) {
                List<LocalisedLabel> intitulesAlternatifs = requestProcessorDiffusion.queryToFindConceptIntitulesAlternatifs()
                        .with(ConceptsRequestParametizer.ofUri(conceptDTO.uri()))
                        .executeQuery()
                        .listResult(LocalisedLabel.class)
                        .result();

                conceptDTO = conceptDTO.withIntitulesAlternatifs(intitulesAlternatifs);

            }

            Concept concept = conceptService.transformDTOenConcept(conceptDTO);

            return EndpointsUtils.toResponseEntity(concept);

        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @Override
    public ResponseEntity<List<ConceptForList>> getconceptsliste(String label, String collect) {
        String libelle = StringUtils.isEmpty(label) ? "" : label;
        String collection = StringUtils.isEmpty(collect) ? "" : collect;
        List<ConceptDTO> listConceptDTOs = requestProcessorDiffusion.queryToFindConcepts()
                .with(new ConceptsRequestParametizer(libelle, collection))
                .executeQuery()
                .listResult(ConceptDTO.class)
                .result();

        List<ConceptDTO> listConceptDTOsWithLinks = listConceptDTOs.stream()
                .map(conceptDto -> {
                    if (conceptDto.hasLinkValue()) {
                        return getNearbyConcepts(conceptDto);
                    }
                    return conceptDto;
                })
                .toList();
        List<ConceptForList> concepts = listConceptDTOsWithLinks.stream()
                .map(conceptService::transformDTOenDefinition)
                .toList();

        return EndpointsUtils.toResponseEntity(concepts);

    }

    private ConceptDTO getNearbyConcepts(ConceptDTO conceptDto) {
        List<NearbyConcept> nearbyConceptList = requestProcessorDiffusion.queryToFindNearbyConcepts()
                .with(ConceptsRequestParametizer.ofUri(conceptDto.uri()))
                .executeQuery()
                .listResult(NearbyConcept.class)
                .result();
        return conceptDto.withNearbyConcepts(nearbyConceptList);
    }

}
