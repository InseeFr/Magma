package fr.insee.security.internal;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class InseeJwtGrantedAuthoritiesConverterTest {

    @Test
    void shouldReturnBullPointerExceptionWhenConvertResult() {
        InseeJwtGrantedAuthoritiesConverter converter= new InseeJwtGrantedAuthoritiesConverter("roleClaimKey","keyForRolesInRoleClaim");

        Map<String, Object> headers = new HashMap<>();
        headers.put("key",new JSONObject().put("firstMockedKey","firstMockedElement"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("key",new JSONObject().put("secondMockedKey","secondMockedElement"));

        Instant issuedAt = Instant.ofEpochSecond(100);
        Instant expiresAt = Instant.ofEpochSecond(200);

        Jwt jwt = new Jwt("tokenValue",issuedAt,expiresAt, headers, claims);

        assertThrows(NullPointerException.class, ()->converter.convert(jwt));

    }
}