class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;

        int reachable = 0;

        for (int i = minJump; i < n; i++) {
            int addIndex = i - minJump;

            if (dp[addIndex]) {
                reachable++;
            }

            int removeIndex = i - maxJump - 1;

            if (removeIndex >= 0 && dp[removeIndex]) {
                reachable--;
            }

            dp[i] = reachable > 0 && s.charAt(i) == '0';
        }

        return dp[n - 1];
    }
}