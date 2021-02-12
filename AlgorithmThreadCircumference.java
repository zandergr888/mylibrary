//DFS,BFS
import java.util.*;
import java.lang.*;
import java.io.*;
public class AlgorithmThreadCircumference {
    static class times implements Comparable<times>{
        int a, b;
        public times(int _a, int _b){
            a = _a;
            b = _b;
        }
        public int compareTo(times a){
            return this.a - a.a;
        }
    }
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

        adj = new ArrayList[n];
        dist = new int[n];
        visited = new boolean[n];
        for(int i =0;i<adj.length;i++){
            adj[i] = new ArrayList<>();
        }

        for(int i = 0;i<n-1;i++){
            int a= io.nextInt();
            int b = io.nextInt();
            adj[a-1].add(b -1 );
            adj[b-1].add(a - 1);
        }

        bfs(0);
        int ans = 0;
        int[] distCopy = new int[dist.length];
        for(int i  =0;i<distCopy.length;i++){
            distCopy[i] = dist[i];
        }
        Arrays.sort(distCopy);
        int ind = 0;
        int value = 0;
        //finding max value in dist
        for(int i =0;i<dist.length;i++){
            if(dist[i] > value){
                value = dist[i];
                ind = i;
            }
        }
        bfs(ind);

        for(int i =0;i<dist.length;i++){
            ans = Math.max(dist[i],ans);
        }

        io.println(ans * 3);



        io.println("");


        //run BFS

        io.close();
    }

    static void bfs(int k){
        //bfs
        Arrays.fill(dist, -1); // fill distance array with -1's
        Queue<Integer> q = new LinkedList<Integer>();
        dist[k] = 0;
        q.add(k);
        while(!q.isEmpty()){
            int v = q.poll();
            for(int e : adj[v]){
                //if unmarked
                if(dist[e] == -1){
                    //marking e
                    dist[e] = dist[v] + 1;
                    q.add(e);

                }
            }
        }
    }
    public static void dfs(int node)
    {
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