import java.util.*;
public class Algorithms {
    public static class Edge {
        int v = 0, w = 0;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(u, w));
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

    static int[] par, size;

    public static int findPar(int u) {
        if (par[u] == u)
            return u;
        return par[u] = findPar(par[u]);

        // return par[u] == u ? u : par[u] = find(par[u]);
    }

    public static void union(int p1, int p2) {
        if (size[p1] < size[p2]) {
            par[p1] = p2;
            size[p2] += size[p1];
        } else {
            par[p2] = p1;
            size[p1] += size[p2];
        }
    }

    // {{u1,v1,w1},{u2,v2,w2}...}
    public static void unionFind(int[][] edges, ArrayList<Edge>[] graph, int N) {
        par = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            par[i] = i;
            size[i] = 1;
        }

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int p1 = findPar(u);
            int p2 = findPar(v);

            if (p1 != p2) {
                union(p1, p2);
                addEdge(graph, u, v, w);
            }
        }
    }
    public static void kruskalAlgo(int[][] edges){
        Arrays.sort(edges, (a,b) -> {
            return a[2] - b[2];
        });
        int N = edges.length;
        ArrayList<Edge>[] graph = new ArrayList[N];
        unionFind(edges, graph,N);
    }
    public static void articulationPointAndBridges(ArrayList<Edge>[] graph) {
        int N = graph.length;
        low = new int[N];
        disc = new int[N];
        articulation = new boolean[N];
        vis = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!vis[i]) {
                dfs(graph, i, -1);
            }
        }
    }

    public static class pair {
        int vtx, par, w, wsf;

        // for dijikstra1
        pair(int vtx, int par, int w, int wsf) {
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }

        // for dijikstra2
        pair(int vtx, int wsf) {
            this.vtx = vtx;
            this.wsf = wsf;
        }
    }

    public static void dijikstra_01(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        ArrayList<Edge>[] ngraph = new ArrayList[N];
        for (int i = 0; i < N; i++)
            ngraph[i] = new ArrayList<>();

        boolean[] vis = new boolean[N];
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf; // for prims : return a.w - b.w;
        });

        int[] dis = new int[N];
        int[] par = new int[N];

        pq.add(new pair(src, -1, 0, 0));
        while (pq.size() != 0) {
            pair p = pq.remove();

            if (vis[p.vtx])
                continue;

            if (p.par != -1)
                addEdge(ngraph, p.vtx, p.par, p.w);

            vis[p.vtx] = true;

            dis[p.vtx] = p.wsf;
            par[p.vtx] = p.par;

            for (Edge e : graph[p.vtx]) {
                if (!vis[e.v])
                    pq.add(new pair(e.v, p.vtx, e.w, p.wsf + e.w));
            }
        }
    }

    public static void dijikstra_02(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<pair> pq = new PriorityQueue<>((a, b) -> {
            return a.wsf - b.wsf;
        });

        int[] dis = new int[N];
        int[] par = new int[N];
        Arrays.fill(dis, (int) 1e9);
        Arrays.fill(par, -1);

        pq.add(new pair(src, 0));
        while (pq.size() != 0) {
            pair p = pq.remove();

            if (p.wsf >= dis[p.vtx])
                continue;

            for (Edge e : graph[p.vtx]) {
                if (p.wsf + e.w < dis[e.v]) {
                    dis[e.v] = p.wsf + e.w;
                    par[e.v] = p.vtx;
                    pq.add(new pair(e.v, p.wsf + e.w));
                }
            }
        }
    }

    public static class primsPair {
        int vtx, w;

        primsPair(int vtx, int w) {
            this.vtx = vtx;
            this.w = w;
        }
    }

    public static void prims(ArrayList<Edge>[] graph, int src) {
        int N = graph.length;
        PriorityQueue<primsPair> pq = new PriorityQueue<>((a, b) -> {
            return a.w - b.w;
        });

        int[] dis = new int[N];
        Arrays.fill(dis, (int) 1e9);

        pq.add(new primsPair(src, 0));
        while (pq.size() != 0) {
            primsPair p = pq.remove();

            if (vis[p.vtx])
                continue;

            vis[p.vtx] = true;
            for (Edge e : graph[p.vtx]) {
                if (e.w < dis[e.v]) {
                    dis[e.v] = e.w;
                    pq.add(new primsPair(e.v, e.w));
                }
            }
        }
    }
    public static class pair{
        int vtx, par, w, wsf;
        pair(int vtx, int par, int w, int wsf){
            this.vtx = vtx;
            this.par = par;
            this.w = w;
            this.wsf = wsf;
        }
    }
    public static void dijikstra1(ArrayList<Edge>[], int src){
        int N = graph.length;
        
    }
    public static void main(String[] args){
        // Scanner sc = new Scanner(System.in);
    }
} 
