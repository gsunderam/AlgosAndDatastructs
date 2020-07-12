package com.gs.home.datastructures.graph;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Flyweight design pattern to store objects in a cache and dispense when needed by the clients.
 * Factory to create "new" objects in lieu of the clients creating them
 * This reduces memory footprint and pauses due to GC.
 */
public final class Flyweight {
    private static final Map<String, Vertex> flyweight = new ConcurrentHashMap<>();
    private static final Map<String, VertexWeight> flyweightVtx = new ConcurrentHashMap<>();

    public static Vertex getVertex(String name) {
        Vertex vertex = flyweight.get(name);

        if (vertex == null) {
            vertex = new Vertex(name);
            flyweight.putIfAbsent(name, vertex);
        }

        return vertex;
    }

    public static VertexWeight getVertexWeight(String name, int weight) {
        VertexWeight vertexWeight = flyweightVtx.get(name);

        if (vertexWeight == null) {
            vertexWeight = new VertexWeight(name, weight);
            flyweightVtx.putIfAbsent(name, vertexWeight);
        } else {
            vertexWeight.weight = weight;
        }

        return vertexWeight;
    }

    public static VertexWeight getVertexWeight(String name) {
        return flyweightVtx.get(name);
    }
}
