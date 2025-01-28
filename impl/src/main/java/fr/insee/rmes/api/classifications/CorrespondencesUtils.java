package fr.insee.rmes.api.classifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import fr.insee.rmes.modeles.classification.correspondence.Association;
import fr.insee.rmes.modeles.classification.correspondence.Associations;
import fr.insee.rmes.modeles.classification.correspondence.PosteCorrespondence;
import fr.insee.rmes.modeles.classification.correspondence.RawCorrespondence;

public class CorrespondencesUtils {

    private CorrespondencesUtils() {
    	// No-args constructor needed for JAXB
    }

    /**
     * get in shape rawlist in tree map
     * 1 source (map id) -> many target
     */
    /* when id correspondence */
    public static Associations getCorrespondenceByCorrespondenceId(List<RawCorrespondence> rawItemsList) {

        Map<PosteCorrespondence, List<PosteCorrespondence>> mapSourceTargetItems = getTreeMapTargetItemsBySourceByCorrespondenceId(rawItemsList);
        return organizeItemTreeMap(mapSourceTargetItems);
    }

    private static Associations organizeItemTreeMap(Map<PosteCorrespondence, List<PosteCorrespondence>> mapSourceTargetItems) {
        Associations associations = new Associations();
        mapSourceTargetItems.forEach((k, v) -> {

            Association assoc = new Association(k, v);
            associations.getAssociations().add(assoc);

        });

        return associations;
    }

    /**
     * This method transforms sparql query "table" result
     * for mapping 1 source item from source classification to to many many target item in target classification
     * @param idCorrespondence
     * @param rawItemsList
     * @return
     */
    public static Map<PosteCorrespondence, List<PosteCorrespondence>> getTreeMapTargetItemsBySourceByCorrespondenceId(
        List<RawCorrespondence> rawItemsList) {
        /* TreeMap for ordering map keys */
        Map<PosteCorrespondence, List<PosteCorrespondence>> groupedListItems = new TreeMap<>();
        for (RawCorrespondence curRawCorrespondence : rawItemsList) {
            PosteCorrespondence posteSource = newPoste1(curRawCorrespondence);
            if ( ! groupedListItems.containsKey(posteSource)) { // initialize map if new item source
                groupedListItems.put(posteSource, new ArrayList<>());
            }
            // add targetItem
            PosteCorrespondence targetPoste = newPoste2(curRawCorrespondence);
            groupedListItems.get(posteSource).add(targetPoste);
        }
        return groupedListItems;
    }

    /* when id1 + id2 classifications */
    public static Associations getCorrespondenceByclassificationIds(
        String codeClassification,
        List<RawCorrespondence> rawItemsList) {

        Map<PosteCorrespondence, List<PosteCorrespondence>> mapSourceTargetItems =
            getTreeMapTargetItemsBySourceByClassificationsIds(codeClassification, rawItemsList);
        return organizeItemTreeMap(mapSourceTargetItems);
    }

    /**
     * for handling asymetrical correspondencies in RDF data
     */
    public static Map<PosteCorrespondence, List<PosteCorrespondence>> getTreeMapTargetItemsBySourceByClassificationsIds(
        String codeClassificationSource,
        List<RawCorrespondence> rawItemsList) {

        /* TreeMap for ordering map keys */
        Map<PosteCorrespondence, List<PosteCorrespondence>> groupedListItems = new TreeMap<>();

        /*
         * Classification correspondences are not symetrical in database => answering question : what is source / target
         * must in raw correspondances ?
         */
        /* if false => first fields of csv result are for source classification item */
        /* if true => second part of fields */
        boolean poste1IsSource = isPoste1Source(codeClassificationSource, rawItemsList);

        for (RawCorrespondence curRawCorrespondence : rawItemsList) {
            PosteCorrespondence posteSource = mapRawObjectToItemCorrespondence(curRawCorrespondence, poste1IsSource);
            if ( ! groupedListItems.containsKey(posteSource)) {// initialize map
                groupedListItems.put(posteSource, new ArrayList<>());
            }
            // add targetItem
            groupedListItems
                .get(posteSource)
                .add(mapRawObjectToItemCorrespondence(curRawCorrespondence, ! poste1IsSource));
        }

        return groupedListItems;

    }

    /**
     * Correspondance beetween 2 classifications is not symetrical.
     * the order for giving clasification must change results mappings
     * this method verify is swapping source <-> target items i necessary
     * to get the right correspondence
     * @param codeClassificationSource
     * @param rawItemsList
     * @return
     */
    private static Boolean isPoste1Source(String codeClassificationSource, List<RawCorrespondence> rawItemsList) {
        // using the first correspondence ( rawItemsList.get(0) )
        // => which csv result, first or second uri field (uriPoste1/2) does contain classification source id ?
        String uriPoste1FromRaw = rawItemsList.get(0).getUriPoste1();
        String classifSource = "/codes/" + codeClassificationSource + "/";
        return ! uriPoste1FromRaw.contains(classifSource);
    }

    private static PosteCorrespondence mapRawObjectToItemCorrespondence(RawCorrespondence corresp, boolean poste1IsSource) {
        return (poste1IsSource ? newPoste2(corresp) : newPoste1(corresp));
    }

    private static PosteCorrespondence newPoste1(RawCorrespondence corresp) {
        return new PosteCorrespondence(
            corresp.getCodePoste1(),
            corresp.getUriPoste1(),
            corresp.getIntituleFrPoste1(),
            corresp.getIntituleEnPoste1());
    }

    private static PosteCorrespondence newPoste2(RawCorrespondence corresp) {
        return new PosteCorrespondence(
            corresp.getCodePoste2(),
            corresp.getUriPoste2(),
            corresp.getIntituleFrPoste2(),
            corresp.getIntituleEnPoste2());
    }

}
