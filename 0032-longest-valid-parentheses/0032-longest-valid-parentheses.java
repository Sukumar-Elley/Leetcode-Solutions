class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        int maxLen = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    // Pattern: ...()
                    dp[i] = 2 + (i >= 2 ? dp[i - 2] : 0);
                } else if (i - dp[i - 1] - 1 >= 0 &&
                           s.charAt(i - dp[i - 1] - 1) == '(') {
                    // Pattern: ...))
                    dp[i] = dp[i - 1] + 2;

                    int previous = i - dp[i - 1] - 2;
                    if (previous >= 0) {
                        dp[i] += dp[previous];
                    }
                }

                maxLen = Math.max(maxLen, dp[i]);
            }
        }

        return maxLen;
    }
}