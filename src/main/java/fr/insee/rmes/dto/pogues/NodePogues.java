package fr.insee.rmes.dto.pogues;

import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class NodePogues {

    public class Label{
        @Schema
        public String contenu;
        @Schema
        public String langue;

        public String getContenu() {
            return contenu;
        }

        public void setContenu(String contenu) {
            this.contenu = contenu;
        }

        public String getLangue() {
            return langue;
        }

        public void setLangue(String langue) {
            this.langue = langue;
        }

        public Label() {
        }
    }

    public class AltLabel{
        @Schema
        public String contenu;
        @Schema
        public String langue;

        public String getContenu() {
            return contenu;
        }

        public void setContenu(String contenu) {
            this.contenu = contenu;
        }

        public String getLangue() {
            return langue;
        }

        public void setLangue(String langue) {
            this.langue = langue;
        }

        public AltLabel() {
        }
    }

    public class Type{
        @Schema
        public String id;
        @Schema
        public String uri;
        @Schema
        public List<Label> label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<Label> getLabel() {
            return label;
        }

        public void setLabel(List<Label> label) {
            this.label = label;
        }

        public Type() {
        }
    }

    public class Famille{
        @Schema
        public String id;
        @Schema
        public String uri;
        @Schema
        public List<Label> label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<Label> getLabel() {
            return label;
        }

        public Famille() {
        }

        public void setLabel(List<Label> label) {
            this.label = label;


        }
    }

    public class FrequenceCollecte{
        @Schema
        public String id;
        @Schema
        public String uri;
        @Schema
        public List<Label> label;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<Label> getLabel() {
            return label;
        }

        public FrequenceCollecte() {
        }

        public void setLabel(List<Label> label) {
            this.label = label;
        }
    }

        public class Serie {
        @Schema
        public String Id;
        @Schema
        public String uri;
        @Schema
        public List <Label> label;

            public Serie() {
            }

            public String getId() {
                return Id;
            }

            public void setId(String id) {
                Id = id;
            }

            public String getUri() {
                return uri;
            }

            public void setUri(String uri) {
                this.uri = uri;
            }

            public List<Label> getLabel() {
                return label;
            }

            public void setLabel(List<Label> label) {
                this.label = label;
            }
        }

    public class Root{
        public Root() {
        }
        @Schema
        public String id;
        @Schema
        public List<Label> label;
        public String uri;
        @Schema
        public List<AltLabel> altLabel;
        @Schema
        public Type type;
        @Schema
        public String operations;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Label> getLabel() {
            return label;
        }

        public void setLabel(List<Label> label) {
            this.label = label;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<AltLabel> getAltLabel() {
            return altLabel;
        }

        public void setAltLabel(List<AltLabel> altLabel) {
            this.altLabel = altLabel;
        }

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public String getOperations() {
            return operations;
        }

        public void setOperations(String operations) {
            this.operations = operations;
        }

        public Famille getFamille() {
            return famille;
        }

        public void setFamille(Famille famille) {
            this.famille = famille;
        }

        public FrequenceCollecte getFrequenceCollecte() {
            return frequenceCollecte;
        }

        public void setFrequenceCollecte(FrequenceCollecte frequenceCollecte) {
            this.frequenceCollecte = frequenceCollecte;
        }

        public Famille famille;
        public FrequenceCollecte frequenceCollecte;
        public Serie serie;
    }


}
