package fr.insee.rmes.magma.gestion.security.internal;

import com.nimbusds.jose.shaded.gson.JsonArray;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Optional.empty;

public record InseeJwtGrantedAuthoritiesConverter(String roleClaimKey, String keyForRolesInRoleClaim) implements Converter<Jwt, Collection<GrantedAuthority>> {
    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        return extractRoles(source.getClaims())
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    private Stream<String> extractRoles(Map<String, Object> claims) {
        RoleClaim roleClaim=roleClaimFrom(claims);
        ArrayOfRoles arrayOfRoles=roleClaim.arrayOfRoles();
        return arrayOfRoles.stream();
    }

    private RoleClaim roleClaimFrom(Map<String, Object> claims) {
        var valueForRoleClaim=switch (claims.get(this.roleClaimKey)) {
            case JsonObject objectForRoles -> objectForRoles.getAsJsonArray(this.keyForRolesInRoleClaim);
            case Map < ?, ?> mapForRoles -> mapForRoles.get(this.keyForRolesInRoleClaim);
            default -> empty();
        };
        return roleClaimFrom(valueForRoleClaim);
    }

    private RoleClaim roleClaimFrom(Object listOrJsonArray) {
        return switch (listOrJsonArray){
            case JsonArray jsonArray -> () -> () -> jsonArrayToStream(jsonArray);
            case List<?> list -> () -> () -> list.stream().map(this::jsonElementOrElseToString);
            default -> () -> Stream::empty;
        };
    }

    private String jsonElementOrElseToString(Object element) {
        if (element instanceof JsonElement jsonElement){
            return jsonElement.getAsString();
        }
        return element.toString();
    }

    private Stream<String> jsonArrayToStream(JsonArray jsonArray) {
        return StreamSupport.stream(Spliterators.spliterator(jsonArray.iterator(), jsonArray.size(), 0), false)
                .map(JsonElement::getAsString);
    }

    private interface RoleClaim{
        ArrayOfRoles arrayOfRoles();
    }

    private interface ArrayOfRoles{
        Stream<String> stream();
    }

}
