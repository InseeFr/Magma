package fr.insee.rmes.magma.diffusion.api.requestprocessor;

import fr.insee.rmes.magma.diffusion.queries.parameters.*;
import fr.insee.rmes.magma.diffusion.unmarshaller.JacksonUnmarshaller;
import fr.insee.rmes.magma.queries.Query;
import fr.insee.rmes.magma.queries.QueryBuilder;
import fr.insee.rmes.magma.queryexecutor.Csv;
import fr.insee.rmes.magma.queryexecutor.QueryExecutor;
import fr.insee.rmes.magma.results.ListResult;
import fr.insee.rmes.magma.results.SingleResult;
import org.springframework.stereotype.Component;

import static fr.insee.rmes.magma.diffusion.queries.QueryPathListDiffusion.*;

@Component
public record RequestProcessorDiffusion(QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                                        JacksonUnmarshaller unmarshaller) {

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

    public ExecutableQueryBuilder queryToFindRapportQualite (){return new ExecutableQueryBuilder(RAPPORT_QUALITE, this);}

    public ExecutableQueryBuilder queryToFindRubriques(){return new ExecutableQueryBuilder(RUBRIQUES,this);}

    public ExecutableQueryBuilder queryToFindDocuments(){return new ExecutableQueryBuilder(DOCUMENTS,this);}

    public record ExecutableQueryBuilder(String queryPath, RequestProcessorDiffusion requestProcessorDiffusion) {
        public ExecutableQueryDiffusion with(AscendantsDescendantsRequestParametizer ascendantsDescendantsRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(ascendantsDescendantsRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(TerritoireRequestParametizer territoireRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(territoireRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(TerritoireEtoileRequestParametizer territoireEtoileRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(territoireEtoileRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(PrecedentsSuivantsRequestParametizer precedentsRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(precedentsRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(ConceptsRequestParametizer conceptsRequestParametizer) {
            return new ExecutableQueryDiffusion(
                    requestProcessorDiffusion.queryBuilder().build(conceptsRequestParametizer.toParameters(), queryPath),
                    requestProcessorDiffusion
            );
        }

        public ExecutableQueryDiffusion with(ProjetesRequestParametizer projetesRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(projetesRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(TerritoiresLiesRequestParametizer territoiresLiesRequestParametizer) {
            return new ExecutableQueryDiffusion(requestProcessorDiffusion.queryBuilder().build(territoiresLiesRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(ClassificationRequestParametizer classificationRequestParametizer) {
            return new ExecutableQueryDiffusion(
                    requestProcessorDiffusion.queryBuilder().build(classificationRequestParametizer.toParameters(), queryPath),
                    requestProcessorDiffusion
            );
        }

        public ExecutableQueryDiffusion with(OperationRequestParametizer operationRequestParametizer) {
            return new ExecutableQueryDiffusion(
                    requestProcessorDiffusion.queryBuilder().build(operationRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(OperationRubriquesRequestParametizer operationRubriquesRequestParametizer) {
            return new ExecutableQueryDiffusion(
                    requestProcessorDiffusion.queryBuilder().build(operationRubriquesRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }

        public ExecutableQueryDiffusion with(OperationsDocumentsRequestParametizer operationsDocumentsRequestParametizer) {
            return new ExecutableQueryDiffusion(
                    requestProcessorDiffusion.queryBuilder().build(operationsDocumentsRequestParametizer.toParameters(), queryPath), requestProcessorDiffusion);
        }
    }

    public record ExecutableQueryDiffusion(Query query, RequestProcessorDiffusion requestProcessorDiffusion) {
        public QueryResultDiffusion executeQuery() {
            return new QueryResultDiffusion(requestProcessorDiffusion.queryExecutor().execute(query), requestProcessorDiffusion);
        }

        public Boolean executeAskQuery() {
            return requestProcessorDiffusion.queryExecutor().executeAskQuery(query);
        }
    }

    public record QueryResultDiffusion(Csv csv, RequestProcessorDiffusion requestProcessorDiffusion) {
        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(requestProcessorDiffusion.unmarshaller().unmarshalList(csv, clazz));
        }

        public <E> SingleResult<E> singleResult(Class<E> clazz) {
            return new SingleResult<>(requestProcessorDiffusion.unmarshaller().unmarshalOrNull(csv, clazz));
        }
    }

}
