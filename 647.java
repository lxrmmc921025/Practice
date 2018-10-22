class Solution {
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        boolean[][] pali = new boolean[n][n];
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    pali[i][j] = true;
                    dp[i][j] = 1;
                } else if (j == i + 1) {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j];
                    if (s.charAt(i) == s.charAt(j)) {
                        pali[i][j] = true;
                        dp[i][j] = dp[i][j] + 1;
                    } 
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                    if (s.charAt(i) == s.charAt(j) && pali[i + 1][j - 1]) {
                        pali[i][j] = true;
                       dp[i][j] = dp[i][j] + 1;
                    } 
                }
            }
        }
        return dp[0][n - 1];
    }
}