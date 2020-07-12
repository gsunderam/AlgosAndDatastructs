package com.gs.home.datastructures.graph;

public class Vertex {
    String name;
    boolean visited;

    public Vertex(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (other instanceof Vertex &&
                this.name.equalsIgnoreCase(((Vertex) other).name)) return true;

        return false;
    }

    @Override
    public int hashCode() {
        return System.identityHashCode(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
