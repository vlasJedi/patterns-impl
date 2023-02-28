package exercises.greedy;

import java.util.HashMap;
import java.util.Map;

public class Greedes {
    /*
    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
     */
    // HINT any next new min does not need to be aware of previous values except the previous max dif
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int min = 0;
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            int dif =  prices[i] - prices[min];
            if (dif < 0) {
                min = i;
                continue;
            }
            if (dif > profit) profit = dif;
        }
        return profit;
    }

    /**
     * Input: s = "abccccdd"
     * Output: 7
     * Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
                continue;
            }
            map.put(chars[i], 1);
        }
        int res = 0;
        int count = 0;
        for (Character key : map.keySet()) {
            count = map.get(key);
            // int pairs = map.get(key) / 2;
            if (((count & 1) == 1) && ((res & 1) == 1)) count--;
            res += count;
        }
        return res;
    }
}
