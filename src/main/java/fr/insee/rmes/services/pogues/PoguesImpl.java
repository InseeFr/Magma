package fr.insee.rmes.services.pogues;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import fr.insee.rmes.dto.pogues.NodePogues;
import io.swagger.v3.core.util.Json;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

import javax.ws.rs.core.Response;

@Service
public class PoguesImpl extends RdfService implements PoguesServices {

    @Override
    public String getAllCodesLists(Boolean survey) throws RmesException {
        Map<String, Object> params = initParams();
        JSONArray poguesCodesLists ;

        if ( survey) {
             poguesCodesLists = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllCodesListsSurvey.ftlh", params));
        }
        else {
             poguesCodesLists = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getAllCodesLists.ftlh", params));
        }

        for (int i = 0; i < poguesCodesLists.length(); i++) {
            JSONObject PoguesCodesList = poguesCodesLists.getJSONObject(i);

            if (PoguesCodesList.has("seriesAltLabelLg1") && PoguesCodesList.has("seriesAltLabelLg2") ) {
                PoguesCodesList.put("altLabel", this.formatAltLabelPogues(PoguesCodesList));
            }
            PoguesCodesList.remove("seriesAltLabelLg1");
            PoguesCodesList.remove("seriesAltLabelLg2");

            if (PoguesCodesList.has("typeId")) {
                PoguesCodesList.put("label", this.formatTypeLabelPogues(PoguesCodesList));
                PoguesCodesList.put("type", this.formatTypePogues(PoguesCodesList));

            }
            PoguesCodesList.remove("typeLabelLg1");
            PoguesCodesList.remove("typeLabelLg2");
            PoguesCodesList.remove("typeId");


            if (PoguesCodesList.has("familyId")) {
                PoguesCodesList.put("label", this.formatFamilyLabelPogues(PoguesCodesList));
                PoguesCodesList.put("famille", this.formatFamilyPogues(PoguesCodesList));
            }

            PoguesCodesList.remove("familyLabelLg1");
            PoguesCodesList.remove("familyLabelLg2");
            PoguesCodesList.remove("familyId");
            PoguesCodesList.remove("family");

            if (PoguesCodesList.has("periodicityId")) {
                PoguesCodesList.put("label", this.formatFrequencyLabelPogues(PoguesCodesList));
                PoguesCodesList.put("frequence", this.formatFrequencyPogues(PoguesCodesList));
            }

            PoguesCodesList.remove("periodicityLabelLg1");
            PoguesCodesList.remove("periodicityLabelLg2");
            PoguesCodesList.remove("periodicityId");
            PoguesCodesList.remove("periodicity");

            PoguesCodesList.put("label", this.formatLabelPogues(PoguesCodesList));

            PoguesCodesList.remove("seriesLabelLg1");
            PoguesCodesList.remove("seriesLabelLg2");

        }
        return poguesCodesLists.toString();


    }

    @Override
    public String getCodesList(String id) throws RmesException {
        Map<String, Object> params = initParams();

        params.put("ID", id);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);

        JSONObject codesList = repoGestion.getResponseAsObject(buildRequest(Constants.POGUES_QUERIES_PATH, "getCodesList.ftlh", params));

        if (codesList.has("seriesAltLabelLg1") && codesList.has("seriesAltLabelLg2") ) {
            codesList.put("altLabel", this.formatAltLabelPogues(codesList));
        }
        codesList.remove("seriesAltLabelLg1");
        codesList.remove("seriesAltLabelLg2");

        if (codesList.has("typeId")) {
            codesList.put("label", this.formatTypeLabelPogues(codesList));
            codesList.put("type", this.formatTypePogues(codesList));

        }
        codesList.remove("typeLabelLg1");
        codesList.remove("typeLabelLg2");
        codesList.remove("typeId");


        if (codesList.has("familyId")) {
            codesList.put("label", this.formatFamilyLabelPogues(codesList));
            codesList.put("famille", this.formatFamilyPogues(codesList));
        }

