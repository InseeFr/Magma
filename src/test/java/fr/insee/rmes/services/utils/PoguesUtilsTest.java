package fr.insee.rmes.services.utils;

public class PoguesUtilsTest {
    public static final String SERIES_LIST = "[{\"type\":\"http://bauhaus/codes/categorieSource/S\",\"familyId\":\"s15\",\"periodicityId\":\"U\",\"periodicityLabelLg2\":\"Pluriannuelle\",\"proprietaire\":\"DG75-E430\",\"periodicityLabelLg1\":\"Pluriannuelle\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"periodicity\":\"http://bauhaus/codes/frequence/U\",\"typeId\":\"S\",\"id\":\"s1001\",\"seriesLabelLg1\":\"Enquête innovation\",\"seriesAltLabelLg1\":\"CIS\",\"nbOperation\":\"7\",\"family\":\"http://bauhaus/operations/famille/s15\",\"familyLabelLg1\":\"Innovation\",\"seriesAltLabelLg2\":\"CIS\",\"familyLabelLg2\":\"Innovation\",\"seriesLabelLg2\":\"Innovation survey\"},{\"type\":\"http://bauhaus/codes/categorieSource/S\",\"familyId\":\"s2121\",\"periodicityId\":\"P\",\"periodicityLabelLg2\":\"Ponctuelle ou apériodique\",\"proprietaire\":\"DG75-F301\",\"periodicityLabelLg1\":\"Ponctuelle ou apériodique\",\"series\":\"http://bauhaus/operations/serie/s1002\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"periodicity\":\"http://bauhaus/codes/frequence/P\",\"typeId\":\"S\",\"id\":\"s1002\",\"seriesLabelLg1\":\"Enquête auprès des personnes sans domicile\",\"nbOperation\":\"3\",\"family\":\"http://bauhaus/operations/famille/s2121\",\"familyLabelLg1\":\"Grande pauvreté\",\"familyLabelLg2\":\"Extreme poverty\",\"seriesLabelLg2\":\"Survey about homeless\"}]";
    public static final String EXPECTED_GET_SERIES_LIST= """
            [
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête innovation"
                  },
                  {
                    "langue": "en",
                    "contenu": "Innovation survey"
                  }
                ],
                "type": {
                  "id": "S",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Enquête"
                    },
                    {
                      "langue": "en",
                      "contenu": "Survey"
                    }
                  ],
                  "uri": "http://bauhaus/codes/categorieSource/S"
                },
                "series": "http://bauhaus/operations/serie/s1001",
                "id": "s1001",
                "frequence": {
                  "id": "U",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Pluriannuelle"
                    },
                    {
                      "langue": "en",
                      "contenu": "Pluriannuelle"
                    }
                  ],
                  "uri": "http://bauhaus/codes/frequence/U"
                },
                "nbOperation": "7",
                "famille": {
                  "id": "s15",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Innovation"
                    },
                    {
                      "langue": "en",
                      "contenu": "Innovation"
                    }
                  ],
                  "uri": "http://bauhaus/operations/famille/s15"
                },
                "proprietaire": [
                  "DG75-E430"
                ]
              },
              {
                "altLabel": [],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête auprès des personnes sans domicile"
                  },
                  {
                    "langue": "en",
                    "contenu": "Survey about homeless"
                  }
                ],
                "type": {
                  "id": "S",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Enquête"
                    },
                    {
                      "langue": "en",
                      "contenu": "Survey"
                    }
                  ],
                  "uri": "http://bauhaus/codes/categorieSource/S"
                },
                "series": "http://bauhaus/operations/serie/s1002",
                "id": "s1002",
                "frequence": {
                  "id": "P",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Ponctuelle ou apériodique"
                    },
                    {
                      "langue": "en",
                      "contenu": "Ponctuelle ou apériodique"
                    }
                  ],
                  "uri": "http://bauhaus/codes/frequence/P"
                },
                "nbOperation": "3",
                "famille": {
                  "id": "s2121",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Grande pauvreté"
                    },
                    {
                      "langue": "en",
                      "contenu": "Extreme poverty"
                    }
                  ],
                  "uri": "http://bauhaus/operations/famille/s2121"
                },
                "proprietaire": [
                  "DG75-F301"
                ]
              }
            ]
            """
            ;

    public static final String SERIES_EXAMPLE = "[{\"type\":\"http://bauhaus/codes/categorieSource/S\",\"familyId\":\"s15\",\"periodicityId\":\"U\",\"periodicityLabelLg2\":\"Pluriannuelle\",\"periodicityLabelLg1\":\"Pluriannuelle\",\"proprietaire\":\"DG75-E430\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"periodicity\":\"http://bauhaus/codes/frequence/U\",\"typeId\":\"S\",\"id\":\"s1001\",\"seriesLabelLg1\":\"Enquête innovation\",\"seriesAltLabelLg1\":\"CIS\",\"nbOperation\":\"7\",\"family\":\"http://bauhaus/operations/famille/s15\",\"familyLabelLg1\":\"Innovation\",\"seriesAltLabelLg2\":\"CIS\",\"familyLabelLg2\":\"Innovation\",\"seriesLabelLg2\":\"Innovation survey\"}]";

