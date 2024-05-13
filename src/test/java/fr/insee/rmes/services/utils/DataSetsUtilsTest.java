package fr.insee.rmes.services.utils;

public class DataSetsUtilsTest {
    public static final String EXPECTED_JSON_SET_TITRE_LIST = "[LangContent(lang=fr, content=elementLg1), LangContent(lang=en, content=elementLg2)]";
    public static final String EXPECTED_MAP_INIT_PARAMS = "{STRUCTURES_GRAPH=StructureGraphTest, ORGANISATIONS_GRAPH=OrganisationsGraphTest, DATASETS_GRAPH=DatasetGraphTest, LG2=en, ADMS_GRAPH=AdmsGraphTest, CODES_GRAPH=CodesGraphTest, LG1=fr, ONTOLOGIES_GRAPH=OntologiesGraphTest, CONCEPTS_GRAPH=ConceptsGraphTest, OPERATIONS_GRAPH=OperationsGraphTest}";
    public static final String EXPECTED_THEMELIST_GET_THEME_MODEL_SWAGGERS = "[ThemeModelSwagger(uri=uri0, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg10), LabelDataSet(lang=en, content=labelThemeLg20)], themeTaxonomy=themeTaxonomy0), ThemeModelSwagger(uri=uri1, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg11), LabelDataSet(lang=en, content=labelThemeLg21)], themeTaxonomy=themeTaxonomy1), ThemeModelSwagger(uri=uri2, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg12), LabelDataSet(lang=en, content=labelThemeLg22)], themeTaxonomy=themeTaxonomy2), ThemeModelSwagger(uri=uri3, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg13), LabelDataSet(lang=en, content=labelThemeLg23)], themeTaxonomy=themeTaxonomy3)]";
    public static final String EXPECTED_LANGCONTENTLIST_SET_ID_LABEL = "IdLabel(id=id, label=[LangContent(lang=fr, content=content1), LangContent(lang=en, content=content2)], type=null)";
    public static final String EXPECTED_LANGCONTENTLIST_SET_ID_LABEL_WITH_TYPE = "IdLabel(id=id, label=[LangContent(lang=fr, content=content1), LangContent(lang=en, content=content2)], type=type)";
    public static final String URI_TEST = "http://uri.test";
    public static final String DATA_SET_LIST= """
           [
              {
              "dateMiseAJour":"2024-03-28T16:24:07.626773775",
              "dateCreation":"2024-03-28T13:42:13.696871447",
              "statutValidation":"Modified",
              "titreLg1":"Test1",
              "id":"jd1000",
              "titreLg2":"all fields",
              "uri":"http://bauhaus/catalogues/jeuDeDonnees/jd1000"
              },
              {
              "dateMiseAJour":"2024-03-28T13:58:46.726856504",
              "dateCreation":"2024-03-28T13:58:46.726856504",
              "statutValidation":"Unpublished",
              "titreLg1":"Test2",
              "id":"jd1001",
              "titreLg2":"title",
              "uri":"http://bauhaus/catalogues/jeuDeDonnees/jd1001"
              }
           ]
           """
           ;
    public static final String EXPECTED_GET_DATA_SET_LIST= """
            [
              {
                "id": "jd1000",
                "uri": "http://bauhaus/catalogues/jeuDeDonnees/jd1000",
                "modified": "2024-03-28T16:24:07.626773775",
                "validationState": "Modified",
                "title": [
                  {
                    "lang": "fr",
                    "content": "Test1"
                  },
                  {
                    "lang": "en",
                    "content": "all fields"
                  }
                ],
                "idWithTypeId": {
                  "id": "jd1000"
                },
                "uriWithTypeUri": {
                  "uri": "http://bauhaus/catalogues/jeuDeDonnees/jd1000"
                }
              },
              {
                "id": "jd1001",
                "uri": "http://bauhaus/catalogues/jeuDeDonnees/jd1001",
                "modified": "2024-03-28T13:58:46.726856504",
                "validationState": "Unpublished",
                "title": [
                  {
                    "lang": "fr",
                    "content": "Test2"
                  },
                  {
                    "lang": "en",
                    "content": "title"
                  }
                ],
                "idWithTypeId": {
                  "id": "jd1001"
                },
                "uriWithTypeUri": {
                  "uri": "http://bauhaus/catalogues/jeuDeDonnees/jd1001"
                }
              }
            ]
            """
            ;

    public static final String CATALOGUE_RESULT_RELATIONS = "{\"relations\":\"http://www.insee.fr,http://www.rdf.insee.fr\"}";
    public static final String EXPECTED_RELATIONS = "[\"http://www.insee.fr\", \"http://www.rdf.insee.fr\"]";
}
