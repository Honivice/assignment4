import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    private final Map<Integer, Vertex> vertices = new HashMap<>();
    private final Map<Integer, List<Integer>> adjacencyList = new LinkedHashMap<>();
    private final List<Edge> edges = new ArrayList<>();
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(Vertex vertex) {
        if (vertex == null || vertices.containsKey(vertex.getId())) {
            return;
        }
        vertices.put(vertex.getId(), vertex);
        adjacencyList.put(vertex.getId(), new ArrayList<>());
    }

    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            throw new IllegalArgumentException("Both vertices must exist before adding an edge.");
        }

        adjacencyList.get(from).add(to);
        edges.add(new Edge(vertices.get(from), vertices.get(to)));

        if (!directed) {
            adjacencyList.get(to).add(from);
            edges.add(new Edge(vertices.get(to), vertices.get(from)));
        }
    }

    public void printGraph() {
        System.out.println("Adjacency list:");
        for (Map.Entry<Integer, List<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println("V" + entry.getKey() + " -> " + entry.getValue());
        }
    }

    public List<Integer> bfs(int start) {
        if (!vertices.containsKey(start)) {
            throw new IllegalArgumentException("Start vertex does not exist: " + start);
        }

        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();

        visited.add(start);
        queue.offer(start);

        // BFS explores level-by-level using a queue.
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return order;
    }

    public List<Integer> dfs(int start) {
        if (!vertices.containsKey(start)) {
            throw new IllegalArgumentException("Start vertex does not exist: " + start);
        }

        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(start);

        // Iterative DFS goes deep first, then backtracks using a stack.
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            order.add(current);

            List<Integer> neighbors = adjacencyList.get(current);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        return order;
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return directed ? edges.size() : edges.size() / 2;
    }
}
