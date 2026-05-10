# Assignment 4

Simple Java project for graph traversal.

## What is implemented

- `Vertex` class (id)
- `Edge` class (source, destination)
- `Graph` class with adjacency list
- BFS traversal
- DFS traversal
- Performance test for graphs with 10, 30, and 100 vertices

## Files

- `src/Vertex.java`
- `src/Edge.java`
- `src/Graph.java`
- `src/Experiment.java`
- `src/Main.java`

## Complexity

- BFS: `O(V + E)`
- DFS: `O(V + E)`

## Performance table

Fill from your console output:

| Vertices | Edges | BFS (ns) | DFS (ns) |
|---|---:|---:|---:|
| 10 |  |  |  |
| 30 |  |  |  |
| 100 |  |  |  |

## Short answers

- **How size affects speed:** bigger graph -> more time.
- **Which is faster:** depends on graph structure; usually close.
- **Does it match `O(V + E)`:** yes.
- **When BFS is better:** shortest path in unweighted graph.
- **DFS limitation:** does not guarantee shortest path.

## Screenshots to add

Put screenshots in `docs/screenshots/`:

- graph output
- BFS output
- DFS output
- performance output

## Run

```bash
javac src/*.java
java -cp src Main
```