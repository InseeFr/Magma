package fr.insee.rmes.magma.diffusion.api.requestprocessor;

import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.queryexecutor.Csv;
import fr.insee.rmes.magma.diffusion.unmarshaller.Unmarshaller;
import fr.insee.rmes.magma.utils.EndpointsUtils;
import fr.insee.rmes.magma.queries.Query;
import fr.insee.rmes.magma.queries.QueryBuilder;
import fr.insee.rmes.magma.queryexecutor.QueryExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static fr.insee.rmes.magma.diffusion.queries.QueryPathListDiffusion.*;

@Component
public record RequestProcessor(QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                               Unmarshaller unmarshaller) {

    // Peut-être renommer les query en queryToFind et non en forFind

    public ExecutableQueryBuilder queryToFindClassification(){
        return new ExecutableQueryBuilder(NOMENCLATURE, this);
    }

    public ExecutableQueryBuilder queryToFindConcept(){
        return new ExecutableQueryBuilder(CONCEPT,this);
    }

    public ExecutableQueryBuilder queryToFindNearbyConcepts() {
        return new ExecutableQueryBuilder(NEARBY_CONCEPTS,this);
    }

    public ExecutableQueryBuilder queryToFindConceptIntitulesAlternatifs() {
        return new ExecutableQueryBuilder(INTITULES_ALTERNATIFS, this);
    }

    public ExecutableQueryBuilder queryToFindConcepts(){
        return new ExecutableQueryBuilder(CONCEPTS,this);
    }

    public ExecutableQueryBuilder queryforFindAscendantsDescendants() {
        return new ExecutableQueryBuilder(ASCENDANTS_OR_DESCENDANTS, this);
    }

    public ExecutableQueryBuilder queryforFindPrecedentsSuivants() {
        return new ExecutableQueryBuilder(PRECEDENTS, this);
    }

    public ExecutableQueryBuilder queryforFindProjetes() {
        return new ExecutableQueryBuilder(PROJETES, this);
    }

    public ExecutableQueryBuilder queryforFindTerritoire() {
        return new ExecutableQueryBuilder(TERRITOIRE, this);
    }

    public ExecutableQueryBuilder queryToFindIrisAndFauxIris() {
        return new ExecutableQueryBuilder(IRIS_FAUX_IRIS, this);
    }

    public ExecutableQueryBuilder queryToFindIrisList() {
        return new ExecutableQueryBuilder(IRIS_LIST, this);
    }

    public ExecutableQueryBuilder queryToFindCantonsOfCommune() {
        return new ExecutableQueryBuilder(COMMUNE_CANTONS, this);
    }

    public ExecutableQueryBuilder queryToFindCommunesOfCanton() {
        return new ExecutableQueryBuilder(CANTON_COMMUNES, this);
    }

    public ExecutableQueryBuilder queryToFindAscendantsFauxIris() { return new ExecutableQueryBuilder(ASCENDANTS_FAUX_IRIS, this);
    }
    public ExecutableQueryBuilder queryToFindIrisDescendantsCommune() {
        return new ExecutableQueryBuilder(LIEN_COMMUNE_IRIS, this);
    }

    public ExecutableQueryBuilder queryforFindPays() {
        return new ExecutableQueryBuilder(LIEN_PAYS, this);
    }

    public ExecutableQueryBuilder queryforFindDescendantsPays() {
        return new ExecutableQueryBuilder(DESCENDANTS_PAYS, this);
    }

    public ExecutableQueryBuilder queryforFindPaysPrecedents() {
        return new ExecutableQueryBuilder(PAYS_PRECEDENTS, this);
    }

    public ExecutableQueryBuilder queryforFindPaysSuivants() {
        return new ExecutableQueryBuilder(PAYS_SUIVANTS, this);
    }

    public ExecutableQueryBuilder queryToFindIntersections() {return new ExecutableQueryBuilder(TERRITOIRES_LIES, this);}

    public record ExecutableQueryBuilder(String queryPath, RequestProcessor requestProcessor) {
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

         public ExecutableQuery with(ConceptsRequestParametizer conceptsRequestParametizer) {
            return new ExecutableQuery(
                    requestProcessor.queryBuilder().build(conceptsRequestParametizer.toParameters(), queryPath),
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
