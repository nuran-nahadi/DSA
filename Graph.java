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
        for (int i = 0; i <= cap; i++) {
            adj_list.add(new ArrayList<>()); 
        }
    }
    public void add_edge(int u, int v){
        
        adj_list.get(u).add(v);
        adj_list.get(v).add(u);
        vertex.add(u);
        vertex.add(v);
    }

    public void add_vertex(int n){
        cap+=n;
        for (int i = 0; i < n; i++) {
            adj_list.add(new ArrayList<>());
        }
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
            //System.out.print(current+" ");

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
        System.out.println();
    }
    public void BFS_path(int s){
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
            System.out.print(current+" ");

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
        System.out.println();
    }
    public void Print_path(int s,int node){
        if (node == s) {
            System.out.print(s);
        }

        else if (prev[node] == Integer.MIN_VALUE) {
            System.out.println("No Path");
        }
        else{
            Print_path(s, prev[node]);
            System.out.print(" "+node);
        }
    }

    public void Print_Neighbours(int node){
        if (adj_list.get(node).isEmpty()) {
            System.out.println("Node does not exist in graph!");
        }
        for (int i : adj_list.get(node)) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void Print_Adjacency_List(){
        for (int i = 0; i < cap; i++) {
            if (vertex.contains(i)) {
                System.out.print(i+" ->");
                for (int j : adj_list.get(i)) {
                    System.out.print(" "+j);
                }
                System.out.println();
            }
        }
    }

    public  void Print_All_Shortest_Path(int s){
        BFS(s);
        for (int i = 0; i < cap; i++) {
            if (vertex.contains(i)) {
                Print_path(s, i);
                System.out.println();
            }
        }
    }
}