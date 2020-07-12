package com.gs.home.datastructures.graph;

import java.util.Objects;

public class VertexWeight implements Comparable<VertexWeight> {
    Integer weight;
    String name;

    public VertexWeight(String name, int weight) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public int compareTo(VertexWeight other) {
        return this.weight.compareTo(other.weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VertexWeight that = (VertexWeight) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + ":" + weight;
    }
}
