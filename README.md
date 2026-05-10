# Assignment 4: Graph Traversal and Representation System

## Project Overview

This project implements graph representation using an adjacency list and supports two traversal algorithms:

- Breadth-First Search (BFS)
- Depth-First Search (DFS)

The graph is modeled with vertices and edges, then tested on three graph sizes (`10`, `30`, `100` vertices) with execution time measured via `System.nanoTime()`.

## Repository Structure

```text
src/
├── Vertex.java
├── Edge.java
├── Graph.java
├── Experiment.java
└── Main.java
```

## Class Descriptions

- `Vertex` stores a unique vertex id.
- `Edge` stores `source` and `destination` vertices.
- `Graph` stores:
  - vertices (`Map<Integer, Vertex>`)
  - adjacency list (`Map<Integer, List<Integer>>`)
  - edge list (`List<Edge>`)
  - traversal methods `bfs(int start)` and `dfs(int start)`
- `Experiment` creates test graphs, runs traversals, and prints performance results.
- `Main` is the entry point and runs the experiment flow.

## Adjacency List Representation

Each vertex id maps to a list of neighboring vertex ids.
Example format:

`V0 -> [1, 2, 5]`

This representation is memory-efficient for sparse graphs and fits traversal algorithms with complexity `O(V + E)`.

## Algorithm Descriptions

### BFS

**How it works (step-by-step):**
1. Mark the start vertex as visited and put it in a queue.
2. Remove one vertex from the queue.
3. Visit all unvisited neighbors and push them into the queue.
4. Repeat until the queue is empty.

**Use cases:**
- shortest path in unweighted graphs
- level-order exploration
- nearest-node queries

**Time complexity:** `O(V + E)`

### DFS

**How it works (step-by-step):**
1. Push the start vertex into a stack.
2. Pop a vertex and visit it if not visited.
3. Push its unvisited neighbors to continue deeper.
4. Backtrack automatically when no deeper unvisited neighbor exists.

**Use cases:**
- cycle detection
- topological-style exploration
- connectivity/component checks

**Time complexity:** `O(V + E)`

## Experimental Results

Execution times table template (fill with your local run values in nanoseconds):

| Vertices | Edges | BFS Time | DFS Time |
|---|---:|---:|---:|
| 10 | (from output) | (from output) | (from output) |
| 30 | (from output) | (from output) | (from output) |
| 100 | (from output) | (from output) | (from output) |

### Observations

- Graph size growth generally increases traversal time because both `V` and `E` increase.
- BFS and DFS are usually close for sparse graphs, but one may be faster depending on graph shape and runtime conditions.
- Results align with expected `O(V + E)` behavior (linear growth with total visited vertices + edges).
- Traversal order changes depending on graph structure and neighbor insertion order in adjacency lists.

## Analysis Questions

### 1) How does graph size affect BFS and DFS performance?

Larger graphs require visiting more vertices and edges, so both algorithms take more time overall.

### 2) Which traversal is faster in experiments?

Fill this after running locally. In many cases they are close, with differences caused by graph structure and runtime conditions.

### 3) Do results match expected complexity `O(V + E)`?

Yes. Both traversals scale with graph size and edge count rather than with squared growth.

### 4) How does graph structure affect traversal order?

Structure (branching and adjacency order) determines visitation sequence for both BFS and DFS.
Same algorithm on different edge layout can produce different traversal orders.

### 5) When is BFS preferred over DFS?

BFS is preferred when shortest path in unweighted graphs is required or when level-by-level search is needed.

### 6) What are limitations of DFS?

DFS does not guarantee shortest path and may dive deeply into one branch before checking closer alternatives.
Recursive DFS can also risk stack overflow on very deep graphs (iterative stack avoids call-stack overflow).

## Screenshots

Add screenshots to `docs/screenshots/`:

- graph structure output (10 vertices)
- BFS traversal output
- DFS traversal output
- performance results table/output

## Reflection

This assignment helped reinforce how graphs are represented in code and how traversal behavior depends on both the algorithm and edge layout. Building adjacency lists manually made it clearer why graph operations usually target `O(V + E)` performance.

The biggest practical insight was comparing BFS and DFS on the same generated graphs. BFS naturally expands by levels and is useful for shortest paths, while DFS is effective for deep exploration patterns. Measuring both with `nanoTime()` showed they are usually close, but runtime differences appear as graph structure grows.

## Run Instructions

From project root:

```bash
javac src/*.java
java -cp src Main
```