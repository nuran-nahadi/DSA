public class driver {
    public static void main(String[] args) {
        Graph_list g = new Graph_list(6);

        g.add_edge(3, 0);
        g.add_edge(5, 2);
        g.add_edge(0, 1);
        g.add_edge(3, 1);
        g.add_edge(2, 4);
        g.add_edge(5, 3);

        g.BFS(3);
        g.Print_Neighbours(4);
        g.add_vertex(6);
        g.Print_path(3, 4);
        System.out.println();
        g.Print_Adjacency_List();
        g.Print_All_Shortest_Path(3);
    }
}
