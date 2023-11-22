package fr.insee.rmes.services.codelists;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;


@Service
public interface CodeListsServices {

	String getAllCodesLists() throws RmesException;

	String getCodesList(String notation) throws RmesException;

	String getCodesListDateMiseAJour(String notation) throws RmesException;

	String getCodesListPagination(String notation, int pageNumber) throws RmesException;
}
