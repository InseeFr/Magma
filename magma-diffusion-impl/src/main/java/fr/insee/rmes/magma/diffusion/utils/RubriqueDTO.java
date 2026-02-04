package fr.insee.rmes.magma.diffusion.utils;


public record RubriqueDTO (
    String id,
    String uri,
    String idParent,
    String type,
    String titreLg1,
    String titreLg2,
    String valeurSimple,
    String labelLg1,
    String labelLg2,
    String codeUri,
    String organisationUri,
    Boolean hasDocLg1,
    Boolean hasDocLg2,
    String labelObjLg1,
    String labelObjLg2,
    String maxOccurs,
    String geoUri
){

        public RubriqueDTO withLabelLg1(String labelLg1) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                    hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withLabelLg2(String labelLg2) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withValeurSimple(String valeurSimple) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                    hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withTitreLg1(String titreLg1) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withTitreLg2(String titreLg2) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withCodeUri(String codeUri) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withOrganisationUri(String organisationUri) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withHasDocLg1(Boolean hasDocLg1) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withHasDocLg2(Boolean hasDocLg2) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withLabelObjLg1(String labelObjLg1) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withLabelObjLg2(String labelObjLg2) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withMaxOccurs(String maxOccurs) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

        public RubriqueDTO withGeoUri(String geoUri) {
            return new RubriqueDTO(id, uri, idParent, type, titreLg1, titreLg2, valeurSimple, labelLg1, labelLg2, codeUri, organisationUri,
                hasDocLg1, hasDocLg2, labelObjLg1, labelObjLg2, maxOccurs, geoUri);
        }

}