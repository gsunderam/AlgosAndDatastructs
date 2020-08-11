package com.gs.home.datastructures.graph;

import java.util.Map;

import static com.gs.home.log.Logger.print;
import static com.gs.home.log.Logger.stdout;

public class GraphClient {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("D", "F", 1);
        graph.addEdge("E", "F", 1);
        graph.addEdge("E", "C", 1);

        Map<Vertex, Integer> vertices = graph.getVertices("A");
        stdout("Vertices for A are -> " + vertices);
        stdout(graph.isVertexOfEachOther("A", "F"));
        GraphOps.printGraph(graph);
        print("\n");
//        GraphOps.traverseDFS("A", graph);
        GraphOps.noStackDFS("A", graph);
        GraphOps.traverseBFS("A", graph);
        GraphOps.printPath(graph,"D", "E", new StringBuilder(""));
        stdout("AB: " + graph.getEdgeLength("A", "B"));
        DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph, "A", "E");
        dijkstra.printShortestPath();
    }
}
