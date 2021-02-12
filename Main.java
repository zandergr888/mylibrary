
import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
public class Main{
    static class Pair implements Comparable<Pair>{
        int a, b;
        public Pair(int _a, int _b){
            a = _a;
            b = _b;
        }
        public int compareTo(Pair a){
            return this.a - a.a;
        }
    }
    /*static Kattio io;
    static {
        try {
            io = new Kattio("word");
        } catch(IOException e) {}
    }*/
    static Kattio io = new Kattio();
    //static boolean[] visited;
    static ArrayList<Integer> adj[];
    static TreeMap<Integer, Integer> multiset = new TreeMap<Integer, Integer>();
    public static boolean[][] v;
    public static char[][] g;
    public static final int xd[] = {0,1,0,-1}, yd[] = {1,0,-1,0};
    public static int N, M;
    public static void main(String[]args) throws IOException {
        N = io.nextInt();
        M = io.nextInt();
        for(int i =0;i<N;i++){
            String s = io.next();
            for(int j = 0;j<M;j++){
                g[N][M] = s.charAt(j);
            }
        }
        for(int i =0;i<N;i++){
            for(int j =0 ;j<M;j++){
                if(!v[N][M])

            }
        }
        io.close();
    }
    public static void ff(int x, int y){
        Stack<Pair> s = new Stack<Pair>();
        s.push(new Pair(x, y));
        while(!s.isEmpty()){
            Pair p = s.pop();
            x = p.a; y = p.b;
            if (x < 0 || x >= N || y < 0 || y >= M || g[x][y] == '#' || v[x][y])
                continue;
            v[x][y] = true;
            for (int i = 0; i < 4; i++){
                int nx = x+xd[i]; int ny = y+yd[i];
                s.add(new Pair(nx,ny));
            }
        }
    }
    /*public static void dfs(int node)
    {
        visited[node] = true;
        for (int u: adj[node]) {
            if (!visited[u])
                dfs(u);
        }
    }
    public static int count_components()
    {
        int count = 0;
        for (int i = 1; i <= adj.length-1; i++){
            if(!visited[i]){
                count++;
                dfs(i);
            }
        }
        return count;
    }     */
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