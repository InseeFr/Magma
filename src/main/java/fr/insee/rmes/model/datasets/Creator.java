package fr.insee.rmes.model.datasets;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class Creator {
    private List<String> creator;
    public Creator(List<String> creator) {
        this.creator=creator;
    }


    @Override
    public String toString() {
        String rep = "[";
        for (int i=0; i<creator.toArray().length;i++){
            rep = rep + creator.toArray()[i];
            if (i < (creator.toArray().length-1) ){
                rep = rep + ",";
            }
        }
        rep = rep + "]";
        return rep;
    }
}
