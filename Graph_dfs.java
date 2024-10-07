class Graph {
    public List<List<Integer>> adj_list;
    private Set<Integer> vertex;
    int[] prev;
    int[] d;
    int[] dfs_state;
    int[] disco;
    int[] fin;
    int cap = 0;

    Graph(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int n = sc.nextInt();
            int e = sc.nextInt();
            
            adj_list = new ArrayList<>();
            vertex = new LinkedHashSet<Integer>();
            cap = n+1;
            for (int i = 0; i <= cap; i++) {
                adj_list.add(new ArrayList<>()); 
            }
            for (int i = 0; i < e; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                add_directed_edge(u, v);
            }
            sc.close();
        }catch(Exception e)
            {
            e.printStackTrace();
            }
    }

    public void add_edge(int u, int v) {

        adj_list.get(u).add(v);
        adj_list.get(v).add(u);
        vertex.add(u);
        vertex.add(v);
    }

    public void add_directed_edge(int u,int v){
        adj_list.get(u).add(v);
        vertex.add(u);
        vertex.add(v);
    }

    public void add_vertex(int n) {
        cap += n;
        for (int i = 0; i < n; i++) {
            adj_list.add(new ArrayList<>());
        }
    }

    public void bfs_easy(int s) {

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

    public void BFS(int s) {
        Queue<Integer> q = new LinkedList<>();
        int[] state = new int[cap];

        d = new int[cap];
        prev = new int[cap];

        for (int i=0; i < cap; i++) {
            state[i] = 0;
            d[i] = Integer.MAX_VALUE;
            prev[i] = Integer.MIN_VALUE;
        }

        state[s] = 1;
        d[s] = 0;
        prev[s] = s;
        q.add(s);

        while (!q.isEmpty()) {
            int current = q.poll();
            // System.out.print(current+" ");

            for (int i : adj_list.get(current)) {
                if (state[i] == 0) {
                    state[i] = 1;
                    d[i] = d[current] + 1;
                    prev[i] = current;
                    q.add(i);
                }
            }
            state[current] = 2;
        }
        System.out.println();
    }


    public void DFS(int s){
        dfs_state = new int[cap];

        disco = new int[cap];
        fin = new int[cap];
        prev = new int[cap];

        for (int i=0; i < cap; i++) {
            dfs_state[i] = 0;
            disco[i] = Integer.MAX_VALUE;
            fin[i] = Integer.MAX_VALUE;
            prev[i] = Integer.MIN_VALUE;
        }
        int time = 0;
        System.out.print("DFS traversal starting from vertex "+ s +":");
        DFS_visit(s,time);
    }

    public void DFS_visit(int u,int time){
        dfs_state[u] = 1;
        time++;
        disco[u] = time;
        System.out.print(" " + u);
        for (int i : adj_list.get(u)) {
            if (dfs_state[i] == 0) {
                prev[i] = u;
                DFS_visit(i, time);
            }
        }
        dfs_state[u] = 2;
        time++;
        fin[u] = time;
    }



















    public void BFS_path(int s) {
        Queue<Integer> q = new LinkedList<>();
        int[] state = new int[cap];

        d = new int[cap];
        prev = new int[cap];

        for (int i=0; i < cap; i++) {
            state[i] = 0;
            d[i] = Integer.MAX_VALUE;
            prev[i] = Integer.MIN_VALUE;
        }

        state[s] = 1;
        d[s] = 0;
        prev[s] = s;
        q.add(s);

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.print(current + " ");

            for (int i : adj_list.get(current)) {
                if (state[i] == 0) {
                    state[i] = 1;
                    d[i] = d[current] + 1;
                    prev[i] = current;
                    q.add(i);
                }
            }
            state[current] = 2;
        }
        System.out.println();
    }

    public void Print_path(int s, int node) {
        if (node == s) {
            System.out.print(s);
        }

        else if (prev[node] == Integer.MIN_VALUE) {
            System.out.print("No Path");
        } else {
            Print_path(s, prev[node]);
            System.out.print(" " + node);
        }
    }

    public void Print_Neighbours(int node) {
        if (adj_list.get(node).isEmpty()) {
            System.out.println("Node does not exist in graph!");
        }
        for (int i : adj_list.get(node)) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void displayGraph() {
        for (int i = 0; i < cap; i++) {
            if (vertex.contains(i)) {
                System.out.print(i + " ->");
                for (int j : adj_list.get(i)) {
                    System.out.print(" " + j);
                }
                System.out.println();
            }
        }
    }

    public void Print_All_Shortest_Path(int s) {
        BFS(s);
        for (int i = 0; i < cap; i++) {
            if (vertex.contains(i)) {
                Print_path(s, i);
                System.out.println();
            }
        }
    }
}
