package com.gs.home.datastructures.graph;

import java.util.*;

import static com.gs.home.log.Logger.stdout;


/**
 * Java based implementation of the Dijkstra Algorithm using priority queue
 * TODO: Further optimization needed. streamlining
 */
public final class DijkstraAlgorithm {
    private static final int INITIAL_WEIGHT = 100000;
    private transient final Graph graph;
    private transient final String start, end;
    private Queue<VertexWeight> queue = new PriorityQueue<>();

    public DijkstraAlgorithm(Graph graph, String start, String end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
    }

    public void printShortestPath() {
        queue = new PriorityQueue<>();
        initializeQueue();
        Set<String> visited = new HashSet<>();
        List<String> journey = new ArrayList();
        journey.add(start);
        visited.add(start);
        stdout("Processing node " + start);
        String currentNode = "";
        int newDistance = 0;

        while (!currentNode.equalsIgnoreCase(end)) {
            VertexWeight vtxWgt = queue.poll();
            stdout("Vertices of " + vtxWgt.name);

            Map<Vertex, Integer> adjVerts = graph.getVertices(vtxWgt.name);
            for (Map.Entry<Vertex, Integer> edgeWeights : adjVerts.entrySet()) {
                if (!visited.contains(edgeWeights.getKey().name)) {
                    currentNode = edgeWeights.getKey().name;
                    visited.add(currentNode);
                    stdout("Processing node " + currentNode);
                    VertexWeight queueEl = Flyweight.getVertexWeight(edgeWeights.getKey().name);
                    if (queueEl.weight > edgeWeights.getValue() + vtxWgt.weight) {
                        queue.remove(queueEl);
                        newDistance = edgeWeights.getValue() + vtxWgt.weight;
                        queue.offer(Flyweight.getVertexWeight(edgeWeights.getKey().name, newDistance));
                        journey.add(currentNode);
                        if (currentNode.equalsIgnoreCase(end)) break;
                    }
                }
            }
        }

        stdout("Min distance: " + newDistance);
        stdout(queue);
//        stdout("Nodes visited: " + visited);
        stdout("Journey in the Graph: " + journey);
        determineTravelPath(journey);
    }

    private void determineTravelPath(List<String> journey) {
        StringBuilder travelPath = new StringBuilder();
        travelPath.append(end);
        Collections.reverse(journey);
//        stdout(journey);
        String dest, source;
        Map<String, Integer> legLengths = new LinkedHashMap<>();

        int i = 0, j = 1;
        while (j <= journey.size() - 1) {
            dest = journey.get(i);
            source = journey.get(j);
            if (graph.isVertexOfEachOther(dest, source)) {
                i = j;
                travelPath.append(source);
                legLengths.put(source + dest, graph.getEdgeLength(dest, source));
            }

            j++;
        }

        stdout(String.format("Travel path = %s", travelPath.reverse().toString()));
//        stdout(legLengths);
        showGPSPath(legLengths);
    }

    private void initializeQueue() {
        Set<Vertex> allVertices = graph.adjacentVertices.keySet();
        for (Vertex vertex : allVertices) {
            if (vertex.name.equalsIgnoreCase(start)) {
                queue.add(Flyweight.getVertexWeight(start, 0));
            } else {
                queue.add(Flyweight.getVertexWeight(vertex.name, INITIAL_WEIGHT));
            }
        }
    }

    private void showGPSPath(Map<String, Integer> legLengths) {
        List<String> towns = new ArrayList<>(legLengths.keySet());
        for(int i = towns.size()-1; i >= 0; i--) {
            stdout(String.format("Distance (%s) = %d", towns.get(i), legLengths.get(towns.get(i))));
        }
    }
}
