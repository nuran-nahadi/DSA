import java.util.*;

class Graph_list {
    private List<List<Integer>> adj_list;
    private Set<Integer> vertex;
    int []prev;
    int []d;
    int cap = 0;
    Graph_list(int n){
        adj_list = new ArrayList<>();
        vertex = new LinkedHashSet<Integer>();
        cap = n+1;
        for (int i = 0; i <= n; i++) {
            adj_list.add(new ArrayList<>()); 
        }
    }
    public void add_edge(int u, int v){
        
        adj_list.get(u).add(v);
        adj_list.get(v).add(u);
        vertex.add(u);
        vertex.add(v);
    }
    public void print_graph(){
        for (int i = 0; i < cap; i++) {
            for (int j = 0; j < cap; j++) {
                if (adj_list.get(i).get(j)==1) {
                    System.out.println(i+","+j+"\n");
                }
            }
        }
    }
    public void bfs(int s) {
      
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[adj_list.size()];
        
        visited[s] = true;
        q.add(s);
        
        while (!q.isEmpty()) {
          
            int current = q.poll();
            System.out.print(current + " ");

            for (int i : adj_list.get(current)) {
                if (!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }
    }

    public void BFS(int s){
        Queue<Integer> q = new LinkedList<>();
        int []state = new int[cap];

        d = new int[cap];
        prev = new int[cap];

        for (int i : state) {
            state[i]=0;
            d[i]=Integer.MAX_VALUE;
            prev[i]=Integer.MIN_VALUE;
        }

        state[s]=1;
        d[s]=0;
        prev[s]=s;
        q.add(s);

        while (!q.isEmpty()) {
            int current = q.poll();
            //System.out.println(current);

            for (int i : adj_list.get(current)) {
                if (state[i] == 0) {
                    state[i] = 1;
                    d[i] = d[current]+1;
                    prev[i] = current;
                    q.add(i);
                }
            }
            state[current] = 2;
        }

    }

    public void Print_path(int s,int node){
        if (node == s) {
            System.out.println(s);
        }

        else if (prev[node] == Integer.MIN_VALUE) {
            System.out.println("No Path");
        }
        else{
            Print_path(s, prev[node]);
            System.out.println(node);
        }
    }
}
