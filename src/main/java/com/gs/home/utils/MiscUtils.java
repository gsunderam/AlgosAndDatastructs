package com.gs.home.utils;

import com.gs.home.datastructures.graph.Graph;
import com.gs.home.log.Logger;

import java.util.Arrays;
import java.util.List;

public class MiscUtils {
    public static Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("a");
        graph.addVertex("b");
        graph.addVertex("c");
        graph.addVertex("d");
        graph.addVertex("e");
        graph.addVertex("f");

        for (String [] prj : getProjectsDeps()) {
            graph.addEdge(prj[1], prj[0]);
        }

        return graph;
    }

    public static List<String[]> getProjectsDeps() {
        String [] dep1 = {"a", "d"};
        String [] dep2 = {"b", "d"};
        String [] dep3 = {"d", "e"};
        String [] dep4 = {"d", "c"};
        String [] dep5 = {"f", "a"};
        String [] dep6 = {"f", "b"};

        return Arrays.asList(dep1, dep2, dep3, dep4, dep5, dep6);
    }

    public static void main(String[] args) {
        Graph graph = MiscUtils.createGraph();
        printGraph(graph);
    }

    public static void printGraph(Graph graph) {
        graph.getAdjacentVertices().entrySet().forEach(entry -> Logger.stdout(entry.getKey() + ":" + entry.getValue()));
    }
}
