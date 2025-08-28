package fr.insee.rmes.metadata.api;


import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.AireDAttractionDesVilles2020;
import fr.insee.rmes.metadata.model.Concept;
import fr.insee.rmes.metadata.model.ConceptSuivant;
import fr.insee.rmes.metadata.queries.parameters.ConceptRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.ConceptSuivantRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import fr.insee.rmes.metadata.utils.ConceptDTO;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
                .with(new ConceptRequestParametizer(id, Concept.class, "none"))
                .executeQuery()
                .singleResult(ConceptDTO.class).result();

        if (conceptDTO.getHasLink()){
            List<ConceptSuivant> conceptSuivantList = requestProcessor.queryToFindConceptsSuivants()
                    .with(new ConceptSuivantRequestParametizer(conceptDTO.getUri(), ConceptSuivant.class))
                    .executeQuery()
                    .listResult(ConceptSuivant.class).result();
            conceptDTO.setConceptsSuivants(conceptSuivantList);
        }

        Concept concept = conceptDTO.transformDTOenConcept();

        return EndpointsUtils.toResponseEntity(concept);
    }

    @Override
    public ResponseEntity<List<Concept>> getconceptsliste (String libelle){
        String label = StringUtils.isEmpty(libelle) ? "" : libelle;
        List<ConceptDTO> listConceptDTOs = requestProcessor.queryToFindConcepts()
                .with(new ConceptRequestParametizer("none", Concept.class, label))
                .executeQuery()
                .listResult(ConceptDTO.class)
                .result();

        listConceptDTOs.forEach(conceptDto -> {
            if (conceptDto.getHasLink()){
                List<ConceptSuivant> conceptSuivantList = requestProcessor.queryToFindConceptsSuivants()
                        .with(new ConceptSuivantRequestParametizer(conceptDto.getUri(), ConceptSuivant.class))
                        .executeQuery()
                        .listResult(ConceptSuivant.class).result();
                conceptDto.setConceptsSuivants(conceptSuivantList);
            }
        });

        List<Concept> concepts = listConceptDTOs.stream()
                .map(conceptDTO -> conceptDTO.transformDTOenConcept())
                .collect(Collectors.toList());
        return EndpointsUtils.toResponseEntity(concepts);
    }

}
