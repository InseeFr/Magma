package fr.insee.rmes.services.utils;

import java.util.Map;

public class ResponseUtilsTest {
    public static final String EXPECTED_JSON_SET_TITRE_LIST = "[\n" +
            "      {\n" +
            "        \"lang\": \"fr\",\n" +
            "        \"content\": \"elementLg1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"lang\": \"en\",\n" +
            "        \"content\": \"elementLg2\"\n" +
            "      }\n" +
            "    ]";
    public static final String EXPECTED_MAP_INIT_PARAMS = "{STRUCTURES_GRAPH=StructureGraphTest, ORGANISATIONS_GRAPH=OrganisationsGraphTest, DATASETS_GRAPH=DatasetGraphTest, LG2=en, ADMS_GRAPH=AdmsGraphTest, CODES_GRAPH=CodesGraphTest, LG1=fr, CONCEPTS_GRAPH=ConceptsGraphTest, OPERATIONS_GRAPH=OperationsGraphTest}";

    public static final String EXPECTED_THEMELIST_GET_THEME_MODEL_SWAGGERS = "[ThemeModelSwagger(uri=uri0, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg10), LabelDataSet(lang=en, content=labelThemeLg20)], themeTaxonomy=themeTaxonomy0), ThemeModelSwagger(uri=uri1, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg11), LabelDataSet(lang=en, content=labelThemeLg21)], themeTaxonomy=themeTaxonomy1), ThemeModelSwagger(uri=uri2, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg12), LabelDataSet(lang=en, content=labelThemeLg22)], themeTaxonomy=themeTaxonomy2), ThemeModelSwagger(uri=uri3, labelDataSet=[LabelDataSet(lang=fr, content=labelThemeLg13), LabelDataSet(lang=en, content=labelThemeLg23)], themeTaxonomy=themeTaxonomy3)]";

    public static final String EXPECTED_LANGCONTENTLIST_SET_ID_LABEL = "IdLabel(id=id, label=[LangContent(lang=fr, content=content1), LangContent(lang=en, content=content2)], type=null)";

    public static final String EXPECTED_LANGCONTENTLIST_SET_ID_LABEL_WITH_TYPE = "IdLabel(id=id, label=[LangContent(lang=fr, content=content1), LangContent(lang=en, content=content2)], type=type)";
}
