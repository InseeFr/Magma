package fr.insee.rmes.metadata.queries;

import fr.insee.rmes.metadata.queryexecutor.Query;
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
public record QueryBuilder (Configuration freemarkerConfiguration) {
    public static final Path ASCENDANTS_OR_DESCENDANTS = Path.of("geographie/getAscendantsOrDescendantsByCodeTypeDate.ftlh");

    public Query build(Map<String, Object> parameters, Path queryfile) {
        Template template;
        Writer out = new StringWriter();
        try {
            template = freemarkerConfiguration.getTemplate(queryfile.toString());
            template.process(parameters, out);
        }
        catch (IOException | TemplateException e) {
            //TODO lever une Exception non contrôlée et utiliser un ExceptionHandler au niveau des contrôleurs pour traiter l'erreur
            log.error("Can't read query {} : ", queryfile, e);
        }
        return new Query(out);
    }

}
