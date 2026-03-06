package fr.insee.rmes.persistence;

import org.eclipse.rdf4j.repository.Repository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RepositoryUtilsTest {

    @Test
    void shouldNullInitRepository(){
        String emptySesameServer="";
        String nullSesameServer=null;
        boolean nullBecauseOfEmptySesameServer= RepositoryUtils.initRepository(emptySesameServer,"mockedRepositoryID")==null;
        boolean nullBecauseOfNullSesameServer= RepositoryUtils.initRepository(nullSesameServer,"mockedRepositoryID")==null;
        assertTrue(nullBecauseOfEmptySesameServer && nullBecauseOfNullSesameServer);
    }

    @Test
    void shouldReturnNotNullRepository(){
        Repository result = RepositoryUtils.initRepository("emptySesameServer","mockedRepositoryID");
        assertNotNull(result);
    }

}