package fr.insee.rmes.magma.gestion.old.services.codelists;

import fr.insee.rmes.magma.gestion.old.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;


@Service
public interface CodeListsServices {

	String getAllCodesLists(String dateMiseAJour) throws RmesException;

	String getCodesList(String notation) throws RmesException;

	String getCodesListForDataset(String notation) throws RmesException;
	String getCodesListDateMiseAJour(String notation) throws RmesException;

	Integer getMaxpage(String notation) throws RmesException;

	String getCodesListPagination(String notation, int pageNumber) throws RmesException;

	String getCodesListWithoutCodes(String notation) throws RmesException;
}
