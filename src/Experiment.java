import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Experiment {
    private static final int[] GRAPH_SIZES = {10, 30, 100};
    private final List<ResultRow> results = new ArrayList<>();

    public void runTraversals(Graph graph) {
        long bfsStart = System.nanoTime();
        List<Integer> bfsOrder = graph.bfs(0);
        long bfsEnd = System.nanoTime();

        long dfsStart = System.nanoTime();
        List<Integer> dfsOrder = graph.dfs(0);
        long dfsEnd = System.nanoTime();

        int size = graph.getVertexCount();
        long bfsTime = bfsEnd - bfsStart;
        long dfsTime = dfsEnd - dfsStart;

        results.add(new ResultRow(size, graph.getEdgeCount(), bfsTime, dfsTime));

        if (size == 10) {
            System.out.println("\nBFS order for small graph: " + bfsOrder);
            System.out.println("DFS order for small graph: " + dfsOrder);
        }
    }

    public void runMultipleTests() {
        for (int size : GRAPH_SIZES) {
            Graph graph = createConnectedGraph(size);
            if (size == 10) {
                System.out.println("=== Graph structure (10 vertices) ===");
                graph.printGraph();
            }
            runTraversals(graph);
        }
    }

    public void printResults() {
        System.out.println("\n=== Performance Results (nanoseconds) ===");
        System.out.printf("%-10s %-10s %-18s %-18s%n", "Vertices", "Edges", "BFS Time", "DFS Time");
        for (ResultRow row : results) {
            System.out.printf(
                    "%-10d %-10d %-18d %-18d%n",
                    row.vertices(),
                    row.edges(),
                    row.bfsTimeNs(),
                    row.dfsTimeNs()
            );
        }
    }

    private Graph createConnectedGraph(int size) {
        Graph graph = new Graph(false);
        for (int i = 0; i < size; i++) {
            graph.addVertex(new Vertex(i));
        }

        Random random = new Random(size * 31L);

        // First pass guarantees connectivity by forming a chain.
        for (int i = 0; i < size - 1; i++) {
            graph.addEdge(i, i + 1);
        }

        int additionalEdges = Math.max(size, size * 2);
        for (int i = 0; i < additionalEdges; i++) {
            int from = random.nextInt(size);
            int to = random.nextInt(size);
            if (from != to) {
                graph.addEdge(from, to);
            }
        }

        return graph;
    }

    private record ResultRow(int vertices, int edges, long bfsTimeNs, long dfsTimeNs) {
    }
}
