package exercises.strings;

import java.util.HashMap;
import java.util.Map;

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
}
