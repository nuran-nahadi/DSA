import java.util.*;
import java.io.*;

class Edge {
    int destination;
    double weight;

    Edge(int destination, double weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    List<List<Edge>> adj_list;
    private List<List<Edge>> reverse_list;
    private Set<Integer> vertex;
    private Stack<Integer> topology = new Stack<>();
    private Stack<Integer> scc_stack = new Stack<>();
    int[] prev;
    double[] distance;  // Changed to double for weighted distances
    int[] bfs_state;
    int[] dfs_state;
    int[] transpose_visited;
    int[] disco;
    int[] fin;
    int time = 0;
    int cap = 0;

    Graph(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            int n = sc.nextInt();
            int e = sc.nextInt();

            adj_list = new ArrayList<>();
            reverse_list = new ArrayList<>();
            vertex = new LinkedHashSet<Integer>();
            cap = n + 1;
            for (int i = 0; i <= cap; i++) {
                adj_list.add(new ArrayList<>());
                reverse_list.add(new ArrayList<>());
            }
            for (int i = 0; i < e; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                double w = sc.nextDouble();  // Read weight from file
                add_directed_edge(u, v, w);
            }
            init();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        prev = new int[1000000];
        distance = new double[1000000];  // Changed to double
        dfs_state = new int[1000000];
        disco = new int[1000000];
        fin = new int[1000000];
        bfs_state = new int[1000000];
        transpose_visited = new int[1000000];

        for (int i = 0; i < cap; i++) {
            dfs_state[i] = 0;
            transpose_visited[i] = 0;
            disco[i] = Integer.MAX_VALUE;
            fin[i] = Integer.MAX_VALUE;
            prev[i] = Integer.MIN_VALUE;
            distance[i] = Double.POSITIVE_INFINITY;  // Initialize distances to infinity
        }
    }

    // Modified methods for weighted edges
    public void add_edge(int u, int v, double weight) {
        adj_list.get(u).add(new Edge(v, weight));
        adj_list.get(v).add(new Edge(u, weight));  // For undirected weighted graph
        vertex.add(u);
        vertex.add(v);
    }

    public void add_directed_edge(int u, int v, double weight) {
        adj_list.get(u).add(new Edge(v, weight));
        reverse_list.get(v).add(new Edge(u, weight));
        vertex.add(u);
        vertex.add(v);
    }

    // Dijkstra's algorithm for shortest paths in weighted graphs
    public void dijkstra(int source) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Double.compare(distance[a[0]], distance[b[0]]));
        
        for (int i = 0; i < cap; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
        }
        
        distance[source] = 0;
        pq.offer(new int[]{source, 0});
        
        while (!pq.isEmpty()) {
            int u = pq.poll()[0];
            
            for (Edge edge : adj_list.get(u)) {
                int v = edge.destination;
                double weight = edge.weight;
                
                if (distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    prev[v] = u;
                    pq.offer(new int[]{v, v});
                }
            }
        }
    }

    // Print shortest path with weights
    public void printShortestPath(int source, int destination) {
        dijkstra(source);
        
        if (distance[destination] == Double.POSITIVE_INFINITY) {
            System.out.println("No path exists between " + source + " and " + destination);
            return;
        }
        
        List<Integer> path = new ArrayList<>();
        for (int v = destination; v != -1; v = prev[v]) {
            path.add(v);
        }
        Collections.reverse(path);
        
        System.out.println("Shortest path from " + source + " to " + destination + ":");
        System.out.print("Path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println("\nTotal distance: " + distance[destination]);
    }

    // Modified display method to show weights
    public void displayGraph() {
        for (int i = 0; i < cap; i++) {
            if (vertex.contains(i)) {
                System.out.print(i + " ->");
                for (Edge edge : adj_list.get(i)) {
                    System.out.print(" (" + edge.destination + ", weight: " + edge.weight + ")");
                }
                System.out.println();
            }
        }
    }

    // Bellman-Ford algorithm for detecting negative weight cycles
    public boolean bellmanFord(int source) {
        for (int i = 0; i < cap; i++) {
            distance[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
        }
        distance[source] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 0; i < vertex.size() - 1; i++) {
            for (int u : vertex) {
                for (Edge edge : adj_list.get(u)) {
                    int v = edge.destination;
                    double weight = edge.weight;
                    if (distance[u] != Double.POSITIVE_INFINITY && distance[u] + weight < distance[v]) {
                        distance[v] = distance[u] + weight;
                        prev[v] = u;
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u : vertex) {
            for (Edge edge : adj_list.get(u)) {
                int v = edge.destination;
                double weight = edge.weight;
                if (distance[u] != Double.POSITIVE_INFINITY && distance[u] + weight < distance[v]) {
                    return false; // Negative weight cycle exists
                }
            }
        }
        return true;
    }

    // Method to get minimum spanning tree using Prim's algorithm
    public List<Edge> primMST() {
        List<Edge> mst = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Edge[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[0].weight, b[0].weight));
        
        // Start from the first vertex in the graph
        int start = vertex.iterator().next();
        visited.add(start);
        
        // Add all edges from start vertex to PQ
        for (Edge edge : adj_list.get(start)) {
            pq.offer(new Edge[]{edge, new Edge(start, edge.weight)});
        }
        
        while (!pq.isEmpty() && visited.size() < vertex.size()) {
            Edge[] minEdge = pq.poll();
            Edge edge = minEdge[0];
            Edge parent = minEdge[1];
            
            if (visited.contains(edge.destination)) {
                continue;
            }
            
            // Add edge to MST
            mst.add(new Edge(parent.destination, edge.weight));
            visited.add(edge.destination);
            
            // Add all edges from the new vertex
            for (Edge nextEdge : adj_list.get(edge.destination)) {
                if (!visited.contains(nextEdge.destination)) {
                    pq.offer(new Edge[]{nextEdge, new Edge(edge.destination, nextEdge.weight)});
                }
            }
        }
        
        return mst;
    }
}
public class Weighted_Graph_file {
    
}
