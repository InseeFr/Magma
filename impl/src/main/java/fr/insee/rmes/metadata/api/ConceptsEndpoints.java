package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Concept;
import fr.insee.rmes.metadata.model.ListeConceptsInner;
import fr.insee.rmes.metadata.model.NearbyConcept;
import fr.insee.rmes.metadata.queries.parameters.ConceptRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ConceptsNearbyRequestParametizer;
import fr.insee.rmes.metadata.utils.ConceptDTO;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessor requestProcessor;

    public ConceptsEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessor.queryToFindConcept()
                .with(new ConceptRequestParametizer(id, "none"))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO != null) {
            if (conceptDTO.getHasLink()) {
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(new ConceptsNearbyRequestParametizer(conceptDTO.getUri(), NearbyConcept.class))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDTO.setNearbyConcepts(nearbyConceptList);
            }

            Concept concept = conceptDTO.transformDTOenConcept();

            return EndpointsUtils.toResponseEntity(concept);

        } else {
            return ResponseEntity.notFound().build();

        }
    }

    @Override
    public ResponseEntity<List<ListeConceptsInner>> getconceptsliste(String libelle) {
        String label = StringUtils.isEmpty(libelle) ? "" : libelle;
        List<ConceptDTO> listConceptDTOs = requestProcessor.queryToFindConcepts()
                .with(new ConceptRequestParametizer("none", label))
                .executeQuery()
                .listResult(ConceptDTO.class)
                .result();

        listConceptDTOs.forEach(conceptDto -> {
            if (conceptDto.getHasLink()){
                List<NearbyConcept> nearbyConceptList = requestProcessor.queryToFindNearbyConcepts()
                        .with(new ConceptsNearbyRequestParametizer(conceptDto.getUri(), NearbyConcept.class))
                        .executeQuery()
                        .listResult(NearbyConcept.class).result();
                conceptDto.setNearbyConcepts(nearbyConceptList);
            }
        });

        List<ListeConceptsInner> concepts = listConceptDTOs.stream()
                .map(ConceptDTO::transformDTOenDefinition)
                .collect(Collectors.toList());

        return EndpointsUtils.toResponseEntity(concepts);

    }


}
