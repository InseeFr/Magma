package fr.insee.rmes.metadata.api;

import fr.insee.rmes.metadata.api.requestprocessor.RequestProcessor;
import fr.insee.rmes.metadata.model.*;
import fr.insee.rmes.metadata.queries.parameters.AscendantsDescendantsRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.IrisListRequestParametizer;
import fr.insee.rmes.metadata.queries.parameters.TerritoireRequestParametizer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.testcontainers.shaded.org.bouncycastle.crypto.engines.EthereumIESEngine;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GeoIrisEndpoints implements GeoIrisApi {

    private final RequestProcessor requestProcessor;

    public GeoIrisEndpoints(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirisasc (String code, LocalDate date, TypeEnumAscendantsIris type) {
        if (code.matches("^.{5}0000$")) {
            return requestProcessor.queryToFindAscendantsFauxIris()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, type, Iris.class))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
        else {
            return requestProcessor.queryforFindAscendantsDescendants()
                    .with(new AscendantsDescendantsRequestParametizer(code, date, type, Iris.class))
                    .executeQuery()
                    .listResult(TerritoireTousAttributs.class)
                    .toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<Iris> getcogiris(String code, LocalDate date) {
        //cf carte https://github.com/orgs/InseeFr/projects/9/views/5?filterQuery=-application%3AColectica+-scope%3Atechnique+iris&pane=issue&itemId=49451501&issue=InseeFr%7CMetadata-API%7C103
        String code_commune=code.substring(0, 5);
//        RequestProcessor.ListResult<String> types = requestProcessor.queryforFindIrisDescendantsCommune()
//               .with(new TerritoireRequestParametizer(code_commune, Commune.class))
//               .executeQuery().listResult(String.class);

        // Exécuter la requête et obtenir le résultat
        RequestProcessor.ListeResultatsIris<String> types = requestProcessor.queryforFindIrisDescendantsCommune()
                .with(new TerritoireRequestParametizer(code_commune, Commune.class))
                .executeQuery()
                .listeResultatsIris(String.class);
//plante à la méthode unmarshallAll'

        // Obtenir la liste des résultats
        List<String> typeList = types.getList();

//condition à mettre : si l'un des types de la liste finit par "#Iris\r\n", cf https://github.com/InseeFr/Metadata-API/blob/main/src/main/java/fr/insee/rmes/utils/IrisUtils.java#L8
        boolean containsIris = typeList.contains("http://rdf.insee.fr/def/geo#Iris");


//bloc de fin pour que ça compile mais à revoir
        return requestProcessor.queryforFindIris()
                .with(new TerritoireRequestParametizer(code, date, Iris.class, "none"))
                .executeQuery()
                .singleResult(Iris.class)
                .toResponseEntity();
    }

    @Override
    public ResponseEntity<List<TerritoireTousAttributs>> getcogirislist (LocalDate date, Boolean com) {
        boolean finalcom = (com != null) && com;
        return requestProcessor.queryToFindIrisList()
                .with(new IrisListRequestParametizer(date, finalcom))
                .executeQuery()
                .listResult(TerritoireTousAttributs.class)
                .toResponseEntity();

    }
}
