package com.gs.home;

import com.gs.home.datastructures.graph.Graph;
import com.gs.home.datastructures.graph.Vertex;
import com.gs.home.log.Logger;
import com.gs.home.utils.MiscUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class BuildOrder {
    public String[] getBuildOrder() {
        final List<String> orderList = new ArrayList<>();
        Graph graph = MiscUtils.createGraph();
        graph.getAdjacentVertices().keySet().stream().forEach(vertex -> {
            Logger.stdout("Processing : " + vertex);
            if (!vertex.isVisited()) {
                doDFS(graph, vertex, orderList);
            }
        });

        return orderList.toArray(new String[orderList.size()]);
    }

    public void doDFS(Graph graph, Vertex node, List<String> orderList) {
        Set<Vertex> vertices = graph.getVertices(node.getName()).keySet();
        for (Vertex vertex: vertices) {
            if (!vertex.isVisited()) {
                doDFS(graph, vertex, orderList);
            }
        }

        node.setVisited(true);
        orderList.add(node.getName());
    }

    public static void main(String[] args) {
        BuildOrder buildOrder = new BuildOrder();
        String[] resultList = buildOrder.getBuildOrder();
        Logger.stdout(Arrays.toString(resultList));
    }
}
