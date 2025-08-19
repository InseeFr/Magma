package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.Concept;
import fr.insee.rmes.metadata.model.ConceptSuivant;
import fr.insee.rmes.metadata.queries.parameters.ConceptRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ConceptSuivantRequestParametizer;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import fr.insee.rmes.metadata.utils.ConceptDTO;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ConceptsEndpoints implements ConceptsApi {

    private final RequestProcessor requestProcessor;

    public ConceptsEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Concept> getconcept(String id) {
        ConceptDTO conceptDTO = requestProcessor.queryToFindConcept()
                .with(new ConceptRequestParametizer(id, Concept.class))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO.getHasLink()){
            List<ConceptSuivant> conceptSuivantList = requestProcessor.queryToFindConceptSuivants()
                    .with(new ConceptSuivantRequestParametizer(conceptDTO.getUri(), ConceptSuivant.class))
                    .executeQuery()
                    .listResult(ConceptSuivant.class).result();
            conceptDTO.setConceptsSuivants(conceptSuivantList);
        }

        Concept concept = conceptDTO.transformDTOenConcept();

        return EndpointsUtils.toResponseEntity(concept);
    }

}
