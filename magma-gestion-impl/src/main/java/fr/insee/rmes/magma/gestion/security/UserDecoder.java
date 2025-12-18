package fr.insee.rmes.magma.gestion.security;

import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;

import java.util.Optional;

public interface UserDecoder {

    Optional<User> fromPrincipal(Object principal) throws RmesException;
}
