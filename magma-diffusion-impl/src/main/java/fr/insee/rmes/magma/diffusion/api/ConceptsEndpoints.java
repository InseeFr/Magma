package fr.insee.rmes.magma.diffusion.api;


import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.model.LocalisedLabel;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import fr.insee.rmes.magma.diffusion.queries.parameters.ConceptsRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessor requestProcessor;

    public ConceptsEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessor.queryToFindConcept()
                .with(ConceptsRequestParametizer.ofId(id))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO != null) {
            if (Boolean.TRUE.equals(conceptDTO.getHasLink())) {
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(ConceptsRequestParametizer.ofUri(conceptDTO.getUri()))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDTO.setNearbyConcepts(nearbyConceptList);
            }

            if (Boolean.TRUE.equals(conceptDTO.getHasIntitulesAlternatifs())){
                List<LocalisedLabel> intitulesAlternatifs = requestProcessor.queryToFindConceptIntitulesAlternatifs()
                        .with(ConceptsRequestParametizer.ofUri(conceptDTO.getUri()))
                        .executeQuery()
                        .listResult(LocalisedLabel.class)
                        .result();

                conceptDTO.setIntitulesAlternatifs(intitulesAlternatifs);

            }

            Concept concept = conceptDTO.transformDTOenConcept();

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

        listConceptDTOs.forEach(conceptDto -> {
            if (Boolean.TRUE.equals(conceptDto.getHasLink())){
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(ConceptsRequestParametizer.ofUri(conceptDto.getUri()))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDto.setNearbyConcepts(nearbyConceptList);
            }
        });

        List<ConceptForList> concepts = listConceptDTOs.stream()
                .map(ConceptDTO::transformDTOenDefinition)
                .toList();

        return EndpointsUtils.toResponseEntity(concepts);

    }


}
