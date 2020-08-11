package com.gs.home.datastructures.graph.amazon;

import com.gs.home.log.Logger;

import java.util.*;

/**
 * Implementation of bidirectional BFS in a tree. Search from source and destination.
 * when the nodes meet, merge the paths to get the SHORTEST path between src and dest.
 //TODO: Validations of source and dest, check for cycles and a few other use cases
 * Happy path is ONLY tested.
 */
public class GraphBBFS {
    /** Given graph of people */
    private Map<Integer, Person> graph = new HashMap<>();

    public GraphBBFS() {
        graph = Utility.createPersonNodes();
    }

    public void printGraph() {
        Logger.stdout(graph);
    }

    /**
     * Does a bi-directional BFS
     */
    public void searchBiBFS(int source, int dest) {
        BFSData sourceData = new BFSData(graph.get(source));
        BFSData destData = new BFSData(graph.get(dest));

        while (!sourceData.getToVisit().isEmpty() && !destData.getToVisit().isEmpty()) {
            /** search from source */
            Person personFromSrc = searchLevels(sourceData, destData);
            if (personFromSrc != null) {
                Logger.stdout("Search END! Merging paths");
                mergePaths(personFromSrc, sourceData.getVisited(), destData.getVisited());
                return;
            }

            /** search from dest */
            Person personFromDest = searchLevels(destData, sourceData);
            if (personFromDest != null) {
                Logger.stdout("Search ENDS Merging");
                mergePaths(personFromDest, sourceData.getVisited(), destData.getVisited());
                return;
            }
        }
    }

    /**
     * Searches both ways and returns the common person for BOTH paths
     * @param sourceData
     * @param destData
     * @return Common person object
     */
    public Person searchLevels(BFSData sourceData, BFSData destData) {
        int numNodes = sourceData.getToVisit().size();
        for (int i = 0; i < numNodes; i++) {
            PathNode pathNode = sourceData.getToVisit().poll();
            Person person = pathNode.getNext();
            int currentPid = person.getId();

            if (destData.getVisited().containsKey(currentPid)) return person;

            List<Person> friends = person.getFriends();
            for (Person friend: friends) {
                if (!sourceData.getVisited().containsKey(friend.getId())) {
                    PathNode node = new PathNode(currentPid, friend);
                    sourceData.getToVisit().offer(node);
                    sourceData.getVisited().put(friend.getId(), person);
                }
            }
        }

        return null;
   }

    /**
     * TODO: Needs to be implemented. Easy! just track the source map forward and the dest map in
     * reverse. Done something similar in another graph exercise. So leaving it for now -:)
     * @param person Collision spot
     * @param sourceMap visited nodes in sorc map
     * @param destMap same in dest map
     */
   public void mergePaths(Person person, Map<Integer, Person> sourceMap, Map<Integer, Person> destMap) {
        Logger.stdout("=========SOURCE==============");
        sourceMap.entrySet().forEach(entry -> Logger.stdout(String.format("Key = %s value = %s", entry.getKey(), entry.getValue())));
        Logger.stdout("=========DEST==============");
        destMap.entrySet().forEach(entry -> Logger.stdout(String.format("Key = %s value = %s", entry.getKey(), entry.getValue())));
   }

    public static void main(String[] args) {
        GraphBBFS bibfs = new GraphBBFS();
//        bibfs.printGraph();
        bibfs.searchBiBFS(1, 9);
    }
}
