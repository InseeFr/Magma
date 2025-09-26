package fr.insee.rmes.metadata.queries;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Path;
import java.util.Map;

@Component
@Slf4j
public record QueryBuilder(Configuration freemarkerConfiguration) {
    public static final Path ASCENDANTS_OR_DESCENDANTS = Path.of("geographie/getAscendantsOrDescendantsByCodeTypeDate.ftlh");
    public static final Path CONCEPT = Path.of("concepts/getConceptByCode.ftlh");
    public static final Path CONCEPTS = Path.of("concepts/getConceptsByLabel.ftlh");
    public static final Path NEARBY_CONCEPTS = Path.of("concepts/getLinkedConceptsQuery.ftlh");
    public static final Path TERRITOIRE = Path.of("geographie/getTerritoireByCodeDateNomCommune.ftlh");
    public static final Path PRECEDENTS = Path.of("geographie/getPreviousOrNextByCodeTypeDate.ftlh");
    public static final Path PROJETES = Path.of("geographie/getProjectionByCodeTypeDate.ftlh");
    //    public static final Path IRIS = Path.of("geographie/getIrisByCodeDate.ftlh");
    public static final Path IRIS = Path.of("geographie/getTerritoireByCodeDateNomCommune.ftlh");
    public static final Path LIEN_COMMUNE_IRIS = Path.of("geographie/hasIrisDescendant.ftlh");
    public static final Path LIEN_PAYS = Path.of("geographie/getPays.ftlh");
    public static final Path DESCENDANTS_PAYS = Path.of("geographie/getPaysDescendants.ftlh");
    public static final Path PAYS_PRECEDENTS = Path.of("geographie/getPaysPrecedents.ftlh");
    public static final Path PAYS_SUIVANTS = Path.of("geographie/getPaysSuivants.ftlh");


    public Query build(Map<String, Object> parameters, Path queryfile) {
        Template template;
        Writer out = new StringWriter();
        try {
            template = freemarkerConfiguration.getTemplate(queryfile.toString());
            template.process(parameters, out);
        } catch (IOException | TemplateException e) {
            //TODO lever une Exception non contrôlée et utiliser un ExceptionHandler au niveau des contrôleurs pour traiter l'erreur
            log.error("Can't read query {} : ", queryfile, e);
        }
        return new Query(out);
    }

}
