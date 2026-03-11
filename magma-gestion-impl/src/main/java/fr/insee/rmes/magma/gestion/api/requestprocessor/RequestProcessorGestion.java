package fr.insee.rmes.magma.gestion.api.requestprocessor;

import fr.insee.rmes.magma.results.SingleResult;
import fr.insee.rmes.magma.gestion.queries.parameters.StructureComponentsRequestParametizer;
import fr.insee.rmes.magma.gestion.unmarshaller.JacksonUnmarshallerGestion;
import fr.insee.rmes.magma.results.ListResult;
import fr.insee.rmes.magma.queries.Query;
import fr.insee.rmes.magma.queries.QueryBuilder;
import fr.insee.rmes.magma.queryexecutor.Csv;
import fr.insee.rmes.magma.queryexecutor.QueryExecutor;
import org.springframework.stereotype.Component;

import static fr.insee.rmes.magma.gestion.queries.QueryPathListGestion.*;

@Component
public record RequestProcessorGestion(QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                                      JacksonUnmarshallerGestion unmarshaller) {

    // Peut-être renommer les query en queryToFind et non en forFind

    public ExecutableQueryBuilder queryToFindStructuresComponents() {
        return new ExecutableQueryBuilder(STRUCTURES_COMPONENTS, this);

    }

    public ExecutableQueryBuilder queryToFindComponent() {
        return new ExecutableQueryBuilder(COMPONENT, this);

    }

    public ExecutableQueryBuilder queryToFindStructuresSlicesKeys() {
        return new ExecutableQueryBuilder(STRUCTURES_SLICESKEYS, this);

    }

    public ExecutableQueryBuilder queryToFindStructure() {
        return new ExecutableQueryBuilder(STRUCTURE, this);

    }

    public record ExecutableQueryBuilder(String queryPath, RequestProcessorGestion requestProcessor) {
        public ExecutableQueryGestion with(StructureComponentsRequestParametizer structureComponentsRequestParametizer) {
            return new ExecutableQueryGestion(requestProcessor.queryBuilder().build(structureComponentsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }
    }

    public record ExecutableQueryGestion(Query query, RequestProcessorGestion requestProcessor) {
        public QueryResultGestion executeQuery() {
            return new QueryResultGestion(requestProcessor.queryExecutor().execute(query), requestProcessor);
        }
    }

    public record QueryResultGestion(Csv csv, RequestProcessorGestion requestProcessor) {
        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(requestProcessor.unmarshaller().unmarshalList(csv, clazz));
        }

        public <E> SingleResult<E> singleResult(Class<E> clazz) {
            return new SingleResult<>(requestProcessor.unmarshaller().unmarshalOrNull(csv, clazz));
        }
    }

}
