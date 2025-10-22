package fr.insee.rmes.magma.gestion.old.services.organisations;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.insee.rmes.magma.gestion.old.model.organisation.OrganisationModel;
import fr.insee.rmes.magma.gestion.old.modelSwagger.organisations.Label;
import fr.insee.rmes.magma.gestion.old.modelSwagger.organisations.OrganisationsModelSwagger;
import fr.insee.rmes.magma.gestion.old.persistence.FreeMarkerUtils;
import fr.insee.rmes.magma.gestion.old.persistence.RdfService;
import fr.insee.rmes.magma.gestion.old.utils.Constants;
import fr.insee.rmes.magma.gestion.old.utils.config.Config;
import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Service
public class OrganisationsImpl extends RdfService implements OrganisationsServices {
    public OrganisationsImpl(FreeMarkerUtils freeMarkerUtils) {
        super(freeMarkerUtils);
    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
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
                Label prefLabel1 = new Label(Config.LG1, byOrganisations.getPrefLabelLg1());
                Label prefLabel2 = new Label(Config.LG2, byOrganisations.getPrefLabelLg2());
                List<Label> label = new ArrayList<>();
                if (byOrganisations.getPrefLabelLg1() != null) {
                    label.add(prefLabel1);}
                if (byOrganisations.getPrefLabelLg2() != null) {
                    label.add(prefLabel2);
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

        JSONObject operationId = repoGestion.getResponseAsObject(buildRequest(Constants.ORGANISATIONS_QUERIES_PATH, "getOrganisationById.ftlh", params));
        if (operationId.has("Id")) {
            ObjectMapper jsonResponse = new ObjectMapper();
            OrganisationModel operationById = jsonResponse.readValue(operationId.toString(), OrganisationModel.class);

            ObjectMapper mapper = new ObjectMapper();
            Label preflabel1 = new Label(Config.LG1, operationById.getPrefLabelLg1());
            Label preflabel2 = new Label(Config.LG2, operationById.getPrefLabelLg2());
            Label altlabel1 = new Label(Config.LG1, operationById.getAltLabelLg1());
            Label altlabel2 = new Label(Config.LG2, operationById.getAltLabelLg2());
            List<Label> label = new ArrayList<>();
            List<Label> altlabel = new ArrayList<>();
            if (operationById.getPrefLabelLg1() != null) {
                label.add(preflabel1);
            }
            if (operationById.getPrefLabelLg2() != null) {
                label.add(preflabel2);
            }
            if (operationById.getAltLabelLg1() != null) {
                altlabel.add(altlabel1);
            }
            if (operationById.getAltLabelLg2() != null) {
                altlabel.add(altlabel2);
            }
            OrganisationsModelSwagger organisationsListModelSwagger = new OrganisationsModelSwagger(operationById.getId(), operationById.getUri(), label);

            if (operationById.getAbreviation() != null) {
                organisationsListModelSwagger.setAbreviation(operationById.getAbreviation());
            }
            if (altlabel != null) {
                organisationsListModelSwagger.setAltlabelOrganisation(altlabel);
            }
            if (operationById.getUniteDe() != null) {
                organisationsListModelSwagger.setUniteDe(operationById.getUniteDe());
            }
            if (operationById.getSousTelleDe() != null) {
                organisationsListModelSwagger.setSousTelleDe(operationById.getSousTelleDe());
            }


            return mapper.writeValueAsString(organisationsListModelSwagger);
        }
        else{
            throw new RmesException(HttpStatus.NOT_FOUND, "Non existent organization identifier", "The id " + id + " does not correspond to any organization");
        }
    }


}
