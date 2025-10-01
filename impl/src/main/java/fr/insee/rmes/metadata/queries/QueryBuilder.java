package fr.insee.rmes.metadata.queries;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.String;
import java.util.Map;

@Component
@Slf4j
public record QueryBuilder(Configuration freemarkerConfiguration) {
    public static final String ASCENDANTS_OR_DESCENDANTS = "geographie/getAscendantsOrDescendantsByCodeTypeDate.ftlh";
    public static final String CONCEPT = "concepts/getConceptByCode.ftlh";
    public static final String CONCEPTS = "concepts/getConceptsByLabel.ftlh";
    public static final String CONCEPTSUIVANTS = "concepts/getLinkedConceptsQuery.ftlh";
    public static final String TERRITOIRE = "geographie/getTerritoireByCodeDateNomCommune.ftlh";
    public static final String COMMUNE_CANTONS = "geographie/getCommuneCantonsByCodeDate.ftlh";
    public static final String PRECEDENTS = "geographie/getPreviousOrNextByCodeTypeDate.ftlh";
    public static final String PROJETES = "geographie/getProjectionByCodeTypeDate.ftlh";
    //    public static final String IRIS = "geographie/getIrisByCodeDate.ftlh";
    public static final String IRIS = "geographie/getTerritoireByCodeDateNomCommune.ftlh";
    public static final String LIEN_COMMUNE_IRIS = "geographie/hasIrisDescendant.ftlh";
    public static final String LIEN_PAYS = "geographie/getPays.ftlh";
    public static final String DESCENDANTS_PAYS = "geographie/getPaysDescendants.ftlh";
    public static final String PAYS_PRECEDENTS = "geographie/getPaysPrecedents.ftlh";
    public static final String PAYS_SUIVANTS = "geographie/getPaysSuivants.ftlh";


    public Query build(Map<String, Object> parameters, String queryfile) {
        Template template;
        Writer out = new StringWriter();
        try {
            template = freemarkerConfiguration.getTemplate(queryfile);
            template.process(parameters, out);
        } catch (IOException | TemplateException e) {
            //TODO lever une Exception non contrôlée et utiliser un ExceptionHandler au niveau des contrôleurs pour traiter l'erreur
            log.error("Can't read query {} : ", queryfile, e);
        }
        return new Query(out);
    }

}
