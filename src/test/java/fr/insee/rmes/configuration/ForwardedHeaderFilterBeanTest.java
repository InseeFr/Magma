package fr.insee.rmes.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.web.filter.ForwardedHeaderFilter;

import static org.junit.jupiter.api.Assertions.*;

class ForwardedHeaderFilterBeanTest {

    @Test
    void shouldInitializeConstructor(){
        ForwardedHeaderFilterBean forwardedHeaderFilterBean = new ForwardedHeaderFilterBean();
        ForwardedHeaderFilter forwardedHeaderFilter = forwardedHeaderFilterBean.forwardedHeaderFilter();
        assertNotNull(forwardedHeaderFilter);
    }

}