        codesList.remove("familyLabelLg1");
        codesList.remove("familyLabelLg2");
        codesList.remove("familyId");
        codesList.remove("family");

        if (codesList.has("periodicityId")) {
            codesList.put("label", this.formatFrequencyLabelPogues(codesList));
            codesList.put("frequence", this.formatFrequencyPogues(codesList));
        }

        codesList.remove("periodicityLabelLg1");
        codesList.remove("periodicityLabelLg2");
        codesList.remove("periodicityId");
        codesList.remove("periodicity");

        codesList.put("label", this.formatLabelPogues(codesList));

        codesList.remove("seriesLabelLg1");
        codesList.remove("seriesLabelLg2");


        return codesList.toString();
    }

    @Override
    public String getOperationsBySerie(String id) throws RmesException {
        Map<String, Object> params = initParams();
        params.put("ID", id);

        JSONArray operationsList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationsBySerie.ftlh", params));

        for (int i = 0; i < operationsList.length(); i++) {
            JSONObject OperationsList = operationsList.getJSONObject(i);

            if (OperationsList.has("seriesId") ) {
                OperationsList.put("label", this.formatOperationSerieLabelPogues(OperationsList));
                OperationsList.put("serie", this.formatOperationSeriePogues(OperationsList));
            }

            OperationsList.remove("seriesLabelLg1");
            OperationsList.remove("seriesLabelLg2");
            OperationsList.remove("label");
            OperationsList.remove("series");
            OperationsList.remove("seriesId");

            /* mise en forme id, sims*/
            if (OperationsList.has("operationId")) {
            OperationsList.put("id",OperationsList.get("operationId"));}

            OperationsList.remove("operationId");

            if (OperationsList.has("simsId")) {
                OperationsList.put("idRapportQualite", OperationsList.get("simsId"));
                            }
            OperationsList.remove("simsId");

            if (OperationsList.has("operationLabelLg1") && OperationsList.has("operationLabelLg2") ) {
                OperationsList.put("label", this.formatLabelOperationPogues(OperationsList));
            }
            OperationsList.remove("operationLabelLg1");
            OperationsList.remove("operationLabelLg2");

            if (OperationsList.has("operationAltLabelLg1") && OperationsList.has("operationAltLabelLg2") ) {
                OperationsList.put("altLabel", this.formatOperationAltLabelPogues(OperationsList));
            }

            OperationsList.remove("operationAltLabelLg1");
            OperationsList.remove("operationAltLabelLg2");
            OperationsList.remove("typeLabelLg1");
            OperationsList.remove("typeLabelLg2");



        }
        return operationsList.toString();

    }

    @Override
    public String getOperationByCode(String id) throws RmesException {
        Map<String, Object> params = initParams();
        params.put("ID", id);
        JSONArray operationsList = repoGestion.getResponseAsArray(buildRequest(Constants.POGUES_QUERIES_PATH, "getOperationByCode.ftlh", params));
        for (int i = 0; i < operationsList.length(); i++) {
            JSONObject OperationsList = operationsList.getJSONObject(i);

            if (OperationsList.has("seriesId") ) {
                OperationsList.put("label", this.formatOperationSerieLabelPogues(OperationsList));
                if (OperationsList.has("seriesAltLabelLg1") && OperationsList.has("seriesAltLabelLg2") ) {
                    OperationsList.put("altLabel", this.formatAltLabelPogues(OperationsList));
                         }
                OperationsList.put("serie", this.formatOperationByCodeSeriePogues(OperationsList));
            }

            OperationsList.remove("seriesLabelLg1");
            OperationsList.remove("seriesLabelLg2");
            OperationsList.remove("seriesAltLabelLg1");
            OperationsList.remove("seriesAltLabelLg2");
            OperationsList.remove("altLabel");
            OperationsList.remove("label");
            OperationsList.remove("series");
            OperationsList.remove("seriesId");

            /* mise en forme id, sims*/
            if (OperationsList.has("operationId")) {
                OperationsList.put("id",OperationsList.get("operationId"));}

            OperationsList.remove("operationId");

            if (OperationsList.has("simsId")) {
                OperationsList.put("idRapportQualite", OperationsList.get("simsId"));
            }
            OperationsList.remove("simsId");

            if (OperationsList.has("operationLabelLg1") && OperationsList.has("operationLabelLg2") ) {
                OperationsList.put("label", this.formatLabelOperationPogues(OperationsList));
            }
            OperationsList.remove("operationLabelLg1");
            OperationsList.remove("operationLabelLg2");

            if (OperationsList.has("operationAltLabelLg1") && OperationsList.has("operationAltLabelLg2") ) {
                OperationsList.put("altLabel", this.formatOperationAltLabelPogues(OperationsList));
            }

            OperationsList.remove("operationAltLabelLg1");
            OperationsList.remove("operationAltLabelLg2");
            OperationsList.remove("typeLabelLg1");
            OperationsList.remove("typeLabelLg2");



        }
        return operationsList.toString();

    }







    protected JSONArray formatLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("seriesLabelLg1"));
        lg2.put("contenu", obj.getString("seriesLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONArray formatAltLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("seriesAltLabelLg1"));
        lg2.put("contenu", obj.getString("seriesAltLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONArray formatLabelOperationPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("operationLabelLg1"));
        lg2.put("contenu", obj.getString("operationLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONArray formatOperationAltLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("operationAltLabelLg1"));
        lg2.put("contenu", obj.getString("operationAltLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }


    protected JSONObject formatTypePogues(JSONObject obj) {
        JSONObject type = new JSONObject();

        type.put("id",obj.getString("typeId"));
        type.put("uri",obj.getString("type"));
        type.put("label",obj.get("label"));

        return type;
    }

    protected JSONArray formatTypeLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("typeLabelLg1"));
        lg2.put("contenu", obj.getString("typeLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONObject formatFamilyPogues(JSONObject obj) {
        JSONObject family = new JSONObject();

        family.put("id",obj.getString("familyId"));
        family.put("uri",obj.getString("family"));
        family.put("label",obj.get("label"));

        return family;
    }

    protected JSONArray formatFamilyLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("familyLabelLg1"));
        lg2.put("contenu", obj.getString("familyLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONObject formatFrequencyPogues(JSONObject obj) {
        JSONObject frequency = new JSONObject();


        frequency.put("id",obj.getString("periodicityId"));
        frequency.put("uri",obj.getString("periodicity"));
        frequency.put("label",obj.get("label"));

        return frequency;
    }
    protected JSONArray formatFrequencyLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("periodicityLabelLg1"));
        lg2.put("contenu", obj.getString("periodicityLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    protected JSONObject formatOperationSeriePogues(JSONObject obj) {


        JSONObject serie = new JSONObject();

        serie.put("id",obj.getString("seriesId"));
        serie.put("uri",obj.getString("series"));
        serie.put("label",obj.get("label"));

        return serie;
    }

    protected JSONObject formatOperationByCodeSeriePogues(JSONObject obj) {
        JSONObject serie = new JSONObject();

        serie.put("id",obj.getString("seriesId"));
        serie.put("uri",obj.getString("series"));
        serie.put("label",obj.get("label"));
        serie.put("altLabel",obj.get("altLabel"));

        return serie;
    }

    protected JSONArray formatOperationSerieLabelPogues(JSONObject obj) {
        JSONArray label = new JSONArray();

        JSONObject lg1 = new JSONObject();
        JSONObject lg2 = new JSONObject();

        lg1.put("langue", Config.LG1);
        lg2.put("langue", Config.LG2);
        lg1.put("contenu", obj.getString("seriesLabelLg1"));
        lg2.put("contenu", obj.getString("seriesLabelLg2"));

        label.put(lg1);
        label.put(lg2);

        return label;
    }

    public Map<String, Object> initParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("CODELIST_GRAPH", Config.BASE_GRAPH+Config.CODELIST_GRAPH);
        params.put("LG1", Config.LG1);
        params.put("LG2", Config.LG2);


        return params;
    }




}
