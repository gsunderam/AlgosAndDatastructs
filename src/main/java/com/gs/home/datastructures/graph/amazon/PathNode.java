package com.gs.home.datastructures.graph.amazon;

/**
 * Structure to hold the to be Visited persons
 */
public class PathNode {
    /** Keep track of previous node. Not used currently, leaving it  */
    private int prevId;
    /** Next person to visit */
    private Person next;

    public PathNode(int pid, Person next) {
        this.prevId = pid;
        this.next = next;
    }

    public int getPrevId() {
        return prevId;
    }

    public Person getNext() {
        return next;
    }
}
