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
    static Kattio io = new Kattio();
    /*static Kattio io;
   static {
       try {
           io = new Kattio("angry");
       } catch(IOException e) {}
   }

     */


    static ArrayList<Integer> adj[];
    static boolean visited[];
    static int dist[];
    /*
    SAMPLE CASE
6 3 2
1 1 10 14 4 3

     */
    static int N;
    static int numBus;
    static int cap;
    static int[] cow;
    public static void main(String[] args){
        N=io.nextInt();
        numBus= io.nextInt();
        cap= io.nextInt();
        cow = new int[N];
        for(int i =0;i<N;i++){
            cow[i] = io.nextInt();
        }
        Arrays.sort(cow);
        int min = 0;
        int max = (int)1e9;
        while(min != max) {
            int mid = (min+max)/2;
            if(possible( mid)) {
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
    public static boolean possible(int minWait) {
        int first = cow[0]; int used = 1; int curCap = 0;
        for (int i = 0; i < N; i++) {
            if(cow[i]-first>minWait || curCap >= cap){
                used++;
                curCap=0;
                first = cow[i];
            }
            curCap++;
        }
        return used <= numBus;
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