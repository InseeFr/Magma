package fr.insee.rmes.services.pogues;

import java.util.HashMap;

import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import fr.insee.rmes.persistence.RdfService;
import fr.insee.rmes.utils.Constants;
import fr.insee.rmes.utils.config.Config;
import fr.insee.rmes.utils.exceptions.RmesException;

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
            OperationsList.put("id",OperationsList.get("operationId"));
            OperationsList.remove("operationId");
            OperationsList.put("idRapportQualite",OperationsList.get("simsId"));
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


    protected JSONArray formatTypePogues(JSONObject obj) {
        JSONArray type = new JSONArray();

        JSONObject typeDetail = new JSONObject();

        typeDetail.put("id",obj.getString("typeId"));
        typeDetail.put("uri",obj.getString("type"));
        typeDetail.put("label",obj.get("label"));
        type.put(typeDetail);


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

    protected JSONArray formatFamilyPogues(JSONObject obj) {
        JSONArray family = new JSONArray();
        JSONObject familyDetail = new JSONObject();


        familyDetail.put("id",obj.getString("familyId"));
        familyDetail.put("uri",obj.getString("family"));
        familyDetail.put("label",obj.get("label"));

        family.put(familyDetail);
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

    protected JSONArray formatFrequencyPogues(JSONObject obj) {
        JSONArray frequency = new JSONArray();
        JSONObject frequencyDetail = new JSONObject();

        frequencyDetail.put("id",obj.getString("periodicityId"));
        frequencyDetail.put("uri",obj.getString("periodicity"));
        frequencyDetail.put("label",obj.get("label"));
        frequency.put(frequencyDetail);

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

    protected JSONArray formatOperationSeriePogues(JSONObject obj) {
        JSONArray serie = new JSONArray();

        JSONObject serieDetail = new JSONObject();

        serieDetail.put("id",obj.getString("seriesId"));
        serieDetail.put("uri",obj.getString("series"));
        serieDetail.put("label",obj.get("label"));
        serie.put(serieDetail);


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