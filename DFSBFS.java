//DFS,BFS
import java.util.*;
import java.lang.*;
import java.io.*;
public class DFSBFS {
    static Kattio io = new Kattio();
    static ArrayList<Integer> adj[];
    static boolean visited[];
    static int dist[];
    /*
    SAMPLE CASE
    5 6
    0 1
    0 3
    0 2
    1 3
    2 3
    3 4
     */
    public static void main(String[] args){
        // do BFS, see where the furthest node is;
        //subsequently, do BFS again to find furthest node from that point
        //multiply by 3 to get answer

        int n = io.nextInt();
        int m = io.nextInt();
        adj = new ArrayList[n];
        dist = new int[n];
        visited = new boolean[n];
        for(int i =0;i<adj.length;i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0;i<m;i++){
            int a= io.nextInt();
            int b = io.nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }
        bfs(0);
        for(int num: dist){
            io.println(num + " " );
        }
        io.println("\n\n\n\n");
        dfs(0);

        //run BFS

        io.close();
    }

    static void bfs(int k){
        //b
        Arrays.fill(dist, -1); // fill distance array with -1's
        Queue<Integer> q = new LinkedList<Integer>();
        dist[k] = 0;
        q.add(k);
        while(!q.isEmpty()){
            int v = q.poll();
            io.println("visiting" + v);
            for(int e : adj[v]){
                //if unmarked
                if(dist[e] == -1){
                    //marking e
                    io.println("going through unvisited neighbors of " +v + " and found " + e);
                    dist[e] = dist[v] + 1;
                    q.add(e);
                    io.println(q);
                }
            }
        }
    }
    public static void dfs(int node)
    {
        io.println("visiting node " + node);
        visited[node] = true;
        //going through all neighbors of node

        for (int u: adj[node]) {
            //if unvisited, run dfs
            if (!visited[u])
                dfs(u);
        }
    }

}
class Kattio extends PrintWriter {
    private BufferedReader r;
    private StringTokenizer st = new StringTokenizer("");
    private String token;

    // standard input
    public Kattio() { this(System.in,System.out); }
    public Kattio(InputStream i, OutputStream o) {
        super(o);
        r = new BufferedReader(new InputStreamReader(i));
    }
    // USACO-style file input
    public Kattio(String problemName) throws IOException {
        super(new FileWriter(problemName+".out"));
        r = new BufferedReader(new FileReader(problemName+".in"));
    }

    private String peek() {
        if (token == null)
            try {
                while (!st.hasMoreTokens()) {
                    String line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }
    public boolean hasMoreTokens() { return peek() != null; }
    public String next() {
        String ans = peek();
        token = null;
        return ans;
    }

    public int nextInt() { return Integer.parseInt(next()); }
    public double nextDouble() { return Double.parseDouble(next()); }
    public long nextLong() { return Long.parseLong(next()); }
}