package fr.insee.rmes.magma.api.requestprocessor;

import fr.insee.rmes.magma.queries.parameters.*;
import fr.insee.rmes.magma.queries.Query;
import fr.insee.rmes.magma.queries.QueryBuilder;
import fr.insee.rmes.magma.queryexecutor.Csv;
import fr.insee.rmes.magma.queryexecutor.QueryExecutor;
import fr.insee.rmes.magma.results.ListResult;
import fr.insee.rmes.magma.results.SingleResult;
import fr.insee.rmes.magma.unmarshaller.JacksonUnmarshaller;
import fr.insee.rmes.magma.unmarshaller.Unmarshaller;
import org.springframework.stereotype.Component;

import static fr.insee.rmes.magma.queries.QueryPathListDiffusion.*;
import static fr.insee.rmes.magma.queries.QueryPathListGestion.*;

@Component
public record RequestProcessor(QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                               JacksonUnmarshaller unmarshaller) {


    public ExecutableQueryBuilder queryToFindSerieById() {
        return new ExecutableQueryBuilder(SERIE_BY_ID, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindOperationByCode() {
        return new ExecutableQueryBuilder(OPERATION_BY_CODE, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindAllSeries() {
        return new ExecutableQueryBuilder(ALL_SERIES, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindIndicatorById() {
        return new ExecutableQueryBuilder(INDICATOR_BY_ID, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindAllDatasets() {
        return new ExecutableQueryBuilder(ALL_DATASETS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindDatasetById() {
        return new ExecutableQueryBuilder(DATASET_BY_ID, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindDistributionsByDatasetId() {
        return new ExecutableQueryBuilder(DISTRIBUTIONS_BY_DATASET_ID, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindClassification() {
        return new ExecutableQueryBuilder(NOMENCLATURE, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindConcept() {
        return new ExecutableQueryBuilder(CONCEPT, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindNearbyConcepts() {
        return new ExecutableQueryBuilder(NEARBY_CONCEPTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindConceptIntitulesAlternatifs() {
        return new ExecutableQueryBuilder(INTITULES_ALTERNATIFS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindConcepts() {
        return new ExecutableQueryBuilder(CONCEPTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindAscendantsDescendants() {
        return new ExecutableQueryBuilder(ASCENDANTS_OR_DESCENDANTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindPrecedentsSuivants() {
        return new ExecutableQueryBuilder(PRECEDENTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindProjetes() {
        return new ExecutableQueryBuilder(PROJETES, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindTerritoire() {
        return new ExecutableQueryBuilder(TERRITOIRE, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindIrisAndFauxIris() {
        return new ExecutableQueryBuilder(IRIS_FAUX_IRIS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindIrisList() {
        return new ExecutableQueryBuilder(IRIS_LIST, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindCantonsOfCommune() {
        return new ExecutableQueryBuilder(COMMUNE_CANTONS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindCommunesOfCanton() {
        return new ExecutableQueryBuilder(CANTON_COMMUNES, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindAscendantsFauxIris() {
        return new ExecutableQueryBuilder(ASCENDANTS_FAUX_IRIS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindIrisDescendantsCommune() {
        return new ExecutableQueryBuilder(LIEN_COMMUNE_IRIS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindPays() {
        return new ExecutableQueryBuilder(LIEN_PAYS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindDescendantsPays() {
        return new ExecutableQueryBuilder(DESCENDANTS_PAYS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindPaysPrecedents() {
        return new ExecutableQueryBuilder(PAYS_PRECEDENTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryforFindPaysSuivants() {
        return new ExecutableQueryBuilder(PAYS_SUIVANTS, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindIntersections() {
        return new ExecutableQueryBuilder(TERRITOIRES_LIES, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindRapportQualite() {
        return new ExecutableQueryBuilder(RAPPORT_QUALITE, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindRubriques() {
        return new ExecutableQueryBuilder(RUBRIQUES, this, unmarshaller);
    }

    public ExecutableQueryBuilder queryToFindDocuments() {
        return new ExecutableQueryBuilder(DOCUMENTS, this, unmarshaller);
    }

    // =========================================================
    // Inner records
    // =========================================================

    public record ExecutableQueryBuilder(String queryPath, RequestProcessor requestProcessor, Unmarshaller unmarshaller) {

        public ExecutableQuery with(SeriesOperationsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(DatasetsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(IndicateurRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(AscendantsDescendantsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(TerritoireRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(TerritoireEtoileRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(PrecedentsSuivantsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(ConceptsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(ProjetesRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(TerritoiresLiesRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(ClassificationRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(OperationRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(OperationRubriquesRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }

        public ExecutableQuery with(OperationsDocumentsRequestParametizer p) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(p.toParameters(), queryPath), requestProcessor, unmarshaller);
        }
    }

    public record ExecutableQuery(Query query, RequestProcessor requestProcessor, Unmarshaller unmarshaller) {

        public QueryResult executeQuery() {
            return new QueryResult(requestProcessor.queryExecutor().execute(query), unmarshaller);
        }

        public Boolean executeAskQuery() {
            return requestProcessor.queryExecutor().executeAskQuery(query);
        }
    }

    public record QueryResult(Csv csv, Unmarshaller unmarshaller) {

        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(unmarshaller.unmarshalList(csv, clazz));
        }

        public <E> SingleResult<E> singleResult(Class<E> clazz) {
            return new SingleResult<>(unmarshaller.unmarshalOrNull(csv, clazz));
        }
    }
}