    public static final String EXPECTED_SERIE = """
            {
              "altLabel": [
                {
                  "langue": "fr",
                  "contenu": "CIS"
                },
                {
                  "langue": "en",
                  "contenu": "CIS"
                }
              ],
              "label": [
                {
                  "langue": "fr",
                  "contenu": "Enquête innovation"
                },
                {
                  "langue": "en",
                  "contenu": "Innovation survey"
                }
              ],
              "type": {
                "id": "S",
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête"
                  },
                  {
                    "langue": "en",
                    "contenu": "Survey"
                  }
                ],
                "uri": "http://bauhaus/codes/categorieSource/S"
              },
              "series": "http://bauhaus/operations/serie/s1001",
              "id": "s1001",
              "frequence": {
                "id": "U",
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Pluriannuelle"
                  },
                  {
                    "langue": "en",
                    "contenu": "Pluriannuelle"
                  }
                ],
                "uri": "http://bauhaus/codes/frequence/U"
              },
              "nbOperation": "7",
              "famille": {
                "id": "s15",
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Innovation"
                  },
                  {
                    "langue": "en",
                    "contenu": "Innovation"
                  }
                ],
                "uri": "http://bauhaus/operations/famille/s15"
              },
              "proprietaire": [
                "DG75-E430"
              ]
            }
            """
        ;

    public static final String OPERATION_BY_SERIE = "[{\"uri\":\"http://bauhaus/operations/operation/s2106\",\"seriesId\":\"s1001\",\"simsId\":\"2088\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s2106\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2022\",\"operationLabelLg2\":\"Quality report: Community Innovation Survey 2022\",\"operationAltLabelLg2\":\"CIS\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1558\",\"seriesId\":\"s1001\",\"simsId\":\"1975\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1558\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2020\",\"operationLabelLg2\":\"Community Innovation Survey 2020\",\"operationAltLabelLg2\":\"CIS 2020\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2020\",\"seriesLabelLg2\":\"Innovation survey\"}]";
    public static final String EXPECTED_OPERATION_BY_SERIE = """
            [
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête capacité à innover et stratégie 2022"
                  },
                  {
                    "langue": "en",
                    "contenu": "Quality report: Community Innovation Survey 2022"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s2106",
                "serie": {
                  "id": "s1001",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Enquête innovation"
                    },
                    {
                      "langue": "en",
                      "contenu": "Innovation survey"
                    }
                  ],
                  "uri": "http://bauhaus/operations/serie/s1001"
                },
                "id": "s2106"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2020"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2020"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête capacité à innover et stratégie 2020"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community Innovation Survey 2020"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1558",
                "serie": {
                  "id": "s1001",
                  "label": [
                    {
                      "langue": "fr",
                      "contenu": "Enquête innovation"
                    },
                    {
                      "langue": "en",
                      "contenu": "Innovation survey"
                    }
                  ],
                  "uri": "http://bauhaus/operations/serie/s1001"
                },
                "id": "s1558"
              }
            ]
            """
            ;

    public static final String OPERATION_EXAMPLE = "{\"proprietaire\":\"DG75-E430\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"operationId\":\"s2106\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2022\",\"operationLabelLg2\":\"Quality report: Community Innovation Survey 2022\",\"seriesAltLabelLg1\":\"CIS\",\"seriesLabelLg1\":\"Enquête innovation\",\"uri\":\"http://bauhaus/operations/operation/s2106\",\"seriesAltLabelLg2\":\"CIS\",\"seriesId\":\"s1001\",\"simsId\":\"2088\",\"seriesLabelLg2\":\"Innovation survey\"}";
    public static final String EXPECTED_OPERATION = """
            {
              "serie": {
                "id": "s1001",
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête innovation"
                  },
                  {
                    "langue": "en",
                    "contenu": "Innovation survey"
                  }
                ],
                "uri": "http://bauhaus/operations/serie/s1001"
              },
              "id": "s2106",
              "label": [
                {
                  "langue": "fr",
                  "contenu": "Enquête capacité à innover et stratégie 2022"
                },
                {
                  "langue": "en",
                  "contenu": "Quality report: Community Innovation Survey 2022"
                }
              ],
              "uri": "http://bauhaus/operations/operation/s2106",
              "proprietaire": "DG75-E430"
            }
            """
            ;
}
