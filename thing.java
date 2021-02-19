//subsequences summing to seven
import java.util.*;
import java.lang.*;
import java.io.*;
public class thing {
    static class mountain implements Comparable<mountain>{
        int a, b;
        public mountain(int _a, int _b){
            a = _a;
            b = _b;
        }
        public int compareTo(mountain a){
            if(a.a == this.a){
                return Integer.compare(a.b, this.b);
            }
            return Integer.compare(a.a, this.a);
        }
    }
    //static Kattio io = new Kattio();
    static Kattio io;
   static {
       try {
           io = new Kattio("angry");
       } catch(IOException e) {}
   }


    static ArrayList<Integer> adj[];
    static boolean visited[];
    static int dist[];
    /*
    SAMPLE CASE
5 8
4
7
8
6
4

     */
    static int k;
    static int[] locations;
    public static void main(String[] args){
        int n = io.nextInt();
        k = io.nextInt();
        locations = new int[n];
        for(int i =0;i<n;i++){
            locations[i] = io.nextInt();
        }

        int min = 1;
        int max = 50000;
        //max = n/k;
        Arrays.sort(locations);
        while(min != max) {
            int mid = (min+max)/2;
            if(possible(locations, mid, k,dist)) {
                max = mid;
            }
            else {
                min = mid+1;
            }
        }
        io.println(min);


        io.close();
    }
    //given a size of a subarray, is it possible to use this size
    public static boolean possible(int[] l, int r, int k,int[] dist) {

        int used = 0;
        int last = 0;
        while(last < l.length) {
            used++;
            int curr = last+1;
            while(curr < l.length && locations[curr] - locations[last] <= 2*r) {
                curr++;
            }
            last = curr;
        }
        if(used <= k) {
            return true;
        }
        return false;
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