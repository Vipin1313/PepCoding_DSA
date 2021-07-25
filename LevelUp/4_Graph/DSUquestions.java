public class DSUquestions {
    // 695
    public static int[] par, size;

    public int findPar(int u) {
        return par[u] == u ? u : (par[u] = findPar(par[u]));
    }

    public int maxAreaOfIsland(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if (n == 0 || m == 0)
            return 0;

        par = new int[n * m];
        size = new int[n * m];

        int count = 0, maxSize = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                par[i * m + j] = i * m + j;
                size[i * m + j] = 1;
            }
        }

        int[][] dir = { { 1, 0 }, { 0, 1 } };
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int p1 = findPar(i * m + j);
                    for (int d = 0; d < 2; d++) {
                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                            int p2 = findPar(r * m + c);
                            if (p1 != p2) {
                                par[p2] = p1;
                                size[p1] += size[p2];
                            }
                        }
                    }

                    maxSize = Math.max(maxSize, size[p1]);
                }
            }
        }

        return maxSize;
    }

    // 1061
    public String smallestEquivalentString(String A, String B, String S) {
        par = new int[26];
        for (int i = 0; i < par.length; i++)
            par[i] = i;
        for (int i = 0; i < A.length(); i++) {
            int p1 = A.charAt(i);
            int p2 = B.charAt(i);
            par[p1] = Math.max(p1, p2);
            par[p2] = Math.max(p1, p2);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char c = (char) (findPar(S.charAt(i) - 'a' + 'a'));
            sb.append(c);
        }

        return sb.toString();
    }

    // 990
    public boolean equationsPossible(String[] equations) {
        int n = equations.length;
        par = new int[26];
        size = new int[26];
        for (int i = 0; i < 26; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int i = 0; i < n; i++) {
            String str = equations[i];
            int val1 = str.charAt(0) - 'a';
            int val2 = str.charAt(3) - 'a';
            if (str.substring(1, 3).equals("==")) {
                unionfind(val1, val2);
            }

        }
        for (int i = 0; i < n; i++) {
            String str = equations[i];
            int val1 = str.charAt(0) - 'a';
            int val2 = str.charAt(3) - 'a';
            if (str.substring(1, 3).equals("!=")) {
                int x = findPar(val1);
                int y = findPar(val2);
                if (x == y) {
                    return false;
                }
            }
        }
        return true;

    }

    public void unionfind(int x, int y) {
        int i = findPar(x);
        int j = findPar(y);
        if (i != j) {
            if (size[i] > size[j]) {
                par[j] = i;
            } else if (size[j] > size[i]) {
                par[i] = j;
            } else {
                par[i] = j;
                size[j]++;
            }
        }
    }

    // 839
    public int findParent(int u) {
        return par[u] == u ? u : (par[u] = findParent(par[u]));
    }

    public static boolean isSimilar(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i) && ++count > 2)
                return false;
        }
        return true;
    }

    public int numSimilarGroups(String[] strs) {
        int n = strs.length, grp = n;
        par = new int[n];
        for (int i = 0; i < par.length; i++)
            par[i] = i;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    int p1 = findParent(i);
                    int p2 = findParent(j);

                    if (p1 != p2) {
                        par[p1] = p2;
                        grp--;
                    }
                }
            }
        }
        return grp;
    }
    
    public List<Integer> numIslands2(int n, int m, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

        par = new int[n * m];
        Arrays.fill(par, -1);

        int count = 0;
        for (int[] p : positions) {
            int i = p[0], j = p[1];
            if (par[i * m + j] == -1) {
                count++;
                par[i * m + j] = i * m + j;

                int p1 = findPar(i * m + j);
                for (int d = 0; d < dir.length; d++) {
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];

                    if (r >= 0 && c >= 0 && r < n && c < m && par[r * m + c] != -1) {
                        int p2 = findPar(r * m + c);
                        if (p1 != p2) {
                            count--;
                            par[p2] = p1;
                        }
                    }
                }
            }
            ans.add(count);
        }

        return ans;
    }
    // 684
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        par = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            par[i] = i;
        }

        int[] ans = null;
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;

            } else {
                ans = e;
                // break;
            }
        }

        return ans;
    }

    // 1168
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        ArrayList<int[]> allPipes = new ArrayList<>();
        for (int[] a : pipes)
            allPipes.add(a);
        for (int i = 0; i < wells.length; i++)
            allPipes.add(new int[] { 0, i + 1, wells[i] });

        Collections.sort(allPipes, (a, b) -> {
            return a[2] - b[2];
        });

        par = new int[n + 1];
        int ans = 0;

        for (int i = 0; i <= n; i++) {
            par[i] = i;
        }

        for (int[] e : allPipes) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;
                ans += w;
            }
        }

        return ans;
    }

    public int mrPresident(int[][] edges, int N, int K) {
        par = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            par[i] = i;
        }

        Arrays.sort(edges, (a, b) -> {
            return a[2] - b[2];
        });

        ArrayList<Integer> roads = new ArrayList<>();

        int components = N, mcost = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u), p2 = findPar(v);
            if (p1 != p2) {
                par[p1] = p2;
                components--;
                mcost += w;
                roads.add(w);
            }
        }

        if (components > 1)
            return -1;

        int superroad = 0;
        for (int i = roads.size() - 1; i >= 0; i--) {
            if (mcost <= K)
                break;
            mcost = mcost - roads.get(i) + 1;
            superroad++;
        }

        return mcost <= K ? superroad : -1;
    }

}
