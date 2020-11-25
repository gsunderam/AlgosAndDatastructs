package com.gs.home.datastructures.graph;

import java.util.*;

/**
 * Modeled as a Map of (vertex as KEY -> Map of (Adj vertices , weight) as VALUES)
 * A -> B, 2
 *      C, 3
 * B -> A, 2
 *      D, 3 etc.
 */
public class Graph {
    Map<Vertex, Map<Vertex, Integer>> adjacentVertices = new LinkedHashMap<>();

    public void addVertex(String name) {
        adjacentVertices.putIfAbsent(Flyweight.getVertex(name), new HashMap<>());
    }

    public void addEdge(String src, String dest, int weight) {
        Vertex source = Flyweight.getVertex(src);
        Vertex destination = Flyweight.getVertex(dest);
        adjacentVertices.get(source).put(destination, weight);
        adjacentVertices.get(destination).put(source, weight);
    }

    /** Added to accommodate a reqt in Cracking the interview book for build order */
    public void addEdge(String src, String dest) {
        Vertex source = Flyweight.getVertex(src);
        Vertex destination = Flyweight.getVertex(dest);
        adjacentVertices.get(source).put(destination, 0);
    }

    public Map<Vertex, Integer> getVertices(String name) {
        return adjacentVertices.get(Flyweight.getVertex(name));
    }

    public Vertex getVertex(String key) {
        Set<Vertex> vertices = adjacentVertices.keySet();
        return vertices.parallelStream().filter(v -> v.name.equalsIgnoreCase(key)).findFirst().get();
    }

    public boolean isVertexOfEachOther(final String v1, final String v2) {
         return getVertices(v1).keySet().stream().anyMatch(vertex -> vertex.name.equalsIgnoreCase(v2));
    }

    public int getEdgeLength(String source, String destination) {
        Optional<Integer> weight = adjacentVertices.get(Flyweight.getVertex(source)).entrySet().stream().
                filter(entry -> entry.getKey().name.equals(destination)).
                map(entry -> entry.getValue()).findAny();
        return weight.get();
    }

    public Map<Vertex, Map<Vertex, Integer>> getAdjacentVertices() {
        return adjacentVertices;
    }
}