package fr.insee.rmes.metadata.api.requestprocessor;

import fr.insee.rmes.metadata.queries.Query;
import fr.insee.rmes.metadata.queries.parameters.*;
import fr.insee.rmes.metadata.queryexecutor.Csv;
import fr.insee.rmes.metadata.queryexecutor.QueryExecutor;
import fr.insee.rmes.metadata.unmarshaller.Unmarshaller;
import fr.insee.rmes.metadata.utils.EndpointsUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

import static fr.insee.rmes.metadata.queries.QueryBuilder.*;


@Component
public record RequestProcessor(fr.insee.rmes.metadata.queries.QueryBuilder queryBuilder, QueryExecutor queryExecutor,
                               Unmarshaller unmarshaller) {

    // Peut-être renommer les query en queryToFind et non en forFind

    public RequestProcessor.QueryBuilder queryToFindConcept(){
        return new RequestProcessor.QueryBuilder(CONCEPT,this);
    }
    public RequestProcessor.QueryBuilder queryToFindConceptsSuivants() {
        return new RequestProcessor.QueryBuilder(CONCEPTSUIVANTS,this);
    }
    public RequestProcessor.QueryBuilder queryToFindConcepts(){
        return new RequestProcessor.QueryBuilder(CONCEPTS,this);
    }
    public RequestProcessor.QueryBuilder queryforFindAscendantsDescendants() {
        return new RequestProcessor.QueryBuilder(ASCENDANTS_OR_DESCENDANTS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindPrecedentsSuivants() {
        return new RequestProcessor.QueryBuilder(PRECEDENTS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindProjetes() {
        return new RequestProcessor.QueryBuilder(PROJETES, this);
    }

    public RequestProcessor.QueryBuilder queryforFindTerritoire() {
        return new RequestProcessor.QueryBuilder(TERRITOIRE, this);
    }

    public RequestProcessor.QueryBuilder queryforFindIris() {
        return new RequestProcessor.QueryBuilder(IRIS, this);
    }

    public RequestProcessor.QueryBuilder queryToFindIrisList() {
        return new RequestProcessor.QueryBuilder(IRIS_LIST, this);

    public RequestProcessor.QueryBuilder queryToFindCantonsOfCommune() {
        return new RequestProcessor.QueryBuilder(COMMUNE_CANTONS, this);
    }

    public RequestProcessor.QueryBuilder queryToFindCommunesOfCanton() {
        return new RequestProcessor.QueryBuilder(CANTON_COMMUNES, this);
    }

    public RequestProcessor.QueryBuilder queryforFindIrisDescendantsCommune() {
        return new RequestProcessor.QueryBuilder(LIEN_COMMUNE_IRIS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindPays() {
        return new RequestProcessor.QueryBuilder(LIEN_PAYS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindDescendantsPays() {
        return new RequestProcessor.QueryBuilder(DESCENDANTS_PAYS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindPaysPrecedents() {
        return new RequestProcessor.QueryBuilder(PAYS_PRECEDENTS, this);
    }

    public RequestProcessor.QueryBuilder queryforFindPaysSuivants() {
        return new RequestProcessor.QueryBuilder(PAYS_SUIVANTS, this);
    }




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
        public ExecutableQuery with(ConceptSuivantRequestParametizer conceptSuivantRequestParametizer) {
            return new ExecutableQuery(
                    requestProcessor.queryBuilder().build(conceptSuivantRequestParametizer.toParameters(), queryPath),
                    requestProcessor
            );
        }
        public ExecutableQuery with(ProjetesRequestParametizer projetesRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(projetesRequestParametizer.toParameters(), queryPath), requestProcessor);
        }


        public ExecutableQuery with(IrisListRequestParametizer irisListRequestParametizer) {
            return new ExecutableQuery(requestProcessor.queryBuilder().build(irisListRequestParametizer.toParameters(), queryPath), requestProcessor);}
    }



    public record ExecutableQuery(Query query, RequestProcessor requestProcessor) {
        public QueryResult executeQuery() {
            return new QueryResult(requestProcessor.queryExecutor().execute(query), requestProcessor);
        }

    }



    public record QueryResult(Csv csv, RequestProcessor requestProcessor) {
        public <E> ListResult<E> listResult(Class<E> clazz) {
            return new ListResult<>(requestProcessor.unmarshaller().unmarshalList(csv, clazz));
        }

        public <E> SingleResult<E> singleResult(Class<E> clazz) {
            return new SingleResult<>(requestProcessor.unmarshaller().unmarshalOrNull(csv, clazz));
        }

        public <E> ListeResultatsIris<E> listeResultatsIris(Class<E> clazz) {
            List<E> list = requestProcessor.unmarshaller().unmarshalList(csv, clazz);
            return new ListeResultatsIris<>(list);
        }


    }

    public record ListResult<E>(List<E> result) {

        public ResponseEntity<List<E>> toResponseEntity() {
            return EndpointsUtils.toResponseEntity(result);
        }
    }

    public record SingleResult<E>(E result) {
        //        public ResponseEntity<E> toResponseEntity() {return new ResponseEntity<>(result, HttpStatus.OK);}
        public ResponseEntity<E> toResponseEntity() {
            return EndpointsUtils.toResponseEntity(result);
        }
    }


    public record ListeResultatsIris<E>(List<E> result) {
        public boolean contains(String value) {
            return result.stream().anyMatch(item -> item.toString().equals(value));
        }

        public ListeResultatsIris(List<E> result) {
            this.result = result;
        }

        public List<E> getList() {
            return result;
        }

    }

}
