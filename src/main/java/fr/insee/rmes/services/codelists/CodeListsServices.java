package fr.insee.rmes.services.codelists;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.List;

@Service
public interface CodeListsServices {

	List getAllCodesLists() throws RmesException;

	Object getCodesList(String notation) throws RmesException;

}
