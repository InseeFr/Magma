package fr.insee.rmes.magma.gestion.api.requestprocessor;

import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import fr.insee.rmes.magma.gestion.unmarshaller.Unmarshaller;
import fr.insee.rmes.magma.queries.Query;
import fr.insee.rmes.magma.queries.QueryBuilder;
import fr.insee.rmes.magma.queryexecutor.Csv;
import fr.insee.rmes.magma.queryexecutor.QueryExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import fr.insee.rmes.magma.utils.EndpointsUtils;

import java.util.List;

import static fr.insee.rmes.magma.gestion.queries.QueryPathListGestion.STRUCTURES_COMPONENTS;

@Component
public record RequestProcessor(QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                               Unmarshaller unmarshaller) {

    // Peut-être renommer les query en queryToFind et non en forFind

    public ExecutableQueryBuilder queryForFindStructuresComponents() {
        return new ExecutableQueryBuilder(STRUCTURES_COMPONENTS, this);

    }

    public record ExecutableQueryBuilder(String queryPath, RequestProcessor requestProcessor) {
        public ExecutableQuery with(StructureComponentsRequestParametizer structureComponentsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(structureComponentsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }
    }

    public record ExecutableQuery(Query query, RequestProcessor requestProcessor) {
        public QueryResult executeQuery() {
            return new QueryResult(requestProcessor.queryExecutor().execute(query), requestProcessor);
        }

        public Boolean executeAskQuery() {
            return requestProcessor.queryExecutor().executeAskQuery(query);
        }
    }

    public record QueryResult(Csv csv, RequestProcessor requestProcessor) {
        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(requestProcessor.unmarshaller().unmarshalList(csv, clazz));
        }

        public <E> SingleResult<E> singleResult(Class<E> clazz) {
            return new SingleResult<>(requestProcessor.unmarshaller().unmarshalOrNull(csv, clazz));
        }
    }

    public record ListResult<E>(List<E> result) {
        public ResponseEntity<List<E>> toResponseEntity() {
            return EndpointsUtils.toResponseEntity(result);
        }
    }

    public record SingleResult<E>(E result) {
        public ResponseEntity<E> toResponseEntity() {
            return EndpointsUtils.toResponseEntity(result);
        }
    }

}
