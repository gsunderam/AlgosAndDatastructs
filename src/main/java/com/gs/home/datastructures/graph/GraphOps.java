package com.gs.home.datastructures.graph;

import java.util.*;

import static com.gs.home.log.Logger.*;

public class GraphOps {
    public static void printGraph(Graph graph) {
        Set<Vertex> vertices = graph.adjacentVertices.keySet();

        for (Vertex vertex : vertices) {
            StringBuilder line = new StringBuilder();
            line.append(vertex).append("->");

            for (Vertex v : graph.adjacentVertices.get(vertex).keySet()) {
                line.append(v).append(" ");
            }

            stdout(line);
        }
    }

    /**
     * Stacks are used for LIFO processing like DFS search.
     * Depth First
     * @param root
     */
    public static void traverseDFS(String root, Graph graph) {
        Stack<String> stack = new Stack<>();
        stack.push(root);
        printTab("DFS: -> ");

        while (!stack.isEmpty()) {
            String el = stack.pop();
            //Another way is to have a visited Set and check that. That has Space complexity. This approach has Time Complexity
            //But with Java 8+ processing is faster.
            Vertex rootVertex = graph.getVertex(el);

            if (!rootVertex.visited) {
                rootVertex.visited = true;
                printTab(el);
            }

            Set<Vertex> vertices = graph.adjacentVertices.get(rootVertex).keySet();

            for (Vertex vertex : vertices) {
                if (!vertex.visited)  stack.push(vertex.name);
            }
        }

        print("\n");
    }

    /**
     * Queues are used in below algorithm for FIFO processing.
     * Breadth First
     * @param root
     */
    public static void traverseBFS(String root, Graph graph) {
        Queue<String> queue = new PriorityQueue<>();
        Set<String> visited = new HashSet<>();
        queue.offer(root);
        printTab("BFS: -> ");

        while (!queue.isEmpty()) {
            String el = queue.poll();

            if (!visited.contains(el)) {
                visited.add(el);
                printTab(el);
            }

            for (Vertex vertex: graph.getVertices(el).keySet()) {
                if (!visited.contains(vertex.name)) queue.offer(vertex.name);
            }
        }

        print("\n");
    }

    /**
     * Print the path between nodes (inclusive)
     * @param start
     * @param end
     */
    public static void printPath(Graph graph, String start, String end, StringBuilder path) {
        if (start.equalsIgnoreCase(end)) {
            stdout("Path is " + path.toString());
            return;
        }

        if (!path.toString().contains(start)) path.append(start).append("-");

        Set<Vertex> vertices = graph.getVertices(start).keySet();

        for (Vertex v: vertices) {
            if (!path.toString().contains(v.name)) {
                path.append(v.name).append("-");
                printPath(graph, v.name, end, path);
                return;
            }
        }
    }
}
