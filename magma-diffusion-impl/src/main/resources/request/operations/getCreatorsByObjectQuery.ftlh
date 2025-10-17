SELECT ?label          
FROM <${GRAPH}>
WHERE                                 
            {	 
            	?object a ${typeObject} . 
            	FILTER(STRAFTER(STR(?object),'${uriObject}') = '${idObject}') 	 
            	?object dc:creator ?label. 		 
            } 
            
            
