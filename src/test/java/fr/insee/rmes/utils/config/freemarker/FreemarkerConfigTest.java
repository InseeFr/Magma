package fr.insee.rmes.utils.config.freemarker;

import org.junit.jupiter.api.Test;
import static fr.insee.rmes.utils.config.freemarker.FreemarkerConfig.cfg;
import static org.junit.jupiter.api.Assertions.*;

class FreemarkerConfigTest {

    @Test
    void shouldInitializeCfg(){
        var before = cfg;
        FreemarkerConfig.init();
        var after = cfg;
        assertNotEquals(after,before);
    }

    @Test
    void shouldCompareGetCfgAndInit(){
        FreemarkerConfig.init();
        var before = cfg;
        FreemarkerConfig.getCfg();
        var after = cfg;
        assertEquals(after,before);
    }

}