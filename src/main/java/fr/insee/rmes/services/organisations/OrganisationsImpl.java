package fr.insee.rmes.services.organisations;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.model.operation.SerieById;
import fr.insee.rmes.model.organisation.OrganisationModel;
import fr.insee.rmes.modelSwagger.operation.*;
import fr.insee.rmes.modelSwagger.organisations.Label;
import fr.insee.rmes.modelSwagger.organisations.OrganisationsModelSwagger;
import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrganisationsImpl extends RdfService implements OrganisationsServices {

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        //params.put("CODELIST_GRAPH", Config.BASE_GRAPH + Config.CODELIST_GRAPH);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);


        return params;
    }

    @Override
    public String getAllOrganisations() throws RmesException, IOException {
        Map<String, Object> params = initParams();
        JSONArray organisationsList;
        organisationsList = repoGestion.getResponseAsArray(buildRequest(Constants.ORGANISATIONS_QUERIES_PATH, "getAllOrganisations.ftlh", params));


        ObjectMapper jsonResponse = new ObjectMapper();
        OrganisationModel[] listOperations = jsonResponse.readValue(organisationsList.toString(), OrganisationModel[].class);

        ObjectMapper mapper = new ObjectMapper();
        List<OrganisationsModelSwagger> organisationsListModelSwaggerS = new ArrayList<>();

        for (OrganisationModel byOrganisations : listOperations) {
            if (byOrganisations.getId() != null) {
                Label LabelOrganisation1 = new Label(Config.LG1, byOrganisations.getLabelLg1());
                Label LabelOrganisation2 = new Label(Config.LG2, byOrganisations.getLabelLg2());
                List<Label> label = new ArrayList<>();
                if (byOrganisations.getLabelLg1() != null) {
                    label.add(LabelOrganisation1);}
                if (byOrganisations.getLabelLg2() != null) {
                    label.add(LabelOrganisation2);
                }
                OrganisationsModelSwagger organisationsListModelSwagger = new OrganisationsModelSwagger(byOrganisations.getId(), label);
                organisationsListModelSwaggerS.add(organisationsListModelSwagger);
            }
        }
        return mapper.writeValueAsString(organisationsListModelSwaggerS);
    }

    @Override
    public String getOrganisationById(String id) throws RmesException, IOException {
        Map<String, Object> params = initParams();
        params.put("ID", id);

        JSONObject operationId= repoGestion.getResponseAsObject(buildRequest(Constants.ORGANISATIONS_QUERIES_PATH, "getOrganisationById.ftlh", params));

        ObjectMapper jsonResponse =new ObjectMapper();
        OrganisationModel operationById = jsonResponse.readValue(operationId.toString(),OrganisationModel.class);

        ObjectMapper mapper = new ObjectMapper();
        Label label1 = new Label(Config.LG1,operationById.getLabelLg1());
        Label label2 = new Label(Config.LG2, operationById.getLabelLg2());
        List<Label> label = new ArrayList<>();
        if (operationById.getLabelLg1() != null) {
            label.add(label1);}
        if (operationById.getLabelLg2() != null) {
            label.add(label2);
        }

        OrganisationsModelSwagger organisationsListModelSwagger= new OrganisationsModelSwagger(operationById.getId(), label);

        return mapper.writeValueAsString(organisationsListModelSwagger);

    }


}
