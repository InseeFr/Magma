package fr.insee.security;

import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.Optional;

public interface UserDecoder {

    Optional<User> fromPrincipal(Object principal) throws RmesException;
}
