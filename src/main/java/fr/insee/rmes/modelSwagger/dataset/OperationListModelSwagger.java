package fr.insee.rmes.modelSwagger.dataset;

import java.util.List;

public class OperationListModelSwagger {

    private List<OperationListModelSwagger> OperationListModelSwaggerS= null;

    public OperationListModelSwagger() {}

    public OperationListModelSwagger(List<OperationListModelSwagger> OperationListModelSwaggerS) {
        this.OperationListModelSwaggerS=OperationListModelSwaggerS;
    }

    public List<OperationListModelSwagger> getOperationListModelSwaggerS () {
        return OperationListModelSwaggerS;
    }
}
