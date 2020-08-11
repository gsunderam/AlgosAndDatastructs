package com.gs.home.datastructures.graph.amazon;

import java.util.*;

/**
 * Class to encapsulate the Forward and backward search details.
 */
public class BFSData {
    /** Queue to hold the nodes to be visited*/
    private final Queue<Person> toVisit = new LinkedList<>();

    /** To keep track of the already visited path */
    /** Key: current friend, value = prior person */
    private final Map<Integer, Person> visited = new HashMap<>();
    public BFSData() {}

    public BFSData(Person root) {
        this.toVisit.offer(root);
        this.visited.put(root.getId(), null);
    }

    public Queue<Person> getToVisit() {
        return toVisit;
    }

    public Map<Integer, Person> getVisited() {
        return visited;
    }

    public boolean isEmpty() {
        return toVisit.isEmpty();
    }
}
