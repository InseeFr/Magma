package fr.insee.rmes.persistence.rdfQueries;

import fr.insee.rmes.utils.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QueryUtilsTest {

    @Test
    void shouldReturnCorrectEmptyGroupConcat() {
        List<String> res = List.of("[{\"altLabel\":\"\"}]","example");
        boolean isCorrectEmptyGroupConcatFirst = "[]".equals(QueryUtils.correctEmptyGroupConcat(res.getFirst()));
        boolean isCorrectEmptyGroupConcatLast = "example".equals(QueryUtils.correctEmptyGroupConcat(res.getLast()));
        assertTrue(isCorrectEmptyGroupConcatFirst && isCorrectEmptyGroupConcatLast);
    }

    @Test
    void shouldReturnCorrectTransformRdfTypeInString() {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObjectFirst = new JSONObject();
        jsonObjectFirst.put("FirstAttribute","FirstAttributeExample").put("FirstCreator","FirstCreatorExample");

        JSONObject jsonObjectLast = new JSONObject();
        jsonObjectLast.put("LastAttribute","LastAttributeExample").put(Constants.TYPE_OF_OBJECT,"https://creatorExample/myCreator");

        jsonArray.put(jsonObjectFirst).put(jsonObjectLast);

        JSONArray results = QueryUtils.transformRdfTypeInString(jsonArray);

        assertEquals("[{\"FirstAttribute\":\"FirstAttributeExample\",\"FirstCreator\":\"FirstCreatorExample\"},{\"LastAttribute\":\"LastAttributeExample\",\"type\":\"undefined\"}]",results.toString());
    }

    @Test
    void shouldCheckNumberOfPrefixesIsNotZero() {
        int numberOfPrefixes = StringUtils.countOccurrencesOf(QueryUtils.PREFIXES, "PREFIX");
        assertTrue(numberOfPrefixes>0);
    }
}