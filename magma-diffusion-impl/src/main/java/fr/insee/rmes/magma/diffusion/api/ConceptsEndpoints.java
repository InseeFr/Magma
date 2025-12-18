package fr.insee.rmes.magma.diffusion.api;


import fr.insee.rmes.magma.diffusion.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.magma.diffusion.model.Concept;
import fr.insee.rmes.magma.diffusion.model.ConceptIntituleInner;
import fr.insee.rmes.magma.diffusion.model.ConceptForList;
import fr.insee.rmes.magma.diffusion.model.NearbyConcept;
import fr.insee.rmes.magma.diffusion.queries.parameters.ConceptsRequestParametizer;
import fr.insee.rmes.magma.diffusion.utils.ConceptDTO;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessor requestProcessor;

    public ConceptsEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessor.queryToFindConcept()
                .with(new ConceptsRequestParametizer(id, "none"))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO != null) {
            if (conceptDTO.getHasLink()) {
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(new ConceptsRequestParametizer(conceptDTO.getUri()))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDTO.setNearbyConcepts(nearbyConceptList);
            }

            if (conceptDTO.getHasIntitulesAlternatifs()){
                List<ConceptIntituleInner> intitulesAlternatifs = requestProcessor.queryToFindConceptIntitulesAlternatifs()
                        .with(new ConceptsRequestParametizer(conceptDTO.getUri()))
                        .executeQuery()
                        .listResult(ConceptIntituleInner.class)
                        .result();

                //TODO modifier nom de ConceptsNearbyRequestParametizer + supprimer le paramètre TypeOrigine ?
                conceptDTO.setIntitulesAlternatifs(intitulesAlternatifs);

            }

            Concept concept = conceptDTO.transformDTOenConcept();

            return EndpointsUtils.toResponseEntity(concept);

        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @Override
    public ResponseEntity<List<ConceptForList>> getconceptsliste(String libelle) {
        String label = StringUtils.isEmpty(libelle) ? "" : libelle;
        List<ConceptDTO> listConceptDTOs = requestProcessor.queryToFindConcepts()
                .with(new ConceptsRequestParametizer("none", label))
                .executeQuery()
                .listResult(ConceptDTO.class)
                .result();

        listConceptDTOs.forEach(conceptDto -> {
            if (conceptDto.getHasLink()){
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(new ConceptsRequestParametizer(conceptDto.getUri()))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDto.setNearbyConcepts(nearbyConceptList);
            }
        });

        List<ConceptForList> concepts = listConceptDTOs.stream()
                .map(ConceptDTO::transformDTOenDefinition)
                .collect(Collectors.toList());

        return EndpointsUtils.toResponseEntity(concepts);

    }


}
