package fr.insee.rmes.services.structures;

import org.springframework.stereotype.Service;

import fr.insee.rmes.utils.exceptions.RmesException;



@Service
public interface StructuresServices {

	String getAllStructures() throws RmesException;

	String getStructure(String id) throws RmesException;

	String getAllComponents() throws RmesException;

	String getComponent(String id) throws RmesException;


}
