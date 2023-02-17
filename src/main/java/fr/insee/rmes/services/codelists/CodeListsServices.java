package fr.insee.rmes.services.codelists;

import fr.insee.rmes.utils.exceptions.RmesException;
import org.springframework.stereotype.Service;


@Service
public interface CodeListsServices {

	String getAllCodesLists() throws RmesException;

	String getCodesList(String notation) throws RmesException;

	String getCodesListDateMiseAJour(String notation) throws RmesException;
}
