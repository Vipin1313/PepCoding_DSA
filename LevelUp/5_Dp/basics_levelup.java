public class basics_levelup {
    // int[][] dir = {{0,1}}
    public static int fibo_memo(int n, int[] dp){
        if(n <= 1)
          return dp[n] = n;

        if(dp[n] != 0)
          return dp[n];
        
        int ans = fibo_memo(n-1,dp) + fibo_memo(n-2,dp);
        return dp[n] = ans;
    }

    public static int fibo_tabu(int N, int[] dp){
        for(int n = 0; n <= N; n++){
            if(n <= 1){
                dp[n] = n;
                continue;
            }
            int ans = dp[n-1] + dp[n-2];
            dp[n] = ans;
        }
        return dp[N];
    }
    public static int fibo_optimised(int n){
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return a;
    }
    public static int mazePath_memo(int sr, int sc, int er, int ec,int[][] dir, int[][] dp){
        if(sr == er && sc == ec){
            return dp[sr][sc] = 1;
        }
        if(dp[sr][sc] != 0)
           return dp[sr][sc];
        
        int count = 0;
        for(int d[] : dir){
            int r = sr + d[0];
            int c = sc + d[1];
            if(r >= 0 && c >= 0 && r <= er && c <= ec)
               count +=mazePath_memo(r, c, er, ec, dir, dp);
        }   
        return dp[sr][sc] = count;
    }
    public static int mazePath_tabu(int SR, int SC, int er, int ec,int[][] dir, int[][] dp){
        for(int sr = er; sr >= SR; sr--){
            for(int sc = ec; sc >= SC; sc--){
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for(int d[] : dir){
                    int r = sr + d[0];
                    int c = sc + d[1];
                    if(r <= er && c <= ec)
                       count += dp[r][c];
                }  
            }
        }
         
        return dp[SR][SC];
    }
    public static int mazePathJump_tabu(int SR, int SC, int er, int ec,int[][] dir, int[][] dp){
        for(int sr = er; sr >= SR; sr--){
            for(int sc = ec; sc >= SC; sc--){
                if(sr == er && sc == ec){
                    dp[sr][sc] = 1;
                    continue;
                }
                int count = 0;
                for(int d[] : dir){
                    int r = sr + d[0];
                    int c = sc + d[1];
                    while(r <= er && c <= ec)
                       count += dp[r][c];
                       r += d[0];
                       c += d[1];
                }  
                dp[sr][sc] = count;
            }
        }
         
        return dp[SR][SC];
    }

    // Gold Mine.
    public static int goldMine_memo_(int sr, int sc, int[][] mat, int[][] dir, int[][] dp) {
        if (sc == mat[0].length - 1) {
            return dp[sr][sc] = mat[sr][sc];
        }

        if (dp[sr][sc] != -1)
            return dp[sr][sc];

        int maxGold = 0;
        for (int[] d : dir) {
            int r = sr + d[0];
            int c = sc + d[1];

            if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
                maxGold = Math.max(maxGold, goldMine_memo_(r, c, mat, dir, dp) + mat[sr][sc]);
        }

        return dp[sr][sc] = maxGold;
    }

    public static int goldMine_memo(int n, int m, int[][] mat) {
        int[][] dir = { { -1, 1 }, { 1, 1 }, { 0, 1 } };
        int[][] dp = new int[n][m];

        int maxGold = 0;
        for (int r = 0; r < n; r++) {
            maxGold = Math.max(maxGold, goldMine_memo_(r, 0, mat, dir, dp));
        }

        return maxGold;
    }

    public static int goldMine_Tabu(int SR, int SC, int[][] mat, int[][] dir, int[][] dp) {
        for (int sc = mat[0].length - 1; sc >= SC; sc--) {
            for (int sr = mat.length - 1; sr >= SR; sr--) {
                if (sc == mat[0].length - 1) {
                    dp[sr][sc] = mat[sr][sc];
                    continue;
                }

                int maxGold = 0;
                for (int[] d : dir) {
                    int r = sr + d[0];
                    int c = sc + d[1];

                    if (r >= 0 && c >= 0 && r < mat.length && c < mat[0].length)
                        maxGold = Math.max(maxGold, dp[r][c] + mat[sr][sc]);
                }

                dp[sr][sc] = maxGold;
            }
        }

        int maxGold = 0;
        for (int r = 0; r < mat.length; r++) {
            maxGold = Math.max(maxGold, dp[r][0]);
        }

        return maxGold;
    }

    // 70
    public int climbStairs(int n) {
        int a = 1, b = 1;
        for (int i = 0; i < n; i++) {
            int sum = a + b;
            a = b;
            b = sum;
        }

        return a;
    }

    // 746

    public int minCost_memo(int n, int[] cost, int[] dp) {
        if (n <= 1) {
            return dp[n] = cost[n];
        }

        if (dp[n] != 0)
            return dp[n];

        int minCost = Math.min(minCost_memo(n - 1, cost, dp), minCost_memo(n - 2, cost, dp));

        return dp[n] = minCost + (n == cost.length ? 0 : cost[n]);
    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];

        return minCost_memo(n, cost, dp);
    }

    // board Path
    public static int boardPath_memo(int n, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
            count += boardPath_memo(n - dice, dp);
        }

        return dp[n] = count;
    }

    public static int boardPath_dp(int N, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 1; dice <= 6 && n - dice >= 0; dice++) {
                count += dp[n - dice];// boardPath_memo(n - dice, dp);
            }

            dp[n] = count;
        }
        return dp[N];
    }

    public static int boardPath_Opti(int n) {
        LinkedList<Integer> ll = new LinkedList<>();

        ll.addLast(1);
        ll.addLast(1);
        for (int i = 2; i <= n; i++) {
            if (ll.size() <= 6)
                ll.addLast(ll.getLast() * 2);
            else
                ll.addLast(ll.getLast() * 2 - ll.removeFirst());
        }

        return ll.getLast();
    }

    public static int boardPath_memo02(int n,int[] arr, int[] dp) {
        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != 0)
            return dp[n];

        int count = 0;
        for (int dice = 0; dice < arr.length && n - arr[dice] >= 0; dice++) {
            count += boardPath_memo02(n - arr[dice], arr, dp);
        }

        return dp[n] = count;
    }

    public static int boardPath_tabu02(int N, int[] arr, int[] dp) {
        for (int n = 0; n <= N; n++) {
            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int count = 0;
            for (int dice = 0; dice < arr.length && n - arr[dice] >= 0; dice++) {
                count += dp[n - arr[dice]];// boardPath_memo(n - dice, dp);
            }

            dp[n] = count;
        }
        return dp[N];
    }
    public static void boardPath() {
        int n = 20;
        int[] dp = new int[n + 1];
        // System.out.println(boardPath_memo(n, dp));
        System.out.println(boardPath_dp(n, dp));
        System.out.println(boardPath_Opti(n));

        display(dp);
    }
}
