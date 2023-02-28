package exercises.dp;

public class DymPro {

    /**
     * You are climbing a staircase. It takes n steps to reach the top.
     * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
     * Each new stair will include all previous stair solutions and:
     * - any exclusively new solutions that do not overlap with previous
     *   in other words - solutions that previously were not possible but with new stair
     *   started to be available
     *  SO f(n) = f(n-1) + new 2 step (the 1 step is ignored as it overlaps with previous)
     *  BUT this NEW 2 step exclusive produces its own set of possible combinations
     *  AND interesting is that this set can be defined as f(n-2) because we need all combinations
     *  previously calculated
     *  2 = 2
     *  * 1. 1 step + 1 step
     *  * 2. 2 steps
     *  3 = 3
     *  * 1. 1 step + 1 step + 1 step
     *  * 2. 2 steps + 1 step
     *  * 3. 1 steps + 2 step NEW
     *  4 = 5
     *  * 1. 1 step + 1 step + 1 step + 1 step
     *  * 2. 2 steps + 1 step + 1 step
     *  * 3. 1 steps + 2 step + 1 step
     *  * 4. 1 step + 1 step + 2 steps NEW // looks like this includes f(n-2)
     *  * 5. 2 steps + 2 steps NEW
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 0 || n == 1) return n;
        if (n == 2) return 2;
        if (n == 3) return 3;
        int[] res = new int[n - 1];
        // for 2 stairs
        res[0] = 2;
        // for 3 stairs
        res[1] = 3;
        for (int i = 2; i < res.length; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res[res.length - 1];
    }
}
