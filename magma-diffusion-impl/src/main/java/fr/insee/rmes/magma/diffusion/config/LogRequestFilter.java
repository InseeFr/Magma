package fr.insee.rmes.magma.diffusion.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.container.ContainerRequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import java.io.IOException;


@Slf4j
@WebFilter
public class LogRequestFilter extends AbstractRequestLoggingFilter {

	public LogRequestFilter() {
		super.setIncludeQueryString(true);
		super.setIncludePayload(true);
	}
    

	private StringBuilder logRequest(ContainerRequestContext requestContext) {
		StringBuilder sb = new StringBuilder();
    	   sb.append(requestContext.getMethod()).append(" ").append(requestContext.getUriInfo().getPath());
		return sb;
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		log.info(message);
	}

	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
		log.info(message);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		super.doFilterInternal(request, response, filterChain);
		log.info("REQUEST PROCESSED WITH STATUS: {}", response.getStatus());
	}
}
