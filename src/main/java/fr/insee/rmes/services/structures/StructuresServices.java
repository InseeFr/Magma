package fr.insee.rmes.services.structures;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;

import java.util.List;

@Service
public interface StructuresServices {

	List getAllStructures() throws RmesException;

	Object getStructure(String id) throws RmesException;

}
