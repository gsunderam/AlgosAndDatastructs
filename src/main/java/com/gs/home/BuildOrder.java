package com.gs.home;

import com.gs.home.datastructures.graph.Graph;
import com.gs.home.datastructures.graph.Vertex;
import com.gs.home.log.Logger;
import com.gs.home.utils.MiscUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Model a list of project dependencies such as {[f, b], [f, a], [b, c], [d, e],...} as a graph and return
 * the build order. second is dependent on the first. This is where Graph data structure is useful
 * Use DFS to find the build order. At the end of every DFS, add that element. This is coz. graph edges are
 * modelled like so, b --> f, a --> f or in other words DEP --> INDEP
 * Time complexity : O(N) N = num of projects
 */
public class BuildOrder {
    public String[] getBuildOrder() {
        final List<String> orderList = new ArrayList<>();
        Graph graph = MiscUtils.createGraph();
        graph.getAdjacentVertices().keySet().stream().forEach(vertex -> {
            Logger.stdout("Processing : " + vertex);
            if (!vertex.isVisited()) { //Pick a random node and start the DFS
                doDFS(graph, vertex, orderList);
            }
        });

        return orderList.toArray(new String[orderList.size()]);
    }

    /**
     * Recurse on the nodes's children and add the last node in the search to the build order
     * as this project is THE independent project. Repeat
     * @param graph
     * @param node
     * @param orderList
     */
    public void doDFS(Graph graph, Vertex node, List<String> orderList) {
        Set<Vertex> vertices = graph.getVertices(node.getName()).keySet();
        for (Vertex vertex: vertices) {
            if (!vertex.isVisited()) { /** Check whether already done */
                doDFS(graph, vertex, orderList);
            }
        }

        /** Mark the project or node as "DONE", so to avoid cycles */
        node.setVisited(true);
        orderList.add(node.getName());
    }

    public static void main(String[] args) {
        BuildOrder buildOrder = new BuildOrder();
        String[] resultList = buildOrder.getBuildOrder();
        Logger.stdout(Arrays.toString(resultList));
    }
}
