package fr.insee.rmes.persistence;

import fr.insee.rmes.utils.config.freemarker.FreemarkerConfig;
import fr.insee.rmes.utils.exceptions.RmesException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeMarkerUtils {

    private final Configuration freemarkerConfiguration = FreemarkerConfig.getCfg();
    @Getter
	private static final FreeMarkerUtils instance = new FreeMarkerUtils();

    public String buildRequest(String root, String fileName, Map<String, Object> params) throws RmesException {
        Template temp;
        Writer out = new StringWriter();
        try {
            temp = freemarkerConfiguration.getTemplate(root + fileName);
            temp.process(params, out);
        } catch (IOException | TemplateException e) {
            throw new RmesException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage(),
                    "Can't read query " + fileName);
        }
        return out.toString();
    }

}
