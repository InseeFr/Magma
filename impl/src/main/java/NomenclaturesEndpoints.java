import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.queries.parameters.ClassificationRequestParametizer;
import org.springframework.http.ResponseEntity;

public class NomenclaturesEndpoints implements NomenclatureApi {

    private final RequestProcessor requestProcessor;

    public NomenclaturesEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<Nomenclature> getClassificationByCode(String classification, String level, String code){
        return requestProcessor.queryToFindClassification()
                .with(new ClassificationRequestParametizer(classification, level, code))
                .executeQuery()
                .listResult()
                .toResponseEntity();
    }
}
