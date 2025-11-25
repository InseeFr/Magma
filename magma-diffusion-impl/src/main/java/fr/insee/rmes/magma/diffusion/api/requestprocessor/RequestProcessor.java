package fr.insee.rmes.magma.diffusion.api.requestprocessor;

import fr.insee.rmes.magma.diffusion.queries.Query;
import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.queryexecutor.Csv;
import fr.insee.rmes.magma.diffusion.queryexecutor.QueryExecutor;
import fr.insee.rmes.magma.diffusion.unmarshaller.Unmarshaller;
import fr.insee.rmes.magma.diffusion.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static fr.insee.rmes.magma.diffusion.queries.QueryBuilder.*;

@Component
public record RequestProcessor(fr.insee.rmes.magma.diffusion.queries.QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                               Unmarshaller unmarshaller) {

    // Peut-être renommer les query en queryToFind et non en forFind

    public QueryBuilder queryToFindClassification(){
        return new QueryBuilder(NOMENCLATURE, this);
    }

    public QueryBuilder queryToFindConcept(){
        return new QueryBuilder(CONCEPT,this);
    }

    public QueryBuilder queryToFindNearbyConcepts() {
        return new QueryBuilder(NEARBY_CONCEPTS,this);
    }

    public QueryBuilder queryToFindConcepts(){
        return new QueryBuilder(CONCEPTS,this);
    }

    public QueryBuilder queryforFindAscendantsDescendants() {
        return new QueryBuilder(ASCENDANTS_OR_DESCENDANTS, this);
    }

    public QueryBuilder queryforFindPrecedentsSuivants() {
        return new QueryBuilder(PRECEDENTS, this);
    }

    public QueryBuilder queryforFindProjetes() {
        return new QueryBuilder(PROJETES, this);
    }

    public QueryBuilder queryforFindTerritoire() {
        return new QueryBuilder(TERRITOIRE, this);
    }

    public QueryBuilder queryToFindIrisAndFauxIris() {
        return new QueryBuilder(IRIS_FAUX_IRIS, this);
    }

    public QueryBuilder queryToFindIrisList() {
        return new QueryBuilder(IRIS_LIST, this);
    }

    public QueryBuilder queryToFindCantonsOfCommune() {
        return new QueryBuilder(COMMUNE_CANTONS, this);
    }

    public QueryBuilder queryToFindCommunesOfCanton() {
        return new QueryBuilder(CANTON_COMMUNES, this);
    }

    public QueryBuilder queryToFindAscendantsFauxIris() { return new RequestProcessor.QueryBuilder(ASCENDANTS_FAUX_IRIS, this);
    }
    public QueryBuilder queryToFindIrisDescendantsCommune() {
        return new QueryBuilder(LIEN_COMMUNE_IRIS, this);
    }

    public QueryBuilder queryforFindPays() {
        return new QueryBuilder(LIEN_PAYS, this);
    }

    public QueryBuilder queryforFindDescendantsPays() {
        return new QueryBuilder(DESCENDANTS_PAYS, this);
    }

    public QueryBuilder queryforFindPaysPrecedents() {
        return new QueryBuilder(PAYS_PRECEDENTS, this);
    }

    public QueryBuilder queryforFindPaysSuivants() {
        return new QueryBuilder(PAYS_SUIVANTS, this);
    }

    public QueryBuilder queryToFindIntersections() {return new QueryBuilder(TERRITOIRES_LIES, this);}

    public record QueryBuilder(String queryPath, RequestProcessor requestProcessor) {
        public ExecutableQuery with(AscendantsDescendantsRequestParametizer ascendantsDescendantsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(ascendantsDescendantsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(TerritoireRequestParametizer territoireRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(territoireRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(TerritoireEtoileRequestParametizer territoireEtoileRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(territoireEtoileRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(PrecedentsSuivantsRequestParametizer precedentsRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(precedentsRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(ConceptRequestParametizer conceptRequestParametizer) {
            return new ExecutableQuery(
                    requestProcessor.queryBuilder().build(conceptRequestParametizer.toParameters(), queryPath),
                    requestProcessor
            );
        }

        public ExecutableQuery with(ConceptsNearbyRequestParametizer conceptSuivantRequestParametizer) {
            return new ExecutableQuery(
                    requestProcessor.queryBuilder().build(conceptSuivantRequestParametizer.toParameters(), queryPath),
                    requestProcessor
            );
        }

        public ExecutableQuery with(ProjetesRequestParametizer projetesRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(projetesRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(TerritoiresLiesRequestParametizer territoiresLiesRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(territoiresLiesRequestParametizer.toParameters(), queryPath), requestProcessor);
        }

        public ExecutableQuery with(ClassificationRequestParametizer classificationRequestParametizer) {
            return new ExecutableQuery(
                    requestProcessor.queryBuilder().build(classificationRequestParametizer.toParameters(), queryPath),
                    requestProcessor
            );
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
