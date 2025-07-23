package exercises.strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringEx {
    // isomorphic strings
    // strings that have 1 char to 1 char relationship
    // foo - gaa YES
    // foo - gas NO
    // fas - gaa NO
    public boolean isIsomorphic(String s, String t) {
        Map<String, String> maps = new HashMap<>();
        Map<String, String> reverseMap = new HashMap<>();

        String[] first = s.split("");
        String[] second = t.split("");
        for (int i = 0; i < first.length; i++) {
            String iStr = first[i];
            String secStr = second[i];
            boolean contains = maps.containsKey(iStr);
            if (!contains) {
                boolean hasReverse = reverseMap.containsKey(secStr);
                if (hasReverse) {
                    return false;
                }
                maps.put(iStr, secStr);
                reverseMap.put(secStr, iStr);
                continue;
            }
            String mappedV = maps.get(iStr);
            if (mappedV.equals(second[i])) {
                continue;
            }
            return false;
        }
        return true;
    }

    // s is a subsequence for t
    // if some t chars can be deleted and no moving of chars
    // so s = t
    // s = "abc", t = "ahbgdc" TRUE
    // s = "axc", t = "ahbgdc" FALSE
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;

        char[] subSeq = s.toCharArray();
        char[] trgStr = t.toCharArray();

        int lastFoundIndex = -1;
        for(int i = 0; i < trgStr.length; i++) {
            if (lastFoundIndex == (subSeq.length - 1)) {
                return true;
            }
            if (trgStr[i] == subSeq[lastFoundIndex + 1]) {
                lastFoundIndex++;
            }
        }
        return lastFoundIndex == (subSeq.length - 1);
    }


    public void cosineSimilarity(String s1, String s2) {
        String[] words1 = s1.split("\\s");
        String[] words2 = s2.split("\\s");
        // build vector size
        Set<String> vocal = Stream.of(words1, words2).flatMap(Stream::of).collect(Collectors.toSet());
        Map<String, Integer> dictOfWordCounts1 = vocal.stream().collect(Collectors.toMap((word) -> word, (word) -> 0));
        Arrays.stream(words1).forEach(word -> {
            dictOfWordCounts1.put(word, dictOfWordCounts1.get(word) + 1);
        });
        Map<String, Integer> dictOfWordCounts2 = vocal.stream().collect(Collectors.toMap((word) -> word, (word) -> 0));
        Arrays.stream(words2).forEach(word -> {
            dictOfWordCounts2.put(word, dictOfWordCounts2.get(word) + 1);
        });
        System.out.printf("Vocabular set from two string: %s%n", vocal);
        Double similarity = dotProduct(dictOfWordCounts1, dictOfWordCounts2, vocal)
                / (vectorLength(dictOfWordCounts1) * vectorLength(dictOfWordCounts2));
        System.out.printf("Cosine similarity is: %s%n", similarity);
    }

    private Integer dotProduct(Map<String, Integer> dict1, Map<String, Integer> dict2, Set<String> vocal) {
        return vocal.stream().map(word -> dict1.get(word) * dict2.get(word)).reduce(0, Integer::sum);
    }

    private Double vectorLength(Map<String, Integer> dict1) {
        return Math.sqrt(dict1.values().stream().map(count -> count * count).reduce(0, Integer::sum));
    }
}
