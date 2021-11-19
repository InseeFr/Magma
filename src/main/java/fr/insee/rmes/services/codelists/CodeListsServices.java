package fr.insee.rmes.services.codelists;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.List;

@Service
public interface CodeListsServices {

	String getAllCodesLists() throws RmesException;

	String getCodesList(String notation) throws RmesException;

}
