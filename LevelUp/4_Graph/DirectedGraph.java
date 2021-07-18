import java.util.ArrayList;
import java.util.LinkedList;

public class DirectedGraph {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
    }

    // O(2E)
    public static void display(ArrayList<Edge>[] graph) {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            System.out.print(i + " -> ");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + ", " + e.w + ") ");
            }
            System.out.println();
        }
    }

    public static void dfs_topo(ArrayList<Edge>[] graph, int src, boolean[] vis, ArrayList<Integer> ans) {
        vis[src] = true;
        for (Edge e : graph[src]) {
            if (!vis[e.v])
                dfs_topo(graph, e.v, vis, ans);
        }

        ans.add(src);
    }

    public static void topologicalOrder(ArrayList<Edge>[] graph) {
        ArrayList<Integer> ans = new ArrayList<>();

        int N = graph.length;
        boolean[] vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i])
                dfs_topo(graph, i, vis, ans);
        }
    }

    public static ArrayList<Integer> kahnsAlgo(ArrayList<Edge>[] graph) {
        int N = graph.length;
        LinkedList<Integer> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        int[] indegree = new int[N];

        // O(E)
        for (int i = 0; i < N; i++) {
            for (Edge e : graph[i]) {
                indegree[e.v]++;
            }
        }

        // O(V)
        for (int i = 0; i < N; i++)
            if (indegree[i] == 0)
                que.addLast(i);

        // O(E + V)
        while (que.size() != 0) {
            int rvtx = que.removeFirst();
            ans.add(rvtx);

            for (Edge e : graph[rvtx]) {
                if (--indegree[e.v] == 0)
                    que.addLast(e.v);
            }
        }

        if (ans.size() != N)
            ans.clear();

        return ans;
    }

    //329
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        
        int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        int[][] indegree = new int[n][m];
        LinkedList<Integer> que = new LinkedList<>();
        
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                for(int d=0;d<4;d++){
                    int r = i + dir[d][0];
                    int c = j + dir[d][1];
                    
                    if(r >= 0 && c>=0 && r < n && c < m && matrix[r][c] < matrix[i][j])
                        indegree[i][j]++;
                }
                if(indegree[i][j] == 0)
                   que.addLast(i*m+j);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        
        int level=0;
        
        while(que.size()!=0){
            int size=que.size();
            while(size-->0){
                int ridx=que.removeFirst();
                int x=ridx/m;
                int y=ridx%m;
                
                for(int d=0;d<4;d++){
                    int r=x+dir[d][0];
                    int c=y+dir[d][1];
                    
                    if(r>=0 && c>=0 && r<n && c<m && matrix[r][c]>matrix[x][y]){
                        if(--indegree[r][c]==0){
                            que.addLast(r*m+c);
                        }
                    }
                }
            }
            level++;
        }
        return level;
    }
    public static void constructGraph() {
        int N = 7;
        ArrayList<Edge>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);
        addEdge(graph, 3, 0, 10);
        addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 5, 6, 3);
        addEdge(graph, 6, 4, 8);

        display(graph);
    }

    public static void main(String[] args) {
        constructGraph();
    }

}