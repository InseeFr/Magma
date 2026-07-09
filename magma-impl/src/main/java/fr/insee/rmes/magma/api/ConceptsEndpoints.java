package fr.insee.rmes.magma.api;

import fr.insee.rmes.magma.api.*;

import fr.insee.rmes.magma.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.model.Concept;
import fr.insee.rmes.magma.model.ConceptForList;
import fr.insee.rmes.magma.model.LocalisedContenu;
import fr.insee.rmes.magma.model.NearbyConcept;
import fr.insee.rmes.magma.queries.parameters.ConceptsRequestParametizer;
import fr.insee.rmes.magma.services.ConceptService;
import fr.insee.rmes.magma.utils.ConceptDTO;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessor requestProcessor;
    private final ConceptService conceptService;

    public ConceptsEndpoints(RequestProcessor requestProcessor, ConceptService conceptService) {
        this.requestProcessor = requestProcessor;
        this.conceptService = conceptService;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessor.queryToFindConcept()
                .with(ConceptsRequestParametizer.ofId(id))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO != null) {
            if (conceptDTO.hasLinkValue()) {
               conceptDTO = getNearbyConcepts(conceptDTO);
            }

            if (conceptDTO.hasIntitulesAlternatifsValue()) {
                List<LocalisedContenu> intitulesAlternatifs = requestProcessor.queryToFindConceptIntitulesAlternatifs()
                        .with(ConceptsRequestParametizer.ofUri(conceptDTO.uri()))
                        .executeQuery()
                        .listResult(LocalisedContenu.class)
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
        List<ConceptDTO> listConceptDTOs = requestProcessor.queryToFindConcepts()
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
        List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                .with(ConceptsRequestParametizer.ofUri(conceptDto.uri()))
                .executeQuery()
                .listResult(NearbyConcept.class)
                .result();
        return conceptDto.withNearbyConcepts(nearbyConceptList);
    }

}
