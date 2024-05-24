package fr.insee.rmes.services.utils;

public class PoguesUtilsTest {
    public static final String SERIES_LIST = "[{\"type\":\"http://bauhaus/codes/categorieSource/S\",\"familyId\":\"s15\",\"periodicityId\":\"U\",\"periodicityLabelLg2\":\"Pluriannuelle\",\"proprietaire\":\"DG75-E430\",\"periodicityLabelLg1\":\"Pluriannuelle\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"periodicity\":\"http://bauhaus/codes/frequence/U\",\"typeId\":\"S\",\"id\":\"s1001\",\"seriesLabelLg1\":\"Enquête innovation\",\"seriesAltLabelLg1\":\"CIS\",\"nbOperation\":\"7\",\"family\":\"http://bauhaus/operations/famille/s15\",\"familyLabelLg1\":\"Innovation\",\"seriesAltLabelLg2\":\"CIS\",\"familyLabelLg2\":\"Innovation\",\"seriesLabelLg2\":\"Innovation survey\"},{\"type\":\"http://bauhaus/codes/categorieSource/S\",\"familyId\":\"s2121\",\"periodicityId\":\"P\",\"periodicityLabelLg2\":\"Ponctuelle ou apériodique\",\"proprietaire\":\"DG75-F301\",\"periodicityLabelLg1\":\"Ponctuelle ou apériodique\",\"series\":\"http://bauhaus/operations/serie/s1002\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"periodicity\":\"http://bauhaus/codes/frequence/P\",\"typeId\":\"S\",\"id\":\"s1002\",\"seriesLabelLg1\":\"Enquête auprès des personnes sans domicile\",\"nbOperation\":\"3\",\"family\":\"http://bauhaus/operations/famille/s2121\",\"familyLabelLg1\":\"Grande pauvreté\",\"familyLabelLg2\":\"Extreme poverty\",\"seriesLabelLg2\":\"Survey about homeless\"}]";
    public static final String EXPECTED_GET_SERIES_LIST = """
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
            """;

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
            """;

    public static final String OPERATION_BY_SERIE = "[{\"uri\":\"http://bauhaus/operations/operation/s2106\",\"seriesId\":\"s1001\",\"simsId\":\"2088\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s2106\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2022\",\"operationLabelLg2\":\"Quality report: Community Innovation Survey 2022\",\"operationAltLabelLg2\":\"CIS\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1558\",\"seriesId\":\"s1001\",\"simsId\":\"1975\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1558\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2020\",\"operationLabelLg2\":\"Community Innovation Survey 2020\",\"operationAltLabelLg2\":\"CIS 2020\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2020\",\"seriesLabelLg2\":\"Innovation survey\"}]";
    public static final String OPERATION_WITH_PERIODICIY_BY_SERIE = "[{\"uri\":\"http://bauhaus/operations/operation/s2106\",\"seriesId\":\"s1001\",\"simsId\":\"2088\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s2106\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2022\",\"operationLabelLg2\":\"Quality report: Community Innovation Survey 2022\",\"operationAltLabelLg2\":\"CIS\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1558\",\"seriesId\":\"s1001\",\"simsId\":\"1975\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1558\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2020\",\"operationLabelLg2\":\"Community Innovation Survey 2020\",\"operationAltLabelLg2\":\"CIS 2020\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2020\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1477\",\"seriesId\":\"s1001\",\"simsId\":\"1920\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1477\",\"operationLabelLg1\":\"Enquête capacité à innover et stratégie 2018\",\"operationLabelLg2\":\"Community innovation survey 2018\",\"operationAltLabelLg2\":\"CIS 2018\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2018\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1361\",\"seriesId\":\"s1001\",\"simsId\":\"1553\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1361\",\"operationLabelLg1\":\"Enquête communautaire sur l'innovation 2016\",\"operationLabelLg2\":\"Community innovation survey 2016\",\"operationAltLabelLg2\":\"CIS 2016\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2016\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1199\",\"seriesId\":\"s1001\",\"simsId\":\"1554\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1199\",\"operationLabelLg1\":\"Enquête communautaire sur l'innovation 2014\",\"operationLabelLg2\":\"Community innovation survey 2014\",\"operationAltLabelLg2\":\"CIS 2014\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2014\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1198\",\"seriesId\":\"s1001\",\"simsId\":\"1555\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1198\",\"operationLabelLg1\":\"Enquête communautaire sur l'innovation 2012\",\"operationLabelLg2\":\"Community innovation survey 2012\",\"operationAltLabelLg2\":\"CIS 2012\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2012\",\"seriesLabelLg2\":\"Innovation survey\"},{\"uri\":\"http://bauhaus/operations/operation/s1197\",\"seriesId\":\"s1001\",\"simsId\":\"1556\",\"periodicityId\":\"U\",\"series\":\"http://bauhaus/operations/serie/s1001\",\"typeLabelLg1\":\"Enquête\",\"typeLabelLg2\":\"Survey\",\"operationId\":\"s1197\",\"operationLabelLg1\":\"Enquête communautaire sur l'innovation 2010\",\"operationLabelLg2\":\"Community innovation survey 2010\",\"operationAltLabelLg2\":\"CIS 2010\",\"seriesLabelLg1\":\"Enquête innovation\",\"operationAltLabelLg1\":\"CIS 2010\",\"seriesLabelLg2\":\"Innovation survey\"}]";
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
            """;

    public static final String EXPECTED_OPERATION_WITH_PERIODICIY_BY_SERIE = """
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1558"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2018"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2018"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête capacité à innover et stratégie 2018"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community innovation survey 2018"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1477",
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1477"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2016"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2016"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête communautaire sur l'innovation 2016"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community innovation survey 2016"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1361",
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1361"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2014"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2014"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête communautaire sur l'innovation 2014"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community innovation survey 2014"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1199",
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1199"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2012"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2012"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête communautaire sur l'innovation 2012"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community innovation survey 2012"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1198",
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1198"
              },
              {
                "altLabel": [
                  {
                    "langue": "fr",
                    "contenu": "CIS 2010"
                  },
                  {
                    "langue": "en",
                    "contenu": "CIS 2010"
                  }
                ],
                "label": [
                  {
                    "langue": "fr",
                    "contenu": "Enquête communautaire sur l'innovation 2010"
                  },
                  {
                    "langue": "en",
                    "contenu": "Community innovation survey 2010"
                  }
                ],
                "uri": "http://bauhaus/operations/operation/s1197",
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
                  "uri": "http://bauhaus/operations/serie/s1001",
                  "frequence": "U"
                },
                "id": "s1197"
              }
            ]
            """;

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
            """;
}
