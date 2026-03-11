package fr.insee.rmes.magma.gestion.api;

import fr.insee.rmes.magma.gestion.api.requestprocessor.RequestProcessorGestion;
import fr.insee.rmes.magma.gestion.model.AllListCode;
import fr.insee.rmes.magma.gestion.model.ListCodeById;
import fr.insee.rmes.magma.gestion.queries.parameters.CodesListRequestParametizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CodesListEndpoints implements CodesListsApi {

    private final RequestProcessorGestion requestProcessor;
    private final int perPage;

    public CodesListEndpoints(RequestProcessorGestion requestProcessor,
                              @Value("${fr.insee.rmes.magma.perPage}") int perPage) {
        this.requestProcessor = requestProcessor;
        this.perPage = perPage;
    }

    @Override
    public ResponseEntity<List<AllListCode>> getAllCodesLists(String dateMiseAJour) {
        if (dateMiseAJour == null || dateMiseAJour.isBlank()) {
            return requestProcessor.queryToFindAllCodesLists()
                    .with(new CodesListRequestParametizer())
                    .executeQuery()
                    .listResult(AllListCode.class)
                    .toResponseEntity();
        }
        return requestProcessor.queryToFindAllCodesListsByDate()
                .with(new CodesListRequestParametizer(null, dateMiseAJour, null, null))
                .executeQuery()
                .listResult(AllListCode.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ListCodeById> getCodesList(String id, Boolean withCodes, Boolean dateMiseAJour) {
        if (Boolean.TRUE.equals(dateMiseAJour)) {
            return requestProcessor.queryToFindCodesListDateMAJ()
                    .with(new CodesListRequestParametizer(id))
                    .executeQuery()
                    .singleResult(ListCodeById.class)
                    .toResponseEntity();
        }
        return requestProcessor.queryToFindCodesList()
                .with(new CodesListRequestParametizer(id))
                .executeQuery()
                .singleResult(ListCodeById.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<ListCodeById> getCodesListPagination(String id, Integer pageNumber) {
        int offset = (pageNumber - 1) * perPage;
        return requestProcessor.queryToFindCodesListPagination()
                .with(new CodesListRequestParametizer(id, offset, perPage))
                .executeQuery()
                .singleResult(ListCodeById.class)
                .toResponseEntity();
    }
}