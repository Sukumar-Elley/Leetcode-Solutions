class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int max = 1;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, dfs(arr, d, i, dp));
        }

        return max;
    }

    private int dfs(int[] arr, int d, int i, int[] dp) {
        if (dp[i] != 0) {
            return dp[i];
        }

        dp[i] = 1; // Count the current index

        // Jump to the left
        for (int j = i - 1; j >= Math.max(0, i - d); j--) {
            if (arr[j] >= arr[i]) {
                break;
            }

            dp[i] = Math.max(dp[i], 1 + dfs(arr, d, j, dp));
        }

        // Jump to the right
        for (int j = i + 1; j <= Math.min(arr.length - 1, i + d); j++) {
            if (arr[j] >= arr[i]) {
                break;
            }

            dp[i] = Math.max(dp[i], 1 + dfs(arr, d, j, dp));
        }

        return dp[i];
    }
}