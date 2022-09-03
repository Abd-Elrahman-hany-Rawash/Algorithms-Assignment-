import java.util.*;
class Edge {
    public int Source, Destination, weight;

    public Edge(int src, int dest, int weight) {
        this.Source = src;
        this.Destination = dest;
        this.weight = weight;
    }
}

class MST {
    Map<Integer, Integer> graph = new HashMap<>();

    MST(int size) {
        for (int i = 0; i < size; i++)
            graph.put(i, i);
    }

    private int search(int k) {
        if (this.graph.get(k) == k)
            return k;
        return search(this.graph.get(k));
    }

    public static ArrayList<Edge> MinimumSpanningTree(ArrayList<Edge> edges, int NumVertices) {
        ArrayList<Edge> result = new ArrayList<>();
        MST mst = new MST(NumVertices);
        int index = 0;
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));
        while (result.size() != NumVertices - 1) {
            Edge next_edge = edges.get(index++);
            int x = mst.search(next_edge.Source);
            int y = 0;
            try {
                y = mst.search(next_edge.Destination);
            } catch (Exception e) {
            }
            if (x != y) {
                int key = mst.search(x);
                int value = mst.search(y);
                result.add(next_edge);
                mst.graph.put(key, value);
            }
        }
        return result;
    }
}

class Problem1 {
    public static void main(String[] args) {
        ArrayList<Edge> graph = new ArrayList<>();
        graph.add(new Edge(1, 4, 1));
        graph.add(new Edge(1, 2, 2));
        graph.add(new Edge(1, 3, 4));
        graph.add(new Edge(2, 4, 3));
        graph.add(new Edge(2, 5, 10));
        graph.add(new Edge(3, 4, 2));
        graph.add(new Edge(3, 6, 5));
        graph.add(new Edge(4, 5, 7));
        graph.add(new Edge(4, 6, 8));
        graph.add(new Edge(4, 7, 4));
        graph.add(new Edge(5, 7, 6));
        graph.add(new Edge(6, 7, 1));
        int NumVertices = 7;
        ArrayList<Edge> mst = MST.MinimumSpanningTree(graph, NumVertices);
        System.out.println("Edges   weight");
        for (int i = 0; i < mst.size(); i++)
            System.out.println(mst.get(i).Source + "-" + mst.get(i).Destination + "       " + mst.get(i).weight);
    }
}