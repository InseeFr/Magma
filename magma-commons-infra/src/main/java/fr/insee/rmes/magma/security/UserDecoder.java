package fr.insee.rmes.magma.security;

import fr.insee.rmes.magma.utils.exceptions.RmesException;

import java.util.Optional;

public interface UserDecoder {

    Optional<User> fromPrincipal(Object principal) throws RmesException;
}
