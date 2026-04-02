package fr.insee.rmes.magma.gestion.api.requestprocessor;

import fr.insee.rmes.magma.results.SingleResult;
import fr.insee.rmes.magma.gestion.queries.parameters.DatasetsRequestParametizer;
import fr.insee.rmes.magma.gestion.queries.parameters.SeriesOperationsRequestParametizer;
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


    public ExecutableQueryBuilder queryToFindSerieById() {
        return new ExecutableQueryBuilder(SERIE_BY_ID, this);
    }

    public ExecutableQueryBuilder queryToFindOperationByCode() {
        return new ExecutableQueryBuilder(OPERATION_BY_CODE, this);
    }

    public ExecutableQueryBuilder queryToFindAllDatasets() {
        return new ExecutableQueryBuilder(ALL_DATASETS, this);
    }

    public ExecutableQueryBuilder queryToFindAllDatasetsByDate() {
        return new ExecutableQueryBuilder(ALL_DATASETS_BY_DATE, this);
    }

    public record ExecutableQueryBuilder(String queryPath, RequestProcessorGestion requestProcessor) {

        public ExecutableQueryGestion with(SeriesOperationsRequestParametizer seriesOperationsRequestParametizer) {
            return new ExecutableQueryGestion(requestProcessor.queryBuilder().build(seriesOperationsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQueryGestion with(DatasetsRequestParametizer datasetsRequestParametizer) {
            return new ExecutableQueryGestion(requestProcessor.queryBuilder().build(datasetsRequestParametizer.toParameters(), queryPath), requestProcessor);
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
