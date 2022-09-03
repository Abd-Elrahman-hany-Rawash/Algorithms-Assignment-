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

class Problem3 {
    public static void main(String[] args) {
        ArrayList<Edge> graph = new ArrayList<>();
        // input: (1, 2, 5)
        // input: (1, 3, 3)
        // input: (4, 1, 6)
        // input: (2, 4, 7)
        // input: (3, 2, 4)
        // input: (3, 4, 5)
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Number OF Vertices");
        int NumVertices = input.nextInt();// 4

        System.out.println("Enter Number OF Edges");
        int NumofEdges = input.nextInt();// 6

        for (int i = 0; i < NumofEdges; i++) {
            System.out.println("Enter first vertice then Second vertic then weghit");
            int v1 = input.nextInt();
            int v2 = input.nextInt();
            int weghit = input.nextInt();
            graph.add(new Edge(v1, v2, weghit));
        }

        ArrayList<Edge> mst = MST.MinimumSpanningTree(graph, NumVertices);
        int sum = 0;
        for (int i = 0; i < mst.size(); i++)
            sum = sum + mst.get(i).weight;
        System.out.println("Result = " + sum);

    }